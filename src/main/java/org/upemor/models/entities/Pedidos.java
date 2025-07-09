package org.upemor.models.entities;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.upemor.models.Entity;

public class Pedidos extends Entity {
    private Usuarios usuario;
    private LocalTime tiempoEstimado;
    private LocalTime tiempoEntrga;
    private String estado;
    private String comentario;
    private double total;
    private LocalDateTime fecha;

    public Usuarios getUsuario() {return usuario;}

    public LocalTime getTiempoEstimado() {return tiempoEstimado;}

    public LocalTime getTiempoEntrga() {return tiempoEntrga;}

    public String getEstado() {return estado;}

    public String getComentario() {return comentario;}

    public double getTotal() {return total;}

    public LocalDateTime getFecha() {return fecha;}

    public Pedidos() {
        super(0);
    }
}
