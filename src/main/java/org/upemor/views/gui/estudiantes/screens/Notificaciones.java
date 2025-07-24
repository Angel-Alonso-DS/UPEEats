/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
/*
 * Clase Notificaciones
 * Panel gráfico que muestra las notificaciones para estudiantes en la interfaz de UPEEats.
 * Este panel puede personalizarse para mostrar mensajes, avisos o alertas relevantes al usuario autenticado.
 */

package org.upemor.views.gui.estudiantes.screens;

import org.upemor.models.entities.Usuarios;

/**
 * Panel de notificaciones para estudiantes.
 * Muestra un encabezado y un área donde se pueden desplegar las notificaciones del usuario.
 */
public class Notificaciones extends javax.swing.JPanel {

    /**
     * Constructor por defecto.
     * Inicializa los componentes gráficos del panel sin datos de usuario.
     */
    public Notificaciones() {
        initComponents();
    }

    /**
     * Constructor que recibe el usuario autenticado.
     * Inicializa los componentes gráficos y permite personalizar la vista según el usuario.
     * @param usuario Usuario autenticado (actualmente no se usa, pero puede usarse para personalizar el panel).
     */
    public Notificaciones(Usuarios usuario) {
        initComponents();
        // Aquí puedes personalizar el panel según el usuario si lo deseas.
    }

    /**
     * Inicializa y configura todos los componentes gráficos del panel.
     * Este método es generado automáticamente por el editor de formularios.
     * No modificar manualmente.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(782, 720));
        setMinimumSize(new java.awt.Dimension(782, 720));
        setPreferredSize(new java.awt.Dimension(782, 720));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Notificaciones");

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 584, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Declaración de variables de los componentes gráficos
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
