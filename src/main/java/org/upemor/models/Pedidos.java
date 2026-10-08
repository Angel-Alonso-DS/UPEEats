package org.upemor.models;

// import java.sql.Time;
import java.sql.Timestamp;

import org.upemor.models.base.Entidad;
import org.upemor.widgets.ItemEntidad;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase Pedidos
 * Representa un pedido realizado por un usuario en la plataforma UPEEats.
 * Contiene información sobre el usuario, tiempos de entrega, estado, comentarios, total y fecha del pedido.
 */

@Getter
@Setter

public class Pedidos extends Entidad{
    // Usuario que realizó el pedido
    private Usuarios usuario;
    // Folio del pedido
    private String folio;
    // Tiempo estimado para la entrega del pedido
    private String tiempoEstimado;
    // Tiempo real de entrega del pedido
    private String tiempoEntrega;
    // Estado actual del pedido (por ejemplo: "En proceso", "Entregado", etc.)
    private String estado;
    // Comentario adicional sobre el pedido
    private String comentario;
    // Total a pagar por el pedido
    private double total;
    // Fecha en que se realizó el pedido
    private Timestamp fechaPedido;

    /**
     * Constructor de la clase Pedidos.
     * Inicializa un nuevo pedido con toda la información relevante.
     *
     * @param newId           Identificador único del pedido.
     * @param usuario         Usuario que realiza el pedido.
     * @param folio           Folio que es visualizado por el estudiante para identificar su pedido
     * @param tiempoEstimado  Tiempo estimado de entrega.
     * @param tiempoEntrega   Tiempo real de entrega.
     * @param estado          Estado actual del pedido.
     * @param comentario      Comentario adicional sobre el pedido.
     * @param total           Total a pagar por el pedido.
     * @param fechaPedido           Fecha en que se realizó el pedido.
     */
    public Pedidos(long newId, Usuarios usuario, String folio, String tiempoEstimado, String tiempoEntrega, String estado, String comentario, double total, Timestamp fechaPedido) {
        super(newId);
        this.usuario = usuario;
        this.folio = folio;
    this.tiempoEstimado = tiempoEstimado;
    this.tiempoEntrega = tiempoEntrega;
        this.estado = estado;
        this.comentario = comentario;
        this.total = total;
        this.fechaPedido = fechaPedido;
        tipoEntidad = "Pedidos";
    }

    @Override
    public String[][] toInfo() {
        return new String[][] {
            {ItemEntidad.TITULO, "Folio", folio},
            {ItemEntidad.TIEMPO, "Tiempo estimado", tiempoEstimado},
            {ItemEntidad.TIEMPO, "Tiempo entrega", tiempoEntrega},
            {ItemEntidad.SUBTITULO, "Estado", estado},
            {ItemEntidad.SUBTITULO, "Usuario: ", usuario.getNombre()},
            {ItemEntidad.CONTENIDO, "Comentario", comentario},
            {ItemEntidad.PRECIO, "total", total + ""},
            {ItemEntidad.FECHA, "Fecha", fechaPedido + ""},

        };
    }
}
