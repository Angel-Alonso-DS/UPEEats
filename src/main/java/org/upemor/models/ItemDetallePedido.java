package org.upemor.models;

import org.upemor.models.base.Entidad;
import org.upemor.widgets.ItemEntidad;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

/**
 * Modelo que representa un ítem o detalle dentro de un pedido en el sistema UpeEats.
 * <p>
 * Cada instancia de esta clase corresponde a un producto específico dentro de un pedido,
 * incluyendo la cantidad solicitada, el total calculado y cualquier observación adicional.
 * Hereda de la clase base Entidad.
 */
public class ItemDetallePedido extends Entidad {

    /**
     * Producto asociado a este ítem del pedido.
     */
    private Productos producto;

    /**
     * Cantidad de unidades del producto en el pedido.
     */
    private int cantidad;

    /**
     * Total calculado para este ítem (precio * cantidad).
     */
    private double total;

    /**
     * Observaciones adicionales para este ítem (por ejemplo, instrucciones especiales).
     */
    private String observacion;

    /**
     * Constructor para crear un nuevo ítem de detalle de pedido.
     * @param producto Producto asociado
     * @param cantidad Cantidad solicitada
     * @param total Total calculado para este ítem
     * @param observacion Observaciones adicionales
     */
    public ItemDetallePedido(Productos producto, int cantidad, double total, String observacion) {
        super(0);
        this.producto = producto;
        this.cantidad = cantidad;
        this.total = total;
        this.observacion = observacion;
        tipoEntidad = "ItemDetallePedido";
    }

    /**
     * Devuelve la información del ítem en formato de arreglo para mostrar en la interfaz.
     * @return Arreglo de pares clave-valor con los datos principales del ítem.
     */
    @Override
    public String[][] toInfo() {
        return new String[][] {
            {ItemEntidad.TITULO, "Producto", producto.getNombreProducto()},
            {ItemEntidad.SUBTITULO, "Precio", producto.getPrecio() + ""},
            {ItemEntidad.SUBTITULO, "Cantidad", cantidad + ""},
            {ItemEntidad.CONTENIDO, "Total", total + ""},
        };
    }
}
