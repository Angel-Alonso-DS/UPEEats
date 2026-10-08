package org.upemor.widgets;

import java.awt.*;
import java.beans.BeanProperty;
import javax.swing.*;
import org.upemor.theme.*;

/**
 * Componente de encabezado reutilizable que muestra un título alineado a la izquierda
 * y una sección de búsqueda con campo de texto, botón de búsqueda y botón de filtro
 * alineados a la derecha.
 *
 * Este componente puede integrarse a cualquier ventana o panel, y ofrece la posibilidad
 * de personalizar la visibilidad del área de búsqueda, así como definir acciones específicas
 * para los botones de búsqueda y filtro.
 */
public class Encabezado extends JPanel {

    // --- Componentes visuales ---
    private JLabel tituloLbl;
    private JTextField campoBusqueda;
    private JButton bttnBusqueda;
    private JButton bttnFitro;

    // --- Estado de visibilidad del panel de búsqueda ---
    private boolean mostrarBusqueda = false;

    // --- Interfaces para acciones personalizadas ---
    public interface BusquedaAccion {
        void onBusqueda(String texto); // Acción ejecutada al presionar el botón de búsqueda
    }

    public interface FiltroAccion {
        void onFiltro(); // Acción ejecutada al presionar el botón de filtro
    }

    private BusquedaAccion busquedaAccion;
    private FiltroAccion filtroAccion;

    /**
     * Constructor principal que inicializa los componentes y establece su diseño.
     */
    public Encabezado() {
        setLayout(new BorderLayout());
        setOpaque(false); // fondo transparente
        setPreferredSize(Dimensiones.ENCABEZADO); // tamaño fijo definido externamente

        // ---------- Título ----------
        tituloLbl = new JLabel("[Titulo]");
        tituloLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
        tituloLbl.setForeground(Colores.TEXTO);
        add(tituloLbl, BorderLayout.WEST);

        // ---------- Panel de búsqueda (derecha) ----------
        JPanel panelBusquedas = new JPanel();
        panelBusquedas.setLayout(new BoxLayout(panelBusquedas, BoxLayout.X_AXIS));
        panelBusquedas.setOpaque(false); // fondo transparente

        // ---------- Botón de búsqueda ----------
        bttnBusqueda = new JButton();
        bttnBusqueda.setVisible(mostrarBusqueda);
        bttnBusqueda.setMinimumSize(Dimensiones.WIDGET_CUADRADO);
        bttnBusqueda.setMaximumSize(Dimensiones.WIDGET_CUADRADO);
        bttnBusqueda.setPreferredSize(Dimensiones.WIDGET_CUADRADO);
        bttnBusqueda.setAlignmentY(Component.CENTER_ALIGNMENT);
        bttnBusqueda.setIcon(new ImageIcon(getClass().getResource("/icons/search-icon.png")));
        bttnBusqueda.addActionListener(e -> {
            if (busquedaAccion != null) {
                busquedaAccion.onBusqueda(campoBusqueda.getText());
            }
        });

        // ---------- Campo de texto para búsqueda ----------
        campoBusqueda = new JTextField(15);
        campoBusqueda.setVisible(mostrarBusqueda);
        campoBusqueda.setFont(new Font("Segoe UI", Font.BOLD, 18));
        campoBusqueda.setMinimumSize(Dimensiones.WIDGET_DIMENSION);
        campoBusqueda.setMaximumSize(Dimensiones.WIDGET_DIMENSION);
        // campoBusqueda.setAlignmentY(Component.CENTER_ALIGNMENT); // opcional si se desea centrar

        // ---------- Botón de filtro ----------
        bttnFitro = new JButton();
        bttnFitro.setVisible(mostrarBusqueda);
        bttnFitro.setMinimumSize(Dimensiones.WIDGET_CUADRADO);
        bttnFitro.setMaximumSize(Dimensiones.WIDGET_CUADRADO);
        bttnFitro.setPreferredSize(Dimensiones.WIDGET_CUADRADO);
        bttnFitro.setAlignmentY(Component.CENTER_ALIGNMENT);
        bttnFitro.setIcon(new ImageIcon(getClass().getResource("/icons/filter.png")));
        bttnFitro.addActionListener(e -> {
            if (filtroAccion != null) {
                filtroAccion.onFiltro();
            }
        });

        // ---------- Añadir componentes al panel de búsqueda ----------
        panelBusquedas.add(bttnBusqueda);
        panelBusquedas.add(Box.createHorizontalStrut(6)); // espacio entre componentes
        panelBusquedas.add(campoBusqueda);
        panelBusquedas.add(Box.createHorizontalStrut(6));
        panelBusquedas.add(bttnFitro);

        // ---------- Añadir panel derecho al contenedor principal ----------
        add(panelBusquedas, BorderLayout.EAST);
    }

    // =============================
    // Propiedades públicas del bean
    // =============================

    /**
     * Obtiene el texto del título del encabezado.
     * @return Texto del título.
     */
    public String getTitulo() {
        return tituloLbl.getText();
    }

    /**
     * Establece el texto del título que se muestra a la izquierda.
     * Esta propiedad será editable desde el editor visual si se usa en NetBeans.
     *
     * @param titulo Texto a mostrar como título.
     */
    @BeanProperty
    public void setTitulo(String titulo) {
        this.tituloLbl.setText(titulo);
    }

    /**
     * Indica si se debe mostrar el panel de búsqueda (campo + botones).
     * @return true si se muestra, false si se oculta.
     */
    public boolean isMostrarBusqueda() {
        return mostrarBusqueda;
    }

    /**
     * Establece la visibilidad del panel de búsqueda.
     * Esta propiedad también será editable en el editor de propiedades de NetBeans.
     *
     * @param mostrar true para mostrar los controles de búsqueda y filtro.
     */
    @BeanProperty
    public void setMostrarBusqueda(boolean mostrar) {
        this.mostrarBusqueda = mostrar;

        bttnBusqueda.setVisible(mostrar);
        campoBusqueda.setVisible(mostrar);
        bttnFitro.setVisible(mostrar);

        revalidate();
        repaint();
    }

    // =============================
    // Setters para listeners
    // =============================

    /**
     * Establece la acción a ejecutar cuando se presiona el botón de búsqueda.
     * @param accion Implementación de la interfaz BusquedaAccion.
     */
    public void setBusquedaAccion(BusquedaAccion accion) {
        this.busquedaAccion = accion;
    }

    /**
     * Establece la acción a ejecutar cuando se presiona el botón de filtro.
     * @param accion Implementación de la interfaz FiltroAccion.
     */
    public void setFiltroAccion(FiltroAccion accion) {
        this.filtroAccion = accion;
    }
}
