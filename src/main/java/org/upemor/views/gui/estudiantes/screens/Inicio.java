/*
 * Clase Inicio
 * Panel gráfico de bienvenida para estudiantes en la interfaz de UPEEats.
 * Puede personalizarse para mostrar información relevante al usuario autenticado.
 */

package org.upemor.views.gui.estudiantes.screens;

import org.upemor.models.entities.Usuarios;

/**
 * Panel de inicio para estudiantes.
 * Muestra un mensaje de bienvenida y puede ser personalizado para mostrar información relevante.
 */
public class Inicio extends javax.swing.JPanel {

    /**
     * Constructor por defecto.
     * Inicializa los componentes gráficos del panel sin datos de usuario.
     */
    public Inicio() {
        initComponents();
    }

    /**
     * Constructor que recibe el usuario autenticado.
     * Inicializa los componentes gráficos y permite personalizar la vista según el usuario.
     * @param usuario Usuario autenticado (actualmente no se usa, pero puede usarse para personalizar el panel).
     */
    public Inicio(Usuarios usuario) {
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(782, 720));
        setMinimumSize(new java.awt.Dimension(782, 720));
        setPreferredSize(new java.awt.Dimension(782, 720));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("La Isla");

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 214, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(412, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Declaración de variables de los componentes gráficos
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
}
