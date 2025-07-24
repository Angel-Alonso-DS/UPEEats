/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.upemor.views.gui.admin.dashboard;

import org.upemor.models.entities.Usuarios;

/**
 * Ventana principal del panel de administración.
 * Muestra la interfaz gráfica base para las operaciones administrativas.
 */
public class HomeAdmin extends javax.swing.JFrame {

    /**
     * Constructor por defecto.
     * Inicializa los componentes gráficos de la ventana.
     */
    public HomeAdmin() {
        initComponents();
    }

    /**
     * Constructor que recibe el usuario autenticado.
     * Permite personalizar la vista según el usuario que accede.
     * @param usuario Usuario autenticado que accede al panel de administración.
     */
    public HomeAdmin(Usuarios usuario) {
        initComponents();
        // Aquí puedes personalizar la interfaz según el usuario si lo deseas.
    }

    /**
     * Inicializa y configura todos los componentes gráficos de la ventana.
     * Este método es generado automáticamente por el editor de formularios.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método principal para ejecutar la ventana de administración.
     * Configura el look and feel y muestra la ventana.
     */
    public static void main(String args[]) {
        /* Configura el look and feel de Nimbus si está disponible */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Crear y mostrar la ventana */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeAdmin().setVisible(true);
            }
        });
    }

    // Declaración de variables de la interfaz gráfica (agregar aquí si se agregan más componentes)
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
