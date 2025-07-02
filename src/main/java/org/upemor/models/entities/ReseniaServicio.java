package org.upemor.models.entities;

import java.time.LocalDateTime;

public class ReseniaServicio {
    int idReseniaServicio;
    Usuarios usuario;
    Pedidos pedido;
    int puntuacion;
    String comentario;
    LocalDateTime fechaResenia;
    
    public int getIdReseniaServicio() {
        return idReseniaServicio;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public Pedidos getpedido() {
        return pedido;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDateTime getFechaResenia() {
        return fechaResenia;
    }

    public ReseniaServicio() {}
}
