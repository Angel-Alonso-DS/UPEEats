package org.upemor.models.entities;

public class DetallePedido {
    private int idDetallePedido;
    private Productos producto;
    private Pedidos pedido;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    private String observaciones;
    
    public int getIdDetallePedido() {
        return idDetallePedido;
    }
    public Productos getProducto() {
        return producto;
    }
    public Pedidos getPedido() {
        return pedido;
    }
    public int getCantidad() {
        return cantidad;
    }
    public double getPrecioUnitario() {
        return precioUnitario;
    }
    public double getSubtotal() {
        return subtotal;
    }
    public String getObservaciones() {
        return observaciones;
    }

    public DetallePedido() {}
}
