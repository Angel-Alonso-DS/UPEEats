package org.upemor.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.upemor.models.base.Entidad;
import org.upemor.widgets.ItemEntidad;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase Productos
 * Representa un producto disponible en la plataforma UPEEats.
 * Contiene información como nombre, imagen, descripción, precio, tiempo de preparación, disponibilidad, fecha de registro y categorías asociadas.
 */
@Getter
@Setter

public class Productos extends Entidad{
    // Nombre del producto
    private String nombreProducto;
    // Ruta de la imagen del producto (relativa a la carpeta de imágenes)
    private String imagen;
    // Descripción del producto
    private String descripcion;
    // Precio del producto
    private double precio;
    // Tiempo estimado de preparación del producto
    private String tiempoPreparacion;
    // Indica si el producto está disponible para la venta
    private boolean disponible;
    // Fecha en que el producto fue registrado en la plataforma
    private Timestamp fechaRegistro;
    // Lista de categorías a las que pertenece el producto
    private List<Categorias> categorias;
    
    /**
     * Constructor de la clase Productos.
     * Inicializa un nuevo producto con toda la información relevante.
     *
     * @param id                Identificador único del producto.
     * @param nombreProducto    Nombre del producto.
     * @param imagen            Imagen del producto como arreglo de bytes (BLOB).
     * @param descripcion       Descripción del producto.
     * @param precio            Precio del producto.
     * @param tiempoPreparacion Tiempo estimado de preparación.
     * @param disponible        Disponibilidad del producto.
     * @param fechaRegistro     Fecha de registro del producto.
     * @param categorias        Categorías asociadas al producto.
     */
    public Productos(long id, String nombreProducto, String imagen, String descripcion, double precio, String tiempoPreparacion, boolean disponible, Timestamp fechaRegistro, List<Categorias> categorias) {
        super(id);
        this.nombreProducto = nombreProducto;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tiempoPreparacion = tiempoPreparacion;
        this.disponible = disponible;
        this.fechaRegistro = fechaRegistro;
        this.categorias = categorias;
        tipoEntidad = "Productos";
    }
    /**
     * @return Devuelve los nombres de las categoras almacenadas
     */
    public String getNombresCategorias() {
        String nombres = "";
        
        for (Categorias c : categorias) nombres +=  "| " + c.getNombre() + " | ";
        
        return nombres;
    }

    public List<Long> getIdCategorias() {
        List<Long> idCategorias = new ArrayList<>();
        for (Categorias c : categorias) idCategorias.add(c.getId());

        return idCategorias;
    }

    @Override
    public String[][] toInfo() {
        return new String[][] {
            {ItemEntidad.TITULO, "Nombre", nombreProducto},
            {ItemEntidad.IMAGEN, "Imagen", imagen != null ? imagen : ""},
            {ItemEntidad.CONTENIDO, "Descripcion", descripcion},
            {ItemEntidad.PRECIO, "Precio", precio + ""},
            {ItemEntidad.TIEMPO, "Tiempo preparacion", tiempoPreparacion},
            {ItemEntidad.SUBTITULO, "Disponible", disponible ? "Disponible" : "No disponible"},
            {ItemEntidad.CATEGORIAS, "Categorias", getNombresCategorias()},
        };
    }
}
