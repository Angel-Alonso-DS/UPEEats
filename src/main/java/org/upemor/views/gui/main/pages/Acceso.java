/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.upemor.views.gui.main.pages;

import javax.swing.*;

import org.upemor.controllers.SesionControlador;
import org.upemor.utils.Validadores;
import org.upemor.views.gui.estudiantes.Home;

/**
 * Panel de acceso para estudiantes.
 * Permite iniciar sesión con matrícula y contraseña.
 */
public class Acceso extends javax.swing.JPanel {

    // Controlador de sesión para autenticación de estudiantes
    private SesionControlador sesionControlador;

    /**
     * Constructor por defecto.
     * Inicializa los componentes gráficos y el controlador de sesión.
     */
    public Acceso() {
        initComponents();
        sesionControlador = new SesionControlador();
    }

    /**
     * Inicializa y configura todos los componentes gráficos del panel.
     * Este método es generado automáticamente por el editor de formularios.
     * No modificar manualmente.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campoMatricula = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        campoContrasenia = new javax.swing.JPasswordField();
        botonEntrar = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(616, 720));
        setPreferredSize(new java.awt.Dimension(616, 720));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Iniciar Sesion");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Matricula");

        campoMatricula.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoMatriculaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Contraseña");

        campoContrasenia.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoContrasenia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoContraseniaActionPerformed(evt);
            }
        });

        botonEntrar.setBackground(new java.awt.Color(239, 55, 67));
        botonEntrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        botonEntrar.setForeground(new java.awt.Color(255, 255, 255));
        botonEntrar.setText("Entrar");
        // Acción al presionar el botón "Entrar"
        botonEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEntrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoMatricula)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoContrasenia)
                    .addComponent(botonEntrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(108, 108, 108))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel1)
                .addGap(92, 92, 92)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
                .addComponent(botonEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Acción al presionar Enter en el campo de contraseña.
     * (Actualmente no realiza ninguna acción extra).
     */
    private void campoContraseniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoContraseniaActionPerformed
        // No implementado, reservado para futuras acciones si se requiere.
    }//GEN-LAST:event_campoContraseniaActionPerformed

    /**
     * Acción al presionar el botón "Entrar".
     * Valida los datos ingresados, realiza la autenticación y abre la ventana principal del estudiante si es exitoso.
     * Si ocurre un error, muestra un mensaje al usuario.
     */
    private void botonEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEntrarActionPerformed
        String matricula = campoMatricula.getText();
        String contrasenia = new String(campoContrasenia.getPassword());
        
        try {
            // Validar matrícula y contraseña
            Validadores.validarMatricula(matricula);
            Validadores.validarContrasenia(contrasenia);

            // Intentar autenticación
            Home home = new Home(sesionControlador.accesoEstudiante(matricula, contrasenia));
            home.setVisible(true);

            // Cerrar la ventana de acceso actual
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.dispose();
        } catch (Exception e) {
            // Mostrar mensaje de error si la autenticación falla
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

    }//GEN-LAST:event_botonEntrarActionPerformed

    /**
     * Acción al presionar Enter en el campo de matrícula.
     * (Actualmente no realiza ninguna acción extra).
     */
    private void campoMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoMatriculaActionPerformed
        // No implementado, reservado para futuras acciones si se requiere.
    }//GEN-LAST:event_campoMatriculaActionPerformed

    // Declaración de variables de los componentes gráficos
    private javax.swing.JButton botonEntrar;
    private javax.swing.JPasswordField campoContrasenia;
    private javax.swing.JTextField campoMatricula;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
