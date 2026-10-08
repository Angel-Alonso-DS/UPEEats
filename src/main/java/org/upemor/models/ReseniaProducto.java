package org.upemor.models;

import java.sql.Timestamp;

import org.upemor.models.base.Entidad;
import org.upemor.widgets.ItemEntidad;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase ReseniaProducto
 * Representa una reseña realizada por un usuario sobre un producto en la plataforma UPEEats.
 * Contiene información sobre el usuario, el producto, la calificación, el comentario y la fecha de la reseña.
 */

@Getter
@Setter

public class ReseniaProducto extends Entidad {
    // Usuario que realizó la reseña
    private Usuarios usuario;
    // Producto al que corresponde la reseña
    private Productos producto;
    // Calificación otorgada al producto (por ejemplo: "5 estrellas")
    private int calificacion;
    // Comentario adicional sobre el producto
    private String comentario;
    // Fecha en que se realizó la reseña
    private Timestamp fechaResenia;
    
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
    public ReseniaProducto(long newId, Usuarios usuario, Productos producto, int calificacion, String comentario,
            Timestamp fechaResenia) {
        super(newId);
        this.usuario = usuario;
        this.producto = producto;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fechaResenia = fechaResenia;
        tipoEntidad = "ReseniaProducto";
    }

    @Override
    public String[][] toInfo() {
        return new String[][] {
            {ItemEntidad.SUBTITULO, "Usuario", usuario.getNombre()},
            {ItemEntidad.SUBTITULO, "Producto", producto.getNombreProducto()},
            {ItemEntidad.CONTENIDO, "Calificacion", calificacion + ""},
            {ItemEntidad.CONTENIDO, "Comentario", comentario},
            {ItemEntidad.FECHA, "Fecha", fechaResenia + ""},
        };
    }
}
