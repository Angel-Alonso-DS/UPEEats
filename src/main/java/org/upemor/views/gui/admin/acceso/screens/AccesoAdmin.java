/*
 * Clase AccesoAdmin
 * Panel de acceso para empleados y administradores en la interfaz gráfica de UPEEats.
 * Permite ingresar el correo y la contraseña, valida los datos y realiza la autenticación.
 * Si el acceso es exitoso, abre el panel principal de administración.
 */

package org.upemor.views.gui.admin.acceso.screens;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.upemor.controllers.SesionControlador;
import org.upemor.models.entities.Usuarios;
import org.upemor.utils.Validadores;
import org.upemor.views.gui.admin.dashboard.HomeAdmin;
import org.upemor.views.gui.estudiantes.Home;

public class AccesoAdmin extends javax.swing.JPanel {
    // Usuario autenticado (no siempre se usa directamente)
    private Usuarios usuario;
    // Controlador de sesión para autenticación de empleados/administradores
    private SesionControlador sesionControlador;

    /**
     * Constructor del panel de acceso.
     * Inicializa los componentes gráficos.
     */
    public AccesoAdmin() {
        initComponents();
    }

    /**
     * Inicializa y configura todos los componentes gráficos del formulario.
     * Este método es generado automáticamente por el editor de formularios.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campoCorreo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        campoContrasenia = new javax.swing.JPasswordField();
        botonEntrar = new javax.swing.JButton();
        sesionControlador = new SesionControlador();

        setMaximumSize(new java.awt.Dimension(622, 720));
        setMinimumSize(new java.awt.Dimension(622, 720));
        setPreferredSize(new java.awt.Dimension(622, 720));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Iniciar Sesion");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Correo");

        campoCorreo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCorreoActionPerformed(evt);
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

        // Layout del formulario
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoCorreo)
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
                .addComponent(campoCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
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
     * Acción al presionar Enter en el campo de correo.
     * (Actualmente no realiza ninguna acción extra).
     */
    private void campoCorreoActionPerformed(java.awt.event.ActionEvent evt) {
        // No implementado, reservado para futuras acciones si se requiere.
    }

    /**
     * Acción al presionar Enter en el campo de contraseña.
     * (Actualmente no realiza ninguna acción extra).
     */
    private void campoContraseniaActionPerformed(java.awt.event.ActionEvent evt) {
        // No implementado, reservado para futuras acciones si se requiere.
    }

    /**
     * Acción al presionar el botón "Entrar".
     * Valida los datos ingresados, realiza la autenticación y abre el panel principal de administración si es exitoso.
     * Si ocurre un error, muestra un mensaje al usuario.
     */
    private void botonEntrarActionPerformed(java.awt.event.ActionEvent evt) {
        String correo = campoCorreo.getText();
        String contrasenia = new String(campoContrasenia.getPassword());

        try {
            // Validar correo y contraseña
            Validadores.validarCorreo(correo);
            Validadores.validarContrasenia(contrasenia);

            // Intentar autenticación
            HomeAdmin home = new HomeAdmin(sesionControlador.accesoEmpleado(correo, contrasenia));
            home.setVisible(true);

            // Cerrar la ventana de acceso actual
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.dispose();
        } catch (Exception e) {
            // Mostrar mensaje de error si la autenticación falla
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    // Declaración de variables de los componentes gráficos
    private javax.swing.JButton botonEntrar;
    private javax.swing.JPasswordField campoContrasenia;
    private javax.swing.JTextField campoCorreo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
