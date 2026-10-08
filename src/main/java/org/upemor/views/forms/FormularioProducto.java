// Métodos de validación se declaran dentro de la clase FormularioProducto
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.upemor.views.forms;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AbstractDocument;

import org.upemor.controllers.CategoriasControlador;
import org.upemor.controllers.ProductosControlador;
import org.upemor.models.Categorias;
import org.upemor.models.Productos;
import org.upemor.theme.Colores;
import org.upemor.utils.FiltroEntradas;
import org.upemor.utils.NotificadorGlobal;
import org.upemor.utils.Receptor;
import org.upemor.utils.Validadores;
import java.nio.file.Files;

/**
 *
 * @author Admin
 */

public class FormularioProducto extends javax.swing.JPanel implements Receptor{
    private ProductosControlador productosCTR;
    private CategoriasControlador categoriasCTR;
    private JDialog dialogo;
    private Productos producto;
    private String imagenRuta = null;
    
    /**
     * Valida y formatea el campo de precio: solo números y un punto decimal, "0" si vacío.
     * No permite más de un punto.
     */
    private void validarCampoPrecio() {
        String texto = campoPrecio.getText().replaceAll("[^0-9.]", "");
        // Permitir solo un punto decimal
        int primerPunto = texto.indexOf('.');
        if (primerPunto != -1) {
            // Eliminar puntos adicionales
            String antes = texto.substring(0, primerPunto + 1);
            String despues = texto.substring(primerPunto + 1).replaceAll("[.]", "");
            texto = antes + despues;
        }
        if (texto.isEmpty()) texto = "0";
        campoPrecio.setText(texto);
    }

    /**
     * Valida y formatea un campo de hora/min/seg: solo números, "00" si vacío, 2 dígitos, y máximo permitido.
     * @param campo JTextField a validar
     * @param maxValor valor máximo permitido (23 para hr, 59 para min/seg)
     */
    private void validarCampoTiempo(javax.swing.JTextField campo, int maxValor) {
        String texto = campo.getText().replaceAll("[^0-9]", "");
        if (texto.isEmpty()) texto = "00";
        else {
            int valor = Integer.parseInt(texto);
            if (valor > maxValor) valor = maxValor;
            texto = String.format("%02d", valor);
        }
        campo.setText(texto);
    }

    /**
     * Creates new form FormularioProducto
     */
    public FormularioProducto() {
        init();
        bttnCancelar.setVisible(false);
        cargarVista();
    }

    public FormularioProducto(JDialog dialogo, Productos producto) {
        this.dialogo = dialogo;
        this.producto = producto;
        init();
        this.imagenRuta = producto.getImagen();
        init();

        bttnAgregar.setText("");
        bttnAgregar.setIcon(new ImageIcon(getClass().getResource("/icons/pen-to-square.png")));
        bttnAgregar.setBackground(Colores.SECUNDARIO);

        campoNombre.setText(producto.getNombreProducto());
        campoDescripcion.setText(producto.getDescripcion());
        campoPrecio.setText(producto.getPrecio() + "");
        boxActivo.setSelected(producto.isDisponible());
        cargarImagenDesdeRuta(imagenRuta);

        String[] tiempoPreparacion = producto.getTiempoPreparacion().split(":");
        campoHr.setText(tiempoPreparacion[0]);
        campoMin.setText(tiempoPreparacion[1]);
        campoSeg.setText(tiempoPreparacion[2]);
        panelListaEntidades.setListaSeleccionId(producto.getIdCategorias());
        cargarVista();
    }
    
