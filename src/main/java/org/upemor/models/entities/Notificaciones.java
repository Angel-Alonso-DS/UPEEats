package org.upemor.models.entities;

import java.sql.Timestamp;

import org.upemor.models.Entity;

import lombok.Getter;

@Getter
public class Notificaciones extends Entity{
    private Usuarios usuario;
    private Pedidos pedido;
    private String mensaje;
    private Timestamp fecha;
    
    public Notificaciones(long newId, Usuarios usuario, Pedidos pedido, String mensaje, Timestamp fecha) {
        super(newId);
        this.usuario = usuario;
        this.pedido = pedido;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }
    
}
