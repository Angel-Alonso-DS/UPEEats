package org.upemor.models.entities;

import java.sql.Timestamp;

import org.upemor.models.Entity;

public class Pedidos extends Entity {
    private Usuarios usuario;
    private Timestamp tiempoEstimado;
    private Timestamp tiempoEntrega;
    private String estado;
    private String comentario;
    private double total;
    private Timestamp fecha;

    
    public Usuarios getUsuario() {return usuario;}
    
    public Timestamp getTiempoEstimado() {return tiempoEstimado;}
    
    public Timestamp getTiempoEntrega() {return tiempoEntrega;}
    
    public String getEstado() {return estado;}
    
    public String getComentario() {return comentario;}
    
    public double getTotal() {return total;}
    
    public Timestamp getFecha() {return fecha;}
    
    public Pedidos(long newId, Usuarios usuario, Timestamp tiempoEstimado, Timestamp tiempoEntrega, String estado, String comentario, double total, Timestamp fecha) {
        super(newId);
        this.usuario = usuario;
        this.tiempoEstimado = tiempoEstimado;
        this.tiempoEntrega = tiempoEntrega;
        this.estado = estado;
        this.comentario = comentario;
        this.total = total;
        this.fecha = fecha;
    }
}
