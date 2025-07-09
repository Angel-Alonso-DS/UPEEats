package org.upemor.models.entities;

import java.time.LocalDateTime;

import org.upemor.models.Entity;

public class ReseniaPedido extends Entity {
    private Usuarios usuario;
    private Pedidos pedido;
    private int calificaion;
    private String comentario;
    private LocalDateTime fechaResenia;
    
    public Usuarios getUsuario() {return usuario;}

    public Pedidos getPedido() {return pedido;}

    public int getCalificaion() {return calificaion;}

    public String getComentario() {return comentario;}

    public LocalDateTime getFechaResenia() {return fechaResenia;}

    public ReseniaPedido() {
        super(0);
    }
}
