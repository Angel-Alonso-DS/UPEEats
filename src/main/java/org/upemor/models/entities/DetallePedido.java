package org.upemor.models.entities;

import org.upemor.models.Entity;

public class DetallePedido extends Entity {
    private Pedidos pedido;
    private Productos producto;
    private int cantidad;
    private double subtotal;
    private String observaciones;

    public Pedidos getPedido() {return pedido;}
    
    public Productos getProducto() {return producto;}
    
    public int getCantidad() {return cantidad;}
    
    public double getSubtotal() {return subtotal;}
    
    public String getObservaciones() {return observaciones;}
    
    public DetallePedido(long newId, Pedidos pedido, Productos producto, int cantidad, double subtotal,
            String observaciones) {
        super(newId);
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.observaciones = observaciones;
    }
}
