/*
 * Clase Registro
 * Panel gráfico para el registro de estudiantes en la interfaz principal de UPEEats.
 * Permite capturar los datos personales y credenciales de un nuevo estudiante,
 * validarlos y enviarlos al controlador para su registro en la base de datos.
 */

package org.upemor.views.gui.main.pages;

import javax.swing.JOptionPane;

import org.upemor.controllers.SesionControlador;
import org.upemor.models.entities.Usuarios;
import org.upemor.utils.Validadores;

/**
 * Panel de registro para estudiantes.
 * Permite ingresar nombre, apellidos, matrícula, correo, teléfono y contraseña.
 * Realiza validaciones y muestra mensajes de éxito o error.
 */
public class Registro extends javax.swing.JPanel {
    // Usuario que se registra (no siempre se usa directamente)
    private Usuarios usuario;
    // Controlador de sesión para registrar estudiantes
    private SesionControlador sesionControlador;

    /**
     * Constructor del panel de registro.
     * Inicializa los componentes gráficos y el controlador de sesión.
     */
    public Registro() {
        initComponents();
        sesionControlador = new SesionControlador();
    }

    /**
     * Inicializa y configura todos los componentes gráficos del formulario.
     * Este método es generado automáticamente por el editor de formularios.
     * No modificar manualmente.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        campoNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        campoApellidoP = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        campoApellidoM = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        campoMatricula = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        campoCorreo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        campoTelefono = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        campoContrasenia = new javax.swing.JPasswordField();
        botonEntrar = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(616, 720));
        setPreferredSize(new java.awt.Dimension(616, 720));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Registrate");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Nombre");

        campoNombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Apellido paterno");

        campoApellidoP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Apellido materno");

        campoApellidoM.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Matricula");

        campoMatricula.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Correo");

        campoCorreo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Telefono");

        campoTelefono.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

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
        botonEntrar.setText("Registrarse");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(botonEntrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(campoContrasenia, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(campoCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(campoApellidoP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(campoApellidoM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(campoMatricula, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(campoNombre)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(campoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(108, 108, 108))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoApellidoP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoApellidoM, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Acción al presionar el botón "Registrarse".
     * Toma los datos del formulario, los valida y llama al controlador para registrar al estudiante.
     * Muestra mensajes de éxito o error según el resultado.
     */
    private void botonEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEntrarActionPerformed
        String nombre = campoNombre.getText();
        String apellidoP = campoApellidoP.getText();
        String apellidoM = campoApellidoM.getText();
        String matricula = campoMatricula.getText();
        String correo = campoCorreo.getText();
        String telefono = campoTelefono.getText();
        String contrasenia = new String(campoContrasenia.getPassword());
        
        try {
            // Validaciones de los campos ingresados
            Validadores.validarNombre(nombre);
            Validadores.validarApellido(apellidoP);
            Validadores.validarApellido(apellidoM);
            Validadores.validarMatricula(matricula);
            Validadores.validarCorreo(correo);
            Validadores.validarTelefono(telefono);
            Validadores.validarContrasenia(contrasenia);

            // Registro del estudiante
            boolean OK = sesionControlador.registrarEstudiante(nombre, apellidoP, apellidoM, correo, contrasenia, telefono, matricula);
            if (OK) {
                JOptionPane.showMessageDialog(this, "Registro exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                // Limpiar campos después de un registro exitoso
                campoNombre.setText("");
                campoApellidoP.setText("");
                campoApellidoM.setText("");
                campoMatricula.setText("");
                campoCorreo.setText("");
                campoTelefono.setText("");
                campoContrasenia.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar usuario", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            // Muestra el mensaje de error de validación o del controlador
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botonEntrarActionPerformed

    /**
     * Acción al presionar Enter en el campo de contraseña.
     * (Actualmente no realiza ninguna acción extra).
     */
    private void campoContraseniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoContraseniaActionPerformed
        // No implementado, reservado para futuras acciones si se requiere.
    }//GEN-LAST:event_campoContraseniaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEntrar;
    private javax.swing.JTextField campoApellidoM;
    private javax.swing.JTextField campoApellidoP;
    private javax.swing.JPasswordField campoContrasenia;
    private javax.swing.JTextField campoCorreo;
    private javax.swing.JTextField campoMatricula;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JTextField campoTelefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables
}
