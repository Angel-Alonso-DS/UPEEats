package org.upemor.models.entities;

import java.sql.Timestamp;

import org.upemor.models.Entity;
import lombok.Getter;

@Getter
public class ReseniaPedido extends Entity {
    private Usuarios usuario;
    private Pedidos pedido;
    private String calificacion;
    private String comentario;
    private Timestamp fechaResenia;
    
    public Usuarios getUsuario() {return usuario;}
    
    public Pedidos getPedido() {return pedido;}
    
    public String getCalificacion() {return calificacion;}
    
    public String getComentario() {return comentario;}
    
    public Timestamp getFechaResenia() {return fechaResenia;}
    
    public ReseniaPedido(long newId, Usuarios usuario, Pedidos pedido, String calificacion, String comentario, Timestamp fechaResenia) {
        super(newId);
        this.usuario = usuario;
        this.pedido = pedido;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fechaResenia = fechaResenia;
    }
}
