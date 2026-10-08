package org.upemor.widgets;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;

import org.upemor.models.base.Entidad;

/**
 * Panel reutilizable para mostrar una lista de entidades genéricas en un JScrollPane.
 * Permite diferentes modos de visualización: vista, detalle y selección múltiple.
 *
 * @param <T> Tipo de entidad que extiende de Entidad base.
 */
public class PanelListaEntidades<T extends Entidad> extends JPanel {
    /** Panel interno que contiene los elementos visuales de la lista. */
    private JPanel panel;
    /** ScrollPane que envuelve al panel de la lista. */
    private JScrollPane scrollPane;
    /** Lista de IDs seleccionados (para selección múltiple). */
    private List<Long> listaSeleccion = new ArrayList<>();

    /** Modo actual del panel (vista, detalle, selección). */
    private int modo;

    /** Constante: modo vista (solo muestra la lista). */
    public final int MODO_VISTA = 0;
    /** Constante: modo detalle (permite ver detalles de la entidad). */
    public final int MODO_DETALLE = 1;
    /** Constante: modo selección (permite seleccionar múltiples entidades). */
    public final int MODO_SELECCION = 2;
    
    /** Indica si se permite la edición de entidades. */
    private boolean isEdicion = true;
    /** Indica si se permite la eliminación de entidades. */
    private boolean isEliminacion = true;

    /**
     * Constructor por defecto. Inicializa el panel en modo vista.
     */
    public PanelListaEntidades() {
        init();
        modo = MODO_VISTA;
    }

    /**
     * Cambia el modo de funcionamiento del panel.
     * @param modo Modo a establecer (MODO_VISTA, MODO_DETALLE, MODO_SELECCION).
     */
    public void setModo(int modo) {
        this.modo = modo;
    }

    /**
     * Habilita o deshabilita la edición de entidades.
     * @param isEdicion true para permitir edición, false para deshabilitar.
     */
    public void tieneEdicion(boolean isEdicion) {
        this.isEdicion = isEdicion;
    }

    /**
     * Habilita o deshabilita la eliminación de entidades.
     * @param isEliminacion true para permitir eliminación, false para deshabilitar.
     */
    public void tieneEliminacion(boolean isEliminacion) {
        this.isEliminacion = isEliminacion;
    }

    /**
     * Cambia el modo a detalle (permite ver detalles de la entidad).
     */
    public void setVistaDetalles() {
        this.modo = MODO_DETALLE;
    }

    /**
     * Cambia el modo a selección múltiple y asigna la lista de IDs seleccionados.
     * @param listaSeleccion Lista de IDs seleccionados.
     */
    public void setListaSeleccionId(List<Long> listaSeleccion) {
        this.modo = MODO_SELECCION;
        this.listaSeleccion = listaSeleccion;
    }

    /**
     * Obtiene la lista de IDs seleccionados actualmente.
     * @return Lista de IDs seleccionados.
     */
    public List<Long> obtenerSeleccionesId() {
        return listaSeleccion;
    }

    /**
     * Inicializa los componentes gráficos y la estructura del panel.
     */
    private void init() {
        setLayout(new BorderLayout());
        setOpaque(false);
        
        panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        scrollPane = new JScrollPane(panel);
        scrollPane.setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Actualiza la lista de entidades mostradas en el panel.
     * Si la lista está vacía, muestra un mensaje indicativo.
     *
     * @param listaEntidades Lista de entidades a mostrar.
     */
    public void actualizarLista(List<T> listaEntidades) {
        panel.removeAll();

        if (listaEntidades.isEmpty()) {
            mostrarMensajeVacio();
            return;
        }

        for (T entidad : listaEntidades) {
            ItemEntidad<T> item;
            
            switch (modo) {
                case MODO_VISTA:
                    item = new ItemEntidad<>(entidad, isEdicion, isEliminacion);
                    break;
                case MODO_DETALLE:
                    item = new ItemEntidad<>(isEdicion, isEliminacion, entidad);
                    break;
                case MODO_SELECCION:
                    item = new ItemEntidad<>(entidad, listaSeleccion, isEdicion, isEliminacion);
                    break;
                default:
                    item = new ItemEntidad<>(entidad, isEdicion, isEliminacion);
                    break;
            }

            item.setMaximumSize(new Dimension(Integer.MAX_VALUE, item.getPreferredSize().height));
            item.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            
            panel.add(item);
        }

        panel.revalidate();
        panel.repaint();
    }

    /**
     * Muestra un mensaje visual cuando la lista de entidades está vacía.
     * Crea un panel con un JLabel estilizado y lo agrega al panel principal.
     */
    private void mostrarMensajeVacio() {
        JPanel panelVacio = new JPanel(new BorderLayout());
        panelVacio.setOpaque(false);
        panelVacio.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        
        JLabel mensajeVacio = new JLabel("No hay elementos para mostrar", JLabel.CENTER);
        mensajeVacio.setFont(new Font("Segoe UI", Font.ITALIC, 18));
        mensajeVacio.setForeground(new Color(134, 142, 150));
        
        panelVacio.add(mensajeVacio, BorderLayout.CENTER);
        panel.add(Box.createVerticalStrut(50));
        panel.add(panelVacio);
        
        panel.revalidate();
        panel.repaint();
    }

}
