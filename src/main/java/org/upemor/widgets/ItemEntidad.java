package org.upemor.widgets;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;

import org.upemor.models.base.Entidad;
import org.upemor.theme.Colores;
import org.upemor.theme.Dimensiones;
import org.upemor.utils.NotificadorGlobal;

/**
 * Componente visual reutilizable para mostrar información de una entidad genérica en la interfaz.
 * Permite mostrar botones de edición y eliminación, así como selección múltiple.
 *
 * @param <T> Tipo de entidad que extiende de Entidad base.
 */
public class ItemEntidad<T extends Entidad> extends JPanel{
    /**
     * Etiqueta para mostrar la imagen asociada a la entidad.
     * Se actualiza dinámicamente según el tipo de entidad y su información.
     */
    private JLabel textoImagen;
    /** Panel que contiene los botones de acción (editar/eliminar). */
    private JPanel panelBotones;
    /** Panel que contiene la información textual de la entidad.*/
    private JPanel panelInfo;
    /** Entidad que se representa en este componente. */
    private T entidad;
    /** Lista de IDs seleccionados (para selección múltiple). */
    private List<Long> listaSeleccion;
    /** Indica si se debe mostrar el botón de editar. */
    private boolean isEditar;
    /** Indica si se debe mostrar el botón de eliminar. */
    private boolean isEliminar;

    /** Constantes para identificar los tipos de información de la entidad. */
    public static final String TITULO = "Tt";
    public static final String SUBTITULO = "su";
    public static final String CONTENIDO = "co";
    public static final String IMAGEN = "im";
    public static final String FECHA = "fe";
    public static final String TIEMPO = "ti";
    public static final String PRECIO = "pr";
    public static final String CANTIDAD = "ca";
    public static final String CATEGORIAS = "ct";


    /** Modo de funcionamiento del componente (0: normal, 1: detalle, 2: selección múltiple). */
    private int modo;

    /**
     * Constructor para modo normal (sin selección múltiple).
     * @param entidad Entidad a mostrar.
     * @param isEditar Si se muestra el botón de editar.
     * @param isEliminar Si se muestra el botón de eliminar.
     */
    public ItemEntidad(T entidad, boolean isEditar, boolean isEliminar) {
        this.entidad = entidad;
        this.isEditar = isEditar;
        this.isEliminar = isEliminar;
        modo = 0;
        init();
    }
    
    /**
     * Constructor alternativo para modo detalle.
     * @param isEditar Si se muestra el botón de editar.
     * @param isEliminar Si se muestra el botón de eliminar.
     * @param entidad Entidad a mostrar.
     */
    public ItemEntidad(boolean isEditar, boolean isEliminar, T entidad) {
        this.entidad = entidad;
        this.isEditar = isEditar;
        this.isEliminar = isEliminar;
        modo = 1;
        init();
    }

    /**
     * Constructor para modo selección múltiple.
     * @param entidad Entidad a mostrar.
     * @param listaSeleccion Lista de IDs seleccionados.
     * @param isEditar Si se muestra el botón de editar.
     * @param isEliminar Si se muestra el botón de eliminar.
     */
    public ItemEntidad(T entidad, List<Long> listaSeleccion, boolean isEditar, boolean isEliminar) {
        this.entidad = entidad;
        this.isEditar = isEditar;
        this.isEliminar = isEliminar;
        this.listaSeleccion = listaSeleccion;
        modo = 2;
        init();
    }

    /**
     * Inicializa los componentes gráficos y la lógica de interacción del widget.
     * Configura el layout, paneles, listeners y botones de acción según el modo.
     */
    private void init() {
        setLayout(new BorderLayout());
        setBorder(null);

        textoImagen = new JLabel("");
        panelInfo = crearPanelInfo();
        cargarContenido(panelInfo);
        panelBotones = crearPanelBotones();
        if (modo < 2) add(panelBotones, BorderLayout.EAST);
        add(panelInfo, BorderLayout.CENTER);
    }

