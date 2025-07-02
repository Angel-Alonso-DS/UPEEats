package org.upemor.models.entities;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Pedidos {
    private int idPedido;
    private Usuarios codigoUsuario;
    private LocalDateTime fechaPedido;
    private double total;
    private String estado;
    private LocalTime tiempoEstimado;
    private LocalTime tiempoEntrga;
    private String detalles;
    
    public int getIdPedido() {
        return idPedido;
    }

    public Usuarios getCodigoUsuario() {
        return codigoUsuario;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public double getTotal() {
        return total;
    }

    public String getEstado() {
        return estado;
    }

    public LocalTime getTiempoEstimado() {
        return tiempoEstimado;
    }

    public LocalTime getTiempoEntrga() {
        return tiempoEntrga;
    }

    public String getDetalles() {
        return detalles;
    }

    public Pedidos() {}
}
