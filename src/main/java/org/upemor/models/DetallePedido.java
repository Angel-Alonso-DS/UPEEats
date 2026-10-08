package org.upemor.models;

import org.upemor.models.base.Entidad;
import org.upemor.widgets.ItemEntidad;

import lombok.Getter;
import lombok.Setter;

/**
 * Entidad que representa el detalle de un pedido en UPEEats.
 * Incluye información del pedido, producto, cantidad, subtotal y observaciones.
 */

 @Getter
@Setter

public class DetallePedido extends Entidad {
    private Pedidos pedido;
    private Productos producto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    private String observaciones;

    /**
     * Constructor de la entidad DetallePedido.
     * @param newId identificador único
     * @param pedido objeto Pedidos asociado
     * @param producto objeto Productos asociado
     * @param cantidad cantidad de productos
     * @param precioUnitario precio actual del producto
     * @param subtotal subtotal del detalle
     * @param observaciones observaciones adicionales
     */
    public DetallePedido(long newId, Pedidos pedido, Productos producto, int cantidad, double precioUnitario, double subtotal, String observaciones) {
        super(newId);
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.observaciones = observaciones;
        tipoEntidad = "DetallePedido";
    }

    @Override
    public String[][] toInfo() {
        return new String[][] {
            {ItemEntidad.TITULO, "Producto", producto.getNombreProducto()},
            {ItemEntidad.CANTIDAD, "Cantidad", cantidad + ""},
            {ItemEntidad.PRECIO, "Precio unitario", precioUnitario + ""},
            {ItemEntidad.CANTIDAD, "Subtotal", subtotal + ""},
            {ItemEntidad.CONTENIDO, "Observaciones", observaciones}
        };
    }
}
