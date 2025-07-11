package org.upemor.models.entities;

import java.sql.Timestamp;

import org.upemor.models.Entity;
import lombok.Getter;

@Getter
public class ReseniaProducto extends Entity {
    private Usuarios usuario;
    private Productos producto;
    private String calificacion;
    private String comentario;
    private Timestamp fechaResenia;
    
    public Usuarios getUsuario() {return usuario;}
    
    public Productos getProducto() {return producto;}
    
    public String getCalificacion() {return calificacion;}
    
    public String getComentario() {return comentario;}
    
    public Timestamp getFechaResenia() {return fechaResenia;}
    
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
