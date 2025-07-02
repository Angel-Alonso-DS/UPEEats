package org.upemor.models.entities;

import java.time.LocalDateTime;

public class ReseniaProducto {
    int idReseniaProducto;
    Usuarios usuario;
    Productos producto;
    int puntuacion;
    String comentario;
    LocalDateTime fechaResenia;
    
    public int getIdReseniaProducto() {
        return idReseniaProducto;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public Productos getProducto() {
        return producto;
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

    public ReseniaProducto() {}

    
}