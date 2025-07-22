package org.upemor.models.entities;

import java.sql.Timestamp;
import java.util.List;

import org.upemor.models.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase Productos
 * Representa un producto disponible en la plataforma UPEEats.
 * Contiene información como nombre, imagen, descripción, precio, tiempo de preparación, disponibilidad, fecha de registro y categorías asociadas.
 */
@Getter
@Setter
public class Productos extends Entity {
    // Nombre del producto
    private String nombreProducto;
    // URL de la imagen del producto
    private String imagenUrl;
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
     * @param imagenUrl         URL de la imagen del producto.
     * @param descripcion       Descripción del producto.
     * @param precio            Precio del producto.
     * @param tiempoPreparacion Tiempo estimado de preparación.
     * @param disponible        Disponibilidad del producto.
     * @param fechaRegistro     Fecha de registro del producto.
     * @param categorias        Categorías asociadas al producto.
     */
    public Productos(long id, String nombreProducto, String imagenUrl, String descripcion, double precio, String tiempoPreparacion, boolean disponible, Timestamp fechaRegistro, List<Categorias> categorias) {
        super(id);
        this.nombreProducto = nombreProducto;
        this.imagenUrl = imagenUrl;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tiempoPreparacion = tiempoPreparacion;
        this.disponible = disponible;
        this.fechaRegistro = fechaRegistro;
        this.categorias = categorias;
    }
}
