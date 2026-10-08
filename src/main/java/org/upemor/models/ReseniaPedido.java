package org.upemor.models;

import java.sql.Timestamp;

import org.upemor.models.base.Entidad;
import org.upemor.widgets.ItemEntidad;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase ReseniaPedido
 * Representa una reseña realizada por un usuario sobre un pedido en la plataforma UPEEats.
 * Contiene información sobre el usuario, el pedido, la calificación, el comentario y la fecha de la reseña.
 */

@Getter
@Setter

public class ReseniaPedido extends Entidad {
    // Usuario que realizó la reseña
    private Usuarios usuario;
    // Pedido al que corresponde la reseña
    private Pedidos pedido;
    // Calificación otorgada al pedido (por ejemplo: "5 estrellas")
    private int calificacion;
    // Comentario adicional sobre el pedido
    private String comentario;
    // Fecha en que se realizó la reseña
    private Timestamp fechaResenia;

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
    public ReseniaPedido(long newId, Usuarios usuario, Pedidos pedido, int calificacion, String comentario, Timestamp fechaResenia) {
        super(newId);
        this.usuario = usuario;
        this.pedido = pedido;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fechaResenia = fechaResenia;
        tipoEntidad = "ReseniaPedido";
    }

    @Override
    public String[][] toInfo() {
        return new String[][] {
            {ItemEntidad.SUBTITULO, "Usuario", usuario.getNombre()},
            {ItemEntidad.SUBTITULO, "Pedido", pedido.getFolio()},
            {ItemEntidad.CONTENIDO, "Calificacion", calificacion + ""},
            {ItemEntidad.CONTENIDO, "Comentario", comentario},
            {ItemEntidad.FECHA, "Fecha", fechaResenia + ""},
        };
    }
}
