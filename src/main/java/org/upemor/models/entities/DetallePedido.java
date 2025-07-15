
package org.upemor.models.entities;


import org.upemor.models.Entity;
import lombok.Getter;

/**
 * Entidad que representa el detalle de un pedido en UPEEats.
 * Incluye información del pedido, producto, cantidad, subtotal y observaciones.
 */
@Getter
public class DetallePedido extends Entity {
    private Pedidos pedido;
    private Productos producto;
    private int cantidad;
    private double subtotal;
    private String observaciones;

    /**
     * Constructor de la entidad DetallePedido.
     * @param newId identificador único
     * @param pedido objeto Pedidos asociado
     * @param producto objeto Productos asociado
     * @param cantidad cantidad de productos
     * @param subtotal subtotal del detalle
     * @param observaciones observaciones adicionales
     */
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
