/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.upemor.views.forms;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.upemor.controllers.UsuariosControlador;
import org.upemor.models.Usuarios;
import org.upemor.utils.NotificadorGlobal;

/**
 * Panel que muestra la información detallada de un empleado/usuario,
 * permitiendo editar su estado (activar/desactivar), eliminarlo o cerrar el diálogo.
 * 
 * @author Admin
 */
public class VistaEmpleados extends javax.swing.JPanel {
    private JDialog dialogo;                 // Diálogo que contiene este panel
    private UsuariosControlador usuariosCTR; // Controlador para acciones sobre usuarios
    private Usuarios usuario;                // Usuario actual mostrado en la vista
    
    /**
     * Constructor que inicializa la vista con los datos del usuario y los controladores.
     * 
     * @param dialogo Diálogo donde se muestra esta vista
     * @param usuario Usuario cuyos datos se mostrarán
     */
    public VistaEmpleados(JDialog dialogo, Usuarios usuario) {
        initComponents();
        this.dialogo = dialogo;
        this.usuario = usuario;
        
        usuariosCTR = new UsuariosControlador();

        // Mostrar datos del usuario en los labels
        textoId.setText(String.valueOf(usuario.getId()));
        textoRol.setText(usuario.getRol());
        textoNombre.setText(usuario.getNombre() + " " + usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno());
        textoCorreo.setText(usuario.getCorreo());
        textoTelefono.setText(usuario.getTelefono());
        // Mostrar matrícula sólo si el rol es estudiante
        textoMatricula.setText(usuario.getRol().equalsIgnoreCase("estudiante") ? usuario.getMatricula() : "");
        textofecha.setText(String.valueOf(usuario.getFechaRegistro()));
        textoEstado.setText(usuario.isActivo() ? "Activo" : "Desactivado");
        bttnCambio.setText(usuario.isActivo() ? "Desactivar" : "Activar");
    }

    /**
     * Método generado por el editor visual para inicializar los componentes Swing.
     * No modificar manualmente para evitar conflictos con el editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textoRol = new javax.swing.JLabel();
        textoNombre = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textoCorreo = new javax.swing.JLabel();
        textoTelefono = new javax.swing.JLabel();
        textoMatricula = new javax.swing.JLabel();
        textofecha = new javax.swing.JLabel();
        textoId = new javax.swing.JLabel();
        bttnCerrar = new javax.swing.JButton();
        bttnCambio = new javax.swing.JButton();
        textoEstado = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(600, 500));
        setMinimumSize(new java.awt.Dimension(600, 500));
        setPreferredSize(new java.awt.Dimension(600, 500));

        textoRol.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        textoRol.setText("[Rol]");

        textoNombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textoNombre.setText("[Nombre completo]");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Contacto");

        textoCorreo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textoCorreo.setText("[Correo]");

        textoTelefono.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textoTelefono.setText("[Telefono]");

        textoMatricula.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textoMatricula.setText("[Matricula]");

        textofecha.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        textofecha.setText("[Fecha]");

        textoId.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        textoId.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        textoId.setText("[id]");

        bttnCerrar.setBackground(new java.awt.Color(51, 51, 51));
        bttnCerrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bttnCerrar.setText("Cerrar");
        bttnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnCerrarActionPerformed(evt);
            }
        });

        bttnCambio.setBackground(new java.awt.Color(239, 55, 67));
        bttnCambio.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bttnCambio.setForeground(new java.awt.Color(255, 255, 255));
        bttnCambio.setText("[Cambio]");
        bttnCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnCambioActionPerformed(evt);
            }
        });

        textoEstado.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textoEstado.setText("[Estado]");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bttnCerrar)
                        .addGap(418, 418, 418))
                    .addComponent(textofecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoMatricula, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoTelefono, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoCorreo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(textoRol, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textoId, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(textoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bttnCambio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoRol)
                    .addComponent(textoId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textoNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textoMatricula)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textoCorreo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textoTelefono)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bttnCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(textofecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(bttnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Acción del botón Cerrar.
     * Simplemente oculta el diálogo.
     */
    private void bttnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnCerrarActionPerformed
        dialogo.setVisible(false);
    }//GEN-LAST:event_bttnCerrarActionPerformed

    /**
     * Acción del botón para activar/desactivar usuario.
     * Pregunta confirmación y realiza la acción.
     */
    private void bttnCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnCambioActionPerformed
        String mensaje = "Deseas " + (usuario.isActivo() ? "desactivar " : "activar ") + "a este usuario";
        
        int OK = JOptionPane.showConfirmDialog(
            SwingUtilities.getWindowAncestor(this),
            mensaje,
            "Cambiar estado del usuario",
            JOptionPane.YES_NO_OPTION
        );

        if (OK == JOptionPane.YES_OPTION) {
            dialogo.setVisible(false);
            
            try {
                boolean res = !usuario.isActivo() ? usuariosCTR.aprobarUsuario(usuario.getId()) : usuariosCTR.desactivarUsuario(usuario.getId());
                
                if (!res) {
                    JOptionPane.showMessageDialog(this, "No se pudo realizar el cambio", "Error al actualizar", JOptionPane.CLOSED_OPTION);
                    return;
                }

                NotificadorGlobal.getInstancia().notificarCambio("actualizacion lista Usuarios", new Object[0]);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al realizar accion " + e.getMessage());
            }

        }
    }//GEN-LAST:event_bttnCambioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bttnCambio;
    private javax.swing.JButton bttnCerrar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel textoCorreo;
    private javax.swing.JLabel textoEstado;
    private javax.swing.JLabel textoId;
    private javax.swing.JLabel textoMatricula;
    private javax.swing.JLabel textoNombre;
    private javax.swing.JLabel textoRol;
    private javax.swing.JLabel textoTelefono;
    private javax.swing.JLabel textofecha;
    // End of variables declaration//GEN-END:variables
}
