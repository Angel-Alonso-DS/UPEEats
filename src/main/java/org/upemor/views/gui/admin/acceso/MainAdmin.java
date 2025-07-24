/*
 * Clase MainAdmin
 * Ventana principal para el acceso y registro de empleados en la interfaz gráfica de administración de UPEEats.
 * Permite alternar entre las pantallas de acceso y registro de empleados usando un CardLayout.
 * Incluye botones para cambiar de pantalla y regresar al menú principal.
 */

package org.upemor.views.gui.admin.acceso;

import javax.swing.*;
import java.awt.*;

import org.upemor.views.gui.admin.acceso.screens.AccesoAdmin;
import org.upemor.views.gui.admin.acceso.screens.RegistroAdmin;
import org.upemor.views.gui.main.Main;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

public class MainAdmin extends javax.swing.JFrame {
    // CardLayout para alternar entre las vistas de acceso y registro
    private CardLayout cardLayout;

    /**
     * Constructor de la clase MainAdmin.
     * Inicializa los componentes de la interfaz gráfica.
     */
    public MainAdmin() {
        initComponents();
    }

    /**
     * Inicializa y configura todos los componentes gráficos de la ventana.
     * Este método es generado automáticamente por el editor de formularios.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        panelLogo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        botonAcceder = new javax.swing.JButton();
        botonRegistrarse = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        panelContenido = new javax.swing.JPanel();
        cardLayout = new CardLayout();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1028, 720));
        setMinimumSize(new java.awt.Dimension(1028, 720));
        setResizable(false);

        panelLogo.setBackground(new java.awt.Color(51, 51, 51));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/upemor/assets/LOGO_U4.png"))); // Logo de la universidad

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // Título "Empleados"
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Empleados");

        botonAcceder.setBackground(new java.awt.Color(239, 55, 67));
        botonAcceder.setFont(new java.awt.Font("Segoe UI", 1, 18));
        botonAcceder.setForeground(new java.awt.Color(255, 255, 255));
        botonAcceder.setText("Acceder");
        // Acción para mostrar la pantalla de acceso
        botonAcceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAccederActionPerformed(evt);
            }
        });

        botonRegistrarse.setBackground(new java.awt.Color(30, 30, 30));
        botonRegistrarse.setFont(new java.awt.Font("Segoe UI", 1, 18));
        botonRegistrarse.setForeground(new java.awt.Color(255, 255, 255));
        botonRegistrarse.setText("Registrarse");
        botonRegistrarse.setToolTipText("");
        // Acción para mostrar la pantalla de registro
        botonRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarseActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // Título "UPEEats"
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("UPEEats");

        jButton1.setBackground(new java.awt.Color(30, 30, 30));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/upemor/assets/icons/user-tie-solid-full.png"))); // Icono de usuario
        // Acción para regresar al menú principal
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        // Layout del panel del logo y botones
        javax.swing.GroupLayout panelLogoLayout = new javax.swing.GroupLayout(panelLogo);
        panelLogo.setLayout(panelLogoLayout);
        panelLogoLayout.setHorizontalGroup(
            panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogoLayout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addGroup(panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonAcceder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonRegistrarse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLogoLayout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addGroup(panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLogoLayout.createSequentialGroup()
                        .addGroup(panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(118, 118, 118))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLogoLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(107, 107, 107))))
            .addGroup(panelLogoLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLogoLayout.setVerticalGroup(
            panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogoLayout.createSequentialGroup()
                .addContainerGap(178, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addComponent(botonAcceder, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94)
                .addComponent(jButton1)
                .addGap(33, 33, 33))
        );

        // Layout del panel de contenido central (pantallas de acceso y registro)
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

        // Configuración del CardLayout y adición de las pantallas
        panelContenido.setLayout(cardLayout);
        panelContenido.add(new AccesoAdmin(), "acceso");
        panelContenido.add(new RegistroAdmin(), "registro");

        // Mostrar pantalla de acceso por defecto
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
    }

    /**
     * Acción del botón "Acceder".
     * Cambia el color de fondo de los botones y muestra la pantalla de acceso.
     */
    private void botonAccederActionPerformed(java.awt.event.ActionEvent evt) {
        botonAcceder.setBackground(new Color(239, 55, 67));
        botonRegistrarse.setBackground(new Color(30, 30, 30));
        cardLayout.show(panelContenido, "acceso");
    }

    /**
     * Acción del botón "Registrarse".
     * Cambia el color de fondo de los botones y muestra la pantalla de registro.
     */
    private void botonRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {
        botonRegistrarse.setBackground(new Color(239, 55, 67));
        botonAcceder.setBackground(new Color(30, 30, 30));
        cardLayout.show(panelContenido, "registro");
    }

    /**
     * Acción del botón de regreso al menú principal.
     * Cierra la ventana actual y abre la ventana principal de la aplicación.
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        Main main = new Main();
        this.dispose();
        main.setVisible(true);
    }

    /**
     * Método principal para ejecutar la ventana de administración.
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
                new MainAdmin().setVisible(true);
            }
        });
    }

    // Declaración de variables de la interfaz gráfica
    private javax.swing.JButton botonAcceder;
    private javax.swing.JButton botonRegistrarse;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelLogo;
    // End of variables declaration//GEN-END:variables
}