    /**
     * Crea y configura el panel de información textual, incluyendo listeners para selección.
     * @return JPanel configurado
     */
    private JPanel crearPanelInfo() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        if (modo > 0) {
            panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            panel.addMouseListener(crearMouseListenerSeleccion());
        }
        return panel;
    }

    /**
     * Crea y configura el panel de botones de acción (editar/eliminar) según permisos.
     * @return JPanel configurado
     */
    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        if (isEditar) {
            JButton bttnEditar = crearBotonAccion("/icons/pen-to-square.png", Colores.SECUNDARIO, e ->
                NotificadorGlobal.getInstancia().notificarCambio("edicion " + entidad.getTipoEntidad(), new Object[] {entidad})
            );
            panel.add(bttnEditar);
        }
        if (isEliminar) {
            JButton bttnEliminar = crearBotonAccion("/icons/trash-solid.png", Colores.PRIMARIO, e ->
                NotificadorGlobal.getInstancia().notificarCambio("eliminacion " + entidad.getTipoEntidad(), new Object[] {entidad})
            );
            panel.add(bttnEliminar);
        }
        return panel;
    }

    /**
     * Crea un botón de acción con ícono, color y listener.
     * @param iconURL Ruta del ícono
     * @param color Color de fondo
     * @param listener Acción al hacer click
     * @return JButton configurado
     */
    private JButton crearBotonAccion(String iconURL, Color color, ActionListener listener) {
        JButton bttn = cargarBoton(iconURL);
        bttn.setBackground(color);
        bttn.addActionListener(listener);
        return bttn;
    }

    /**
     * Crea un MouseListener para selección/hover en paneles y textos.
     * @return MouseAdapter configurado
     */
    private MouseAdapter crearMouseListenerSeleccion() {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Colores.CONTRA_FONDO);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (seleccionado) {
                    setBackground(Colores.ON_PRIMARIO);
                } else {
                    setBackground(Colores.FONDO);
                }
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccion();
            }
        };
    }

    // "/icons/search-icon.png"
    /**
     * Crea un botón con un ícono específico y dimensiones estándar.
     * @param iconURL Ruta del ícono a mostrar.
     * @return JButton configurado.
     */
    private JButton cargarBoton(String iconURL) {
        JButton bttn = new JButton();
        bttn.setMinimumSize(Dimensiones.WIDGET_CUADRADO);
        bttn.setMaximumSize(Dimensiones.WIDGET_CUADRADO);
        bttn.setPreferredSize(Dimensiones.WIDGET_CUADRADO);
        bttn.setIcon(new ImageIcon(getClass().getResource(iconURL)));

        return bttn;
    }
    
    /** Indica si la entidad está seleccionada actualmente. */
    private boolean seleccionado = false;

    /**
     * Alterna el estado de selección de la entidad y notifica los cambios globalmente.
     */
    private void seleccion() {
        if (modo == 0) return;

        if (modo == 1) {
            NotificadorGlobal.getInstancia().notificarCambio("detalle " + entidad.getTipoEntidad(), new Object[] {entidad});
            return;
        }

        if (seleccionado) {
            seleccionado = false;
            listaSeleccion.remove(entidad.getId());
            setBackground(Colores.FONDO);
            revalidate();
            repaint();
        } else {
            seleccionado = true;
            listaSeleccion.add(entidad.getId());
            setBackground(Colores.ON_PRIMARIO);
            revalidate();
            repaint();
        }
    }

    /**
     * Verifica si la entidad está seleccionada en la lista de selección.
     * @return true si está seleccionada, false en caso contrario.
     */
    private boolean estadoSeleccioando() {
        for (long t : listaSeleccion) {
            if (t == entidad.getId()) return true;
        }

        return false;
    }

    /**
     * Crea un JTextArea estilizado para mostrar texto con formato.
     * @param contenido Texto a mostrar.
     * @param fontSize Tamaño de fuente.
     * @param italic Si el texto debe ser itálico.
     * @return JTextArea configurado.
     */
    /**
     * Crea un JTextArea estilizado para mostrar texto con formato.
     * Incluye listeners de selección si aplica.
     * @param contenido Texto a mostrar
     * @param fontSize Tamaño de fuente
     * @param italic Si el texto debe ser itálico
     * @return JTextArea configurado
     */
    private JTextArea crearTexto(String contenido, int fontSize, boolean italic) {
        JTextArea textArea = new JTextArea(contenido);
        textArea.setFont(new Font("Segoe UI", italic ? Font.ITALIC : Font.PLAIN, fontSize));
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(null);
        textArea.setAlignmentX(LEFT_ALIGNMENT);
        if (modo > 0) {
            textArea.setFocusable(false);
            textArea.setCursor(new Cursor(Cursor.HAND_CURSOR));
            textArea.addMouseListener(crearMouseListenerSeleccion());
        }
        return textArea;
    }

    /**
     * Carga y organiza la información de la entidad en el panel proporcionado.
     * @param panel Panel donde se agregan los componentes visuales.
     */
    /**
     * Carga y organiza la información de la entidad en el panel proporcionado.
     * Utiliza StringBuilder para eficiencia y documenta cada paso.
     * @param panel Panel donde se agregan los componentes visuales.
     */
    private void cargarContenido(JPanel panel) {
        // Si hay selección múltiple, marcar como seleccionado si corresponde
        if (listaSeleccion != null && estadoSeleccioando()) {
            seleccionado = true;
            setBackground(Colores.ON_SECUNDARIO);
            revalidate();
            repaint();
        }

        // Acumuladores de información textual
        StringBuilder titulo = new StringBuilder();
        StringBuilder subtitulo = new StringBuilder();
        StringBuilder contenido = new StringBuilder();
        StringBuilder listaCategorias = new StringBuilder("Categorias: ");
        StringBuilder fecha = new StringBuilder();
        StringBuilder tiempo = new StringBuilder();
        StringBuilder precio = new StringBuilder();
        StringBuilder cantidad = new StringBuilder();

        // Procesar la información de la entidad
        for (String[] info : entidad.toInfo()) {
            String tipo = info[0];
            String nombre = info[1];
            String valor = info[2];
            switch (tipo) {
                case TITULO:
                    titulo.append(valor).append(" ");
                    break;
                case SUBTITULO:
                    subtitulo.append(nombre).append(": ").append(valor).append(", ");
                    break;
                case CONTENIDO:
                    contenido.append(valor).append(" ");
                    break;
                case CATEGORIAS:
                    listaCategorias.append(valor);
                    break;
                case IMAGEN:
                    cargarImagenDesdeRuta(valor);
                    add(textoImagen, BorderLayout.WEST);
                    textoImagen.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    textoImagen.addMouseListener(crearMouseListenerSeleccion());
                    break;
                case FECHA:
                    fecha.append(nombre).append(": ").append(valor).append(", ");
                    break;
                case TIEMPO:
                    tiempo.append(nombre).append(": ").append(valor).append(", ");
                    break;
                case PRECIO:
                    precio.append(nombre).append(": $").append(valor);
                    break;
                case CANTIDAD:
                    cantidad.append(nombre).append(": ").append(valor).append(" ");
                    break;
                default:
                    break;
            }
        }

        // Agregar componentes visuales al panel
        JLabel jTitulo = new JLabel(titulo.toString());
        jTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jTitulo.setForeground(Colores.TEXTO);
        panel.add(jTitulo);

        if (modo < 2) {
            if (subtitulo.length() > 0) panel.add(crearTexto(subtitulo.toString(), 14, false));
            if (contenido.length() > 1) panel.add(crearTexto(contenido.toString(), 18, false));
            if (!listaCategorias.toString().equals("Categorias: ")) panel.add(crearTexto(listaCategorias.toString(), 14, false));
            if (tiempo.length() > 0) panel.add(crearTexto(tiempo.toString(), 14, true));
            if (fecha.length() > 0) panel.add(crearTexto(fecha.toString(), 14, true));
            if (precio.length() > 0) panel.add(new JLabel(precio.toString()));
            if (cantidad.length() > 0) panel.add(new JLabel(cantidad.toString()));
        }
    }

    /**
     * Carga y escala la imagen del producto desde una ruta de archivo para mostrarla en la etiqueta correspondiente.
     * @param rutaImagen Ruta relativa (por ejemplo, nombre de archivo en carpeta img/) o null.
     */
    private void cargarImagenDesdeRuta(String rutaImagen) {
        if (rutaImagen == null || rutaImagen.isEmpty()) {
            textoImagen.setIcon(null);
            textoImagen.setText("Sin imagen");
            return;
        }
        try {
            String rutaCompleta = "img/" + rutaImagen;
            ImageIcon icono = new ImageIcon(rutaCompleta);
            int ancho = 100;
            int alto = 100;
            Image imagenEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            textoImagen.setIcon(new ImageIcon(imagenEscalada));
            textoImagen.setText("");
        } catch (Exception e) {
            textoImagen.setText("Error al cargar imagen");
        }
    }

    /**
     * Devuelve el tamaño máximo permitido para este componente (ancho infinito, altura preferida).
     * @return Dimensión máxima.
     */
    @Override
    public Dimension getMaximumSize() {
        Dimension preferred = getPreferredSize();
        return new Dimension(Integer.MAX_VALUE, preferred.height);
    }
}
