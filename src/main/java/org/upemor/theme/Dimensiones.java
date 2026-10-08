package org.upemor.theme;

import java.awt.Dimension;

import javax.swing.GroupLayout;

/**
 * Tamaños predefinidos de componentes
 */
public class Dimensiones {
    /** Tamaño del bodrde de los objetos con la ventana */
    public static final int MARGEN_VENTANAS = 50;

    /** Tamaño de la altura de los componentes (Botones, Campos de texto) */
    public static final int ALTURA_WIDGETS = 40;
    /** Tamaño de la anchura de los componentes (Botones, Campos de texto) */
    public static final int ACHURA_WIDGETS = 500;

    /** Tamaño que tomara por setPreferredSize el Encabezado */
    public static final Dimension ENCABEZADO = new Dimension(500, 50);
    /** Dimension de los componentes (Botones, Campos de texto) */
    public static final Dimension WIDGET_DIMENSION = new Dimension(ACHURA_WIDGETS, ALTURA_WIDGETS);
    /** Dimension de los componentes con altura definida (Botones, Campos de texto) */
    public static final Dimension WIDGET_ALTURAD = new Dimension(GroupLayout.PREFERRED_SIZE, ALTURA_WIDGETS);
    /** Dimension cuadrada de los componentes (Botones, Campos de texto) */
    public static final Dimension WIDGET_CUADRADO = new Dimension(ALTURA_WIDGETS, ALTURA_WIDGETS);

    /** Dimension de las ventanas de los formularios */
    public static final Dimension VENTANA_FORM_CHICA = new Dimension(600, 500);
    /** Dimension de las ventanas de los formularios */
    public static final Dimension VENTANA_FORM_LARGO = new Dimension(600, 680);

    /** Dimension del tamaño de la ventana principal */
    public static final Dimension VENTANA_MAIN = new Dimension(1280, 720);
    /** Dimension de las ventanas de las vistas */
    public static final Dimension VENTANA_VISTA_CHICA = new Dimension(800, 500);
    /** Dimension de las ventanas de las vistas */
    public static final Dimension VENTANA_VISTA_LARGO = new Dimension(800, 695);

    /** Dimension de los paneles de acceso */
    public static final Dimension PANEL_NORMAL = new Dimension(894, 720);

    private Dimensiones() {};
}