    public void init() {
        NotificadorGlobal.getInstancia().registrar(this);
        categoriasCTR = new CategoriasControlador();
        productosCTR = new ProductosControlador();
        initComponents();
        aplicarFiltro();
        panelListaEntidades.setModo(panelListaEntidades.MODO_SELECCION);

        campoPrecio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                validarCampoPrecio();
            }
        });
        campoHr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                validarCampoTiempo(campoHr, 23);
            }
        });
        campoMin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                validarCampoTiempo(campoMin, 59);
            }
        });
        campoSeg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                validarCampoTiempo(campoSeg, 59);
            }
        });
    }

    private void aplicarFiltro() {
        AbstractDocument docCampoNombre = (AbstractDocument) campoNombre.getDocument();
        AbstractDocument docCampoPrecio = (AbstractDocument) campoPrecio.getDocument();
        AbstractDocument docCampoHr = (AbstractDocument) campoHr.getDocument();
        AbstractDocument docCampoMin = (AbstractDocument) campoMin.getDocument();
        AbstractDocument docCampoSeg = (AbstractDocument) campoSeg.getDocument();

        docCampoNombre.setDocumentFilter(new FiltroEntradas(50, FiltroEntradas.NO_SQL));
        docCampoPrecio.setDocumentFilter(new FiltroEntradas(6, FiltroEntradas.SOLO_DECIMALES));
        docCampoHr.setDocumentFilter(new FiltroEntradas(2, FiltroEntradas.SOLO_NUMEROS));
        docCampoMin.setDocumentFilter(new FiltroEntradas(2, FiltroEntradas.SOLO_NUMEROS));
        docCampoSeg.setDocumentFilter(new FiltroEntradas(2, FiltroEntradas.SOLO_NUMEROS));
        
    }

    public void cargarVista() {
        panelListaEntidades.actualizarLista(categoriasCTR.obtenerTodas());
    }


    /**
     * Carga una imagen desde una ruta relativa y la muestra en el botón.
     * @param rutaImagen Ruta relativa de la imagen (puede ser null)
     */
    private void cargarImagenDesdeRuta(String rutaImagen) {
        if (rutaImagen == null || rutaImagen.isEmpty()) {
            bttnImagen.setIcon(null);
            return;
        }
        try {
            File imgFile = new File("img/" + rutaImagen);
            if (!imgFile.exists()) {
                bttnImagen.setIcon(null);
                return;
            }
            ImageIcon icono = new ImageIcon(imgFile.getAbsolutePath());
            int ancho = bttnImagen.getWidth() > 0 ? bttnImagen.getWidth() : 188;
            int alto = bttnImagen.getHeight() > 0 ? bttnImagen.getHeight() : 180;
            Image imagenEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            bttnImagen.setIcon(new ImageIcon(imagenEscalada));
        } catch (Exception e) {
            bttnImagen.setText("Error al cargar imagen");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campoNombre = new javax.swing.JTextField();
        bttnAgregar = new javax.swing.JButton();
        bttnImagen = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoDescripcion = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        campoPrecio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        campoHr = new javax.swing.JTextField();
        campoMin = new javax.swing.JTextField();
        campoSeg = new javax.swing.JTextField();
        panelListaEntidades = new org.upemor.widgets.PanelListaEntidades<Categorias>();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        campoBusqueda = new javax.swing.JTextField();
        bttnBusqueda = new javax.swing.JButton();
        boxActivo = new javax.swing.JCheckBox();
        bttnCancelar = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(3000, 3000));
        setMinimumSize(new java.awt.Dimension(450, 600));
        setPreferredSize(new java.awt.Dimension(450, 600));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Agregar producto");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Nombre");

        campoNombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        bttnAgregar.setBackground(new java.awt.Color(51, 51, 51));
        bttnAgregar.setText("+");
        bttnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnAgregarActionPerformed(evt);
            }
        });

        bttnImagen.setBackground(new java.awt.Color(51, 51, 51));
        bttnImagen.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bttnImagen.setForeground(new java.awt.Color(255, 255, 255));
        bttnImagen.setText("Seleccionar Imagen");
        bttnImagen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttnImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnImagenActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Descripcion");

        campoDescripcion.setColumns(20);
        campoDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoDescripcion.setLineWrap(true);
        campoDescripcion.setRows(5);
        jScrollPane1.setViewportView(campoDescripcion);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Precio");

        campoPrecio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoPrecio.setText("0");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Tiempo preparacion");

        campoHr.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoHr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoHr.setText("00");
        campoHr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoHrKeyTyped(evt);
            }
        });

        campoMin.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoMin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoMin.setText("00");

        campoSeg.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoSeg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoSeg.setText("00");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Categorias");

        campoBusqueda.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoBusqueda.setToolTipText("");

        bttnBusqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search-icon.png"))); // NOI18N
        bttnBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnBusquedaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bttnBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bttnBusqueda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(campoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        boxActivo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        boxActivo.setSelected(true);
        boxActivo.setText("Activo");
        boxActivo.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        bttnCancelar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bttnCancelar.setText("Cancelar");
        bttnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bttnCancelar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bttnImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(boxActivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(campoNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bttnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(campoPrecio)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(campoHr, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoMin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelListaEntidades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bttnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(boxActivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bttnImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelListaEntidades, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoHr, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoMin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(bttnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bttnBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnBusquedaActionPerformed
        panelListaEntidades.actualizarLista(categoriasCTR.buscarPorNombre(campoBusqueda.getText()));
    }//GEN-LAST:event_bttnBusquedaActionPerformed

    private void bttnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnAgregarActionPerformed
        try {
            // Validar y formatear campos antes de procesar
            validarCampoPrecio();
            validarCampoTiempo(campoHr, 23);
            validarCampoTiempo(campoMin, 59);
            validarCampoTiempo(campoSeg, 59);

            String nombre = campoNombre.getText();
            String descripcion = campoDescripcion.getText();
            String tiempoPreparacion = campoHr.getText() + ":" + campoMin.getText() + ":" + campoSeg.getText();

            List<Long> categoriasId = panelListaEntidades.obtenerSeleccionesId();
            List<Categorias> categorias = new ArrayList<>();
            for (Long id : categoriasId) categorias.add(categoriasCTR.buscarPorId(id));

            String precioTexto = campoPrecio.getText();
            double precio = precioTexto.isEmpty() ? 0 : Double.parseDouble(precioTexto);

            Validadores.validarNombre(nombre);
            Validadores.validarTexto(descripcion);
            Validadores.validarCosto(precio);
            Validadores.validarTiempo(tiempoPreparacion);
            // Validación de imagen ya se hace al seleccionar/cargar imagen

            if (producto != null){
                productosCTR.actualizar(producto.getId(), nombre, imagenRuta, descripcion, precio, tiempoPreparacion, getFocusTraversalKeysEnabled(), categorias);
                if (dialogo != null) dialogo.setVisible(false);
            } else {
                productosCTR.insertar(nombre, imagenRuta, descripcion, precio, tiempoPreparacion, true, categorias);
            }

            NotificadorGlobal.getInstancia().notificarCambio("agregar Productos", new Object[0]);

            campoNombre.setText("");
            campoDescripcion.setText("");
            campoHr.setText("00");
            campoMin.setText("00");
            campoSeg.setText("00");
            campoPrecio.setText("0");
            campoBusqueda.setText("");
            bttnImagen.setIcon(null);
            imagenRuta = null;
            panelListaEntidades.setListaSeleccionId(new ArrayList<Long>());
            cargarVista();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Campo invalido", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_bttnAgregarActionPerformed

    private void bttnImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnImagenActionPerformed
        // Configura el selector de archivos con filtro para imágenes
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG", "jpg", "png");
        fileChooser.setFileFilter(filter);
        
        // Muestra el diálogo de selección
        int res = fileChooser.showOpenDialog(this);
        
        // Si el usuario seleccionó un archivo
        if (res == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try {
                byte[] bytes = Files.readAllBytes(archivo.toPath());
                Validadores.validarImagenBytes(bytes);
                // Copiar imagen a carpeta img/ con nombre único
                String nombreUnico = System.currentTimeMillis() + "_" + archivo.getName();
                File destino = new File("img/" + nombreUnico);
                Files.copy(archivo.toPath(), destino.toPath());
                imagenRuta = nombreUnico;
                // Mostrar imagen
                ImageIcon icono = new ImageIcon(destino.getAbsolutePath());
                Image img = icono.getImage();
                ImageIcon icon = new ImageIcon(img.getScaledInstance(bttnImagen.getWidth(), bttnImagen.getHeight(), Image.SCALE_SMOOTH));
                bttnImagen.setIcon(icon);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No se pudo cargar la imagen: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_bttnImagenActionPerformed

    private void campoHrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoHrKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_campoHrKeyTyped

    private void bttnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnCancelarActionPerformed
        if (dialogo != null) dialogo.setVisible(false);
    }//GEN-LAST:event_bttnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox boxActivo;
    private javax.swing.JButton bttnAgregar;
    private javax.swing.JButton bttnBusqueda;
    private javax.swing.JButton bttnCancelar;
    private javax.swing.JButton bttnImagen;
    private javax.swing.JTextField campoBusqueda;
    private javax.swing.JTextArea campoDescripcion;
    private javax.swing.JTextField campoHr;
    private javax.swing.JTextField campoMin;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JTextField campoPrecio;
    private javax.swing.JTextField campoSeg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.upemor.widgets.PanelListaEntidades<Categorias> panelListaEntidades;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onReceptor(String msg, Object[] objetos) {
        if (msg.equals("actualizacion Categorias")) {
            cargarVista();
        }
    }
}
