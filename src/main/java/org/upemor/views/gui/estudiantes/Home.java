/*
 * Clase Home
 * Ventana principal para estudiantes en la interfaz gráfica de UPEEats.
 * Permite navegar entre las diferentes secciones: Inicio, Menú, Notificaciones, Sugerencias y Cuenta.
 * Utiliza un CardLayout para mostrar el contenido correspondiente según el botón seleccionado.
 */

package org.upemor.views.gui.estudiantes;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import java.awt.*;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.upemor.models.entities.Usuarios;
import org.upemor.views.gui.estudiantes.screens.*;
import org.upemor.views.gui.estudiantes.screens.Menu;
import org.upemor.views.gui.main.Main;

/**
 * Ventana principal para estudiantes.
 * Permite cambiar entre paneles de inicio, menú, notificaciones y cuenta.
 */
public class Home extends javax.swing.JFrame {
    
    /**
     * Constructor por defecto.
     * Inicializa los paneles sin datos de usuario.
     */
    public Home() {
        inicio = new Inicio();
        notificaciones = new Notificaciones();
        cuenta = new Cuenta();
        menu = new Menu();

        initComponents();
    }

    /**
     * Constructor que recibe el usuario autenticado.
     * Inicializa los paneles con los datos del usuario y muestra su nombre.
     * @param usuario Usuario autenticado.
     */
    public Home(Usuarios usuario) {
        inicio = new Inicio(usuario);
        notificaciones = new Notificaciones(usuario);
        cuenta = new Cuenta(usuario);
        menu = new Menu(usuario);

        initComponents();
        textoNombre.setText(usuario.getNombre());
    }

