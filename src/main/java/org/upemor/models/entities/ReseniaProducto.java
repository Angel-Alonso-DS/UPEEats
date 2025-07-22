package org.upemor.models.entities;

import java.sql.Timestamp;

import org.upemor.models.Entity;
import lombok.Getter;

/**
 * Clase ReseniaProducto
 * Representa una reseña realizada por un usuario sobre un producto en la plataforma UPEEats.
 * Contiene información sobre el usuario, el producto, la calificación, el comentario y la fecha de la reseña.
 */
@Getter
public class ReseniaProducto extends Entity {
    // Usuario que realizó la reseña
    private Usuarios usuario;
    // Producto al que corresponde la reseña
    private Productos producto;
    // Calificación otorgada al producto (por ejemplo: "5 estrellas")
    private String calificacion;
    // Comentario adicional sobre el producto
    private String comentario;
    // Fecha en que se realizó la reseña
    private Timestamp fechaResenia;
    
    /**
     * Obtiene el usuario que realizó la reseña.
     * @return usuario que realizó la reseña.
     */
    public Usuarios getUsuario() {return usuario;}
    
    /**
     * Obtiene el producto reseñado.
     * @return producto reseñado.
     */
    public Productos getProducto() {return producto;}
    
    /**
     * Obtiene la calificación otorgada.
     * @return calificación del producto.
     */
    public String getCalificacion() {return calificacion;}
    
    /**
     * Obtiene el comentario de la reseña.
     * @return comentario de la reseña.
     */
    public String getComentario() {return comentario;}
    
    /**
     * Obtiene la fecha en que se realizó la reseña.
     * @return fecha de la reseña.
     */
    public Timestamp getFechaResenia() {return fechaResenia;}
    
    /**
     * Constructor de la clase ReseniaProducto.
     * Inicializa una nueva reseña con toda la información relevante.
     *
     * @param newId         Identificador único de la reseña.
     * @param usuario       Usuario que realiza la reseña.
     * @param producto      Producto reseñado.
     * @param calificacion  Calificación otorgada.
     * @param comentario    Comentario adicional.
     * @param fechaResenia  Fecha de la reseña.
     */
    public ReseniaProducto(long newId, Usuarios usuario, Productos producto, String calificacion, String comentario,
            Timestamp fechaResenia) {
        super(newId);
        this.usuario = usuario;
        this.producto = producto;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fechaResenia = fechaResenia;
    }
}
