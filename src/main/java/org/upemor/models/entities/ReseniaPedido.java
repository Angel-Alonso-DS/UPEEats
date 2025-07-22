package org.upemor.models.entities;

import java.sql.Timestamp;

import org.upemor.models.Entity;
import lombok.Getter;

/**
 * Clase ReseniaPedido
 * Representa una reseña realizada por un usuario sobre un pedido en la plataforma UPEEats.
 * Contiene información sobre el usuario, el pedido, la calificación, el comentario y la fecha de la reseña.
 */
@Getter
public class ReseniaPedido extends Entity {
    // Usuario que realizó la reseña
    private Usuarios usuario;
    // Pedido al que corresponde la reseña
    private Pedidos pedido;
    // Calificación otorgada al pedido (por ejemplo: "5 estrellas")
    private String calificacion;
    // Comentario adicional sobre el pedido
    private String comentario;
    // Fecha en que se realizó la reseña
    private Timestamp fechaResenia;
    
    /**
     * Obtiene el usuario que realizó la reseña.
     * @return usuario que realizó la reseña.
     */
    public Usuarios getUsuario() {return usuario;}
    
    /**
     * Obtiene el pedido reseñado.
     * @return pedido reseñado.
     */
    public Pedidos getPedido() {return pedido;}
    
    /**
     * Obtiene la calificación otorgada.
     * @return calificación del pedido.
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
     * Constructor de la clase ReseniaPedido.
     * Inicializa una nueva reseña con toda la información relevante.
     *
     * @param newId         Identificador único de la reseña.
     * @param usuario       Usuario que realiza la reseña.
     * @param pedido        Pedido reseñado.
     * @param calificacion  Calificación otorgada.
     * @param comentario    Comentario adicional.
     * @param fechaResenia  Fecha de la reseña.
     */
    public ReseniaPedido(long newId, Usuarios usuario, Pedidos pedido, String calificacion, String comentario, Timestamp fechaResenia) {
        super(newId);
        this.usuario = usuario;
        this.pedido = pedido;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fechaResenia = fechaResenia;
    }
}
