/*
 * Clase Main
 * Ventana principal de la aplicación UPEEats para estudiantes y empleados.
 * Permite alternar entre las pantallas de acceso y registro de estudiantes, y acceder al módulo de empleados/administradores.
 * Utiliza un CardLayout para mostrar el formulario de acceso o registro según la selección del usuario.
 */

package org.upemor.views.gui.main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.upemor.views.gui.admin.acceso.MainAdmin;
import org.upemor.views.gui.main.pages.Acceso;
import org.upemor.views.gui.main.pages.Registro;

import java.awt.*;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

/**
 * Ventana principal de la aplicación UPEEats.
 * Permite cambiar entre el acceso y el registro de estudiantes, y acceder al módulo de empleados.
 */
public class Main extends javax.swing.JFrame {
    // CardLayout para alternar entre las vistas de acceso y registro
    private CardLayout cardLayout;

    /**
     * Constructor de la clase Main.
     * Inicializa los componentes gráficos de la ventana.
     */
    public Main() {
        initComponents();
    }

    /**
     * Inicializa y configura todos los componentes gráficos de la ventana.
     * Configura el CardLayout y agrega los paneles de acceso y registro.
     * Este método es generado automáticamente por el editor de formularios.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLogo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        botonAcceder = new javax.swing.JButton();
        botonRegistrarse = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        panelContenido = new javax.swing.JPanel();
        cardLayout = new CardLayout();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1028, 720));

        panelLogo.setBackground(new java.awt.Color(51, 51, 51));

        jLabel2.setBackground(new java.awt.Color(239, 55, 32));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/upemor/assets/LOGO_U4.png"))); // Logo de la universidad
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 55, 67), 4));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("UPEEats");

        // Botón para mostrar el formulario de acceso de estudiantes
        botonAcceder.setBackground(new java.awt.Color(239, 55, 67));
        botonAcceder.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        botonAcceder.setForeground(new java.awt.Color(255, 255, 255));
        botonAcceder.setText("Acceder");
        botonAcceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAccederActionPerformed(evt);
            }
        });

        // Botón para mostrar el formulario de registro de estudiantes
        botonRegistrarse.setBackground(new java.awt.Color(30, 30, 30));
        botonRegistrarse.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        botonRegistrarse.setForeground(new java.awt.Color(255, 255, 255));
        botonRegistrarse.setText("Registrarse");
        botonRegistrarse.setToolTipText("");
        botonRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarseActionPerformed(evt);
            }
        });

        // Botón para acceder al módulo de empleados/administradores
        jButton1.setBackground(new java.awt.Color(30, 30, 30));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/upemor/assets/icons/user-tie-solid-full.png"))); // Icono de usuario
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        // Layout del panel lateral con logo y botones
        javax.swing.GroupLayout panelLogoLayout = new javax.swing.GroupLayout(panelLogo);
        panelLogo.setLayout(panelLogoLayout);
        panelLogoLayout.setHorizontalGroup(
            panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogoLayout.createSequentialGroup()
                .addGroup(panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLogoLayout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addGroup(panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(botonAcceder, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(botonRegistrarse, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                    .addGroup(panelLogoLayout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addGroup(panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(panelLogoLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jButton1)))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        panelLogoLayout.setVerticalGroup(
            panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogoLayout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonAcceder, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(25, 25, 25))
        );

        // Layout del panel central donde se muestran los formularios de acceso y registro
        javax.swing.GroupLayout panelContenidoLayout = new javax.swing.GroupLayout(panelContenido);
        panelContenido.setLayout(panelContenidoLayout);
        panelContenidoLayout.setHorizontalGroup(
            panelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 622, Short.MAX_VALUE)
        );
        panelContenidoLayout.setVerticalGroup(
            panelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        // Configuración del CardLayout y adición de los paneles de acceso y registro
        panelContenido.setLayout(cardLayout);
        panelContenido.add(new Acceso(), "acceso");
        panelContenido.add(new Registro(), "registro");
        cardLayout.show(panelContenido, "acceso");

        // Layout principal de la ventana
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Acción al presionar el botón "Acceder".
     * Cambia el color de fondo de los botones y muestra el formulario de acceso.
     */
    private void botonAccederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAccederActionPerformed
        botonAcceder.setBackground(new Color(239, 55, 67));
        botonRegistrarse.setBackground(new Color(30, 30, 30));
        cardLayout.show(panelContenido, "acceso");
    }//GEN-LAST:event_botonAccederActionPerformed

    /**
     * Acción al presionar el botón "Registrarse".
     * Cambia el color de fondo de los botones y muestra el formulario de registro.
     */
    private void botonRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarseActionPerformed
        botonRegistrarse.setBackground(new Color(239, 55, 67));
        botonAcceder.setBackground(new Color(30, 30, 30));
        cardLayout.show(panelContenido, "registro");
    }//GEN-LAST:event_botonRegistrarseActionPerformed

    /**
     * Acción al presionar el botón de acceso a empleados/administradores.
     * Cierra la ventana actual y abre la ventana principal del módulo de empleados.
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MainAdmin mainAdmin = new MainAdmin();
        this.dispose();
        mainAdmin.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Método principal para ejecutar la ventana de inicio de UPEEats.
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
                new Main().setVisible(true);
            }
        });
    }

    // Declaración de variables de los componentes gráficos
    private javax.swing.JButton botonAcceder;
    private javax.swing.JButton botonRegistrarse;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelLogo;
    // End of variables declaration//GEN-END:variables
}