    /**
     * Inicializa y configura todos los componentes gráficos de la ventana.
     * Configura los botones de navegación y el CardLayout para mostrar los paneles correspondientes.
     * Este método es generado automáticamente por el editor de formularios.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bttnInicio = new javax.swing.JButton();
        bttonMenu = new javax.swing.JButton();
        bttnNotificaciones = new javax.swing.JButton();
        bttnSugerencias = new javax.swing.JButton();
        bttnCuenta = new javax.swing.JButton();
        textoNombre = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelConntent = new javax.swing.JPanel();
        cardLayout = new CardLayout();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1028, 720));
        setPreferredSize(new java.awt.Dimension(1028, 720));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        // Botón para mostrar el panel de inicio
        bttnInicio.setBackground(new java.awt.Color(239, 55, 67));
        bttnInicio.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bttnInicio.setForeground(new java.awt.Color(255, 212, 59));
        bttnInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/upemor/assets/icons/house-solid.png"))); // NOI18N
        bttnInicio.setText("Inicio");
        bttnInicio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bttnInicio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        bttnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnInicioActionPerformed(evt);
            }
        });

        // Botón para mostrar el panel de menú del día
        bttonMenu.setBackground(new java.awt.Color(51, 51, 51));
        bttonMenu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bttonMenu.setForeground(new java.awt.Color(255, 212, 59));
        bttonMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/upemor/assets/icons/bars-solid.png"))); // NOI18N
        bttonMenu.setText("Menu");
        bttonMenu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bttonMenu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        bttonMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttonMenuActionPerformed(evt);
            }
        });

        // Botón para mostrar el panel de notificaciones
        bttnNotificaciones.setBackground(new java.awt.Color(51, 51, 51));
        bttnNotificaciones.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bttnNotificaciones.setForeground(new java.awt.Color(255, 212, 59));
        bttnNotificaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/upemor/assets/icons/bell-solid.png"))); // NOI18N
        bttnNotificaciones.setText("Notificaciones");
        bttnNotificaciones.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bttnNotificaciones.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        bttnNotificaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnNotificacionesActionPerformed(evt);
            }
        });

        // Botón para mostrar el panel de sugerencias (aún no implementado en el CardLayout)
        bttnSugerencias.setBackground(new java.awt.Color(51, 51, 51));
        bttnSugerencias.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bttnSugerencias.setForeground(new java.awt.Color(255, 212, 59));
        bttnSugerencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/upemor/assets/icons/comment-solid.png"))); // NOI18N
        bttnSugerencias.setText("Sugerencias");
        bttnSugerencias.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bttnSugerencias.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        bttnSugerencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnSugerenciasActionPerformed(evt);
            }
        });

        // Botón para mostrar el panel de cuenta
        bttnCuenta.setBackground(new java.awt.Color(51, 51, 51));
        bttnCuenta.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bttnCuenta.setForeground(new java.awt.Color(255, 212, 59));
        bttnCuenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/upemor/assets/icons/user-solid.png"))); // NOI18N
        bttnCuenta.setText("Cuenta");
        bttnCuenta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bttnCuenta.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        bttnCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnCuentaActionPerformed(evt);
            }
        });

        textoNombre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        textoNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoNombre.setText("[NOMBRE]");
        textoNombre.setAutoscrolls(true);
        textoNombre.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Bienvenido");

        // Layout del panel lateral de navegación
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bttnInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bttonMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bttnNotificaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                            .addComponent(bttnSugerencias, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bttnCuenta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bttnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bttonMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bttnNotificaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bttnSugerencias, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 267, Short.MAX_VALUE)
                .addComponent(bttnCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        // Panel central donde se muestran los diferentes paneles según la navegación
        javax.swing.GroupLayout panelConntentLayout = new javax.swing.GroupLayout(panelConntent);
        panelConntent.setLayout(panelConntentLayout);
        panelConntentLayout.setHorizontalGroup(
            panelConntentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 782, Short.MAX_VALUE)
        );
        panelConntentLayout.setVerticalGroup(
            panelConntentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );

        // Configuración del CardLayout y adición de los paneles
        panelConntent.setLayout(cardLayout);
        panelConntent.add(inicio, "inicio");
        panelConntent.add(notificaciones, "notificaciones");
        panelConntent.add(cuenta, "cuenta");
        panelConntent.add(menu, "menu");
        
        cardLayout.show(panelConntent, "inicio");

        // Layout principal de la ventana
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelConntent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelConntent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Acción al presionar el botón "Inicio".
     * Cambia el color del botón y muestra el panel de inicio.
     */
    private void bttnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnInicioActionPerformed
        bttnInicio.setBackground(new Color(239, 55, 67));
        bttonMenu.setBackground(new Color(51, 51, 51));
        bttnNotificaciones.setBackground(new Color(51, 51, 51));
        bttnSugerencias.setBackground(new Color(51, 51, 51));
        bttnCuenta.setBackground(new Color(51, 51, 51));
        cardLayout.show(panelConntent, "inicio");
    }//GEN-LAST:event_bttnInicioActionPerformed

    /**
     * Acción al presionar el botón "Menu".
     * Cambia el color del botón y muestra el panel del menú del día.
     */
    private void bttonMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttonMenuActionPerformed
        bttonMenu.setBackground(new Color(239, 55, 67));
        bttnInicio.setBackground(new Color(51, 51, 51));
        bttnNotificaciones.setBackground(new Color(51, 51, 51));
        bttnSugerencias.setBackground(new Color(51, 51, 51));
        bttnCuenta.setBackground(new Color(51, 51, 51));
        cardLayout.show(panelConntent, "menu");
    }//GEN-LAST:event_bttonMenuActionPerformed

    /**
     * Acción al presionar el botón "Notificaciones".
     * Cambia el color del botón y muestra el panel de notificaciones.
     */
    private void bttnNotificacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnNotificacionesActionPerformed
        bttnNotificaciones.setBackground(new Color(239, 55, 67));
        bttnInicio.setBackground(new Color(51, 51, 51));
        bttonMenu.setBackground(new Color(51, 51, 51));
        bttnSugerencias.setBackground(new Color(51, 51, 51));
        bttnCuenta.setBackground(new Color(51, 51, 51));
        cardLayout.show(panelConntent, "notificaciones");
    }//GEN-LAST:event_bttnNotificacionesActionPerformed

    /**
     * Acción al presionar el botón "Sugerencias".
     * Cambia el color del botón y (aún no implementado) mostraría el panel de sugerencias.
     */
    private void bttnSugerenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnSugerenciasActionPerformed
        bttnSugerencias.setBackground(new Color(239, 55, 67));
        bttnInicio.setBackground(new Color(51, 51, 51));
        bttonMenu.setBackground(new Color(51, 51, 51));
        bttnNotificaciones.setBackground(new Color(51, 51, 51));
        bttnCuenta.setBackground(new Color(51, 51, 51));
        // cardLayout.show(panelConntent, "sugerencias");
    }//GEN-LAST:event_bttnSugerenciasActionPerformed

    /**
     * Acción al presionar el botón "Cuenta".
     * Cambia el color del botón y muestra el panel de cuenta del usuario.
     */
    private void bttnCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnCuentaActionPerformed
        bttnCuenta.setBackground(new Color(239, 55, 67));
        bttnInicio.setBackground(new Color(51, 51, 51));
        bttonMenu.setBackground(new Color(51, 51, 51));
        bttnNotificaciones.setBackground(new Color(51, 51, 51));
        bttnSugerencias.setBackground(new Color(51, 51, 51));
        cardLayout.show(panelConntent, "cuenta");
    }//GEN-LAST:event_bttnCuentaActionPerformed

    /**
     * Método principal para ejecutar la ventana de Home.
     * Configura el tema visual y muestra la ventana.
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Crear y mostrar la ventana */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Declaración de variables de los componentes gráficos y paneles
    private javax.swing.JButton bttnCuenta;
    private javax.swing.JButton bttnInicio;
    private javax.swing.JButton bttnNotificaciones;
    private javax.swing.JButton bttnSugerencias;
    private javax.swing.JButton bttonMenu;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelConntent;
    private javax.swing.JLabel textoNombre;
    private CardLayout cardLayout;
    private Inicio inicio;
    private Menu menu;
    private Notificaciones notificaciones;
    private Cuenta cuenta;
    // End of variables declaration//GEN-END:variables
}
