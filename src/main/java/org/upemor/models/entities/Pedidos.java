package org.upemor.models.entities;

import java.sql.Timestamp;

import org.upemor.models.Entity;
import lombok.Getter;

/**
 * Clase Pedidos
 * Representa un pedido realizado por un usuario en la plataforma UPEEats.
 * Contiene información sobre el usuario, tiempos de entrega, estado, comentarios, total y fecha del pedido.
 */
@Getter
public class Pedidos extends Entity {
    // Usuario que realizó el pedido
    private Usuarios usuario;
    // Tiempo estimado para la entrega del pedido
    private Timestamp tiempoEstimado;
    // Tiempo real de entrega del pedido
    private Timestamp tiempoEntrega;
    // Estado actual del pedido (por ejemplo: "En proceso", "Entregado", etc.)
    private String estado;
    // Comentario adicional sobre el pedido
    private String comentario;
    // Total a pagar por el pedido
    private double total;
    // Fecha en que se realizó el pedido
    private Timestamp fecha;

    /**
     * Constructor de la clase Pedidos.
     * Inicializa un nuevo pedido con toda la información relevante.
     *
     * @param newId           Identificador único del pedido.
     * @param usuario         Usuario que realiza el pedido.
     * @param tiempoEstimado  Tiempo estimado de entrega.
     * @param tiempoEntrega   Tiempo real de entrega.
     * @param estado          Estado actual del pedido.
     * @param comentario      Comentario adicional sobre el pedido.
     * @param total           Total a pagar por el pedido.
     * @param fecha           Fecha en que se realizó el pedido.
     */
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
