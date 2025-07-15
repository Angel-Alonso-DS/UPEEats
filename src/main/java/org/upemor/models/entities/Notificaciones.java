package org.upemor.models.entities;

import java.sql.Timestamp;

import org.upemor.models.Entity;

import lombok.Getter;

/**
 * Clase Notificaciones
 * Representa una notificación enviada a un usuario relacionada con un pedido.
 * Contiene información sobre el usuario, el pedido, el mensaje y la fecha de la notificación.
 */
@Getter
public class Notificaciones extends Entity {
    // Usuario al que va dirigida la notificación
    private Usuarios usuario;
    // Pedido relacionado con la notificación
    private Pedidos pedido;
    // Mensaje de la notificación
    private String mensaje;
    // Fecha y hora en que se generó la notificación
    private Timestamp fecha;
    
    /**
     * Constructor de la clase Notificaciones.
     * 
     * @param newId    Identificador único de la notificación.
     * @param usuario  Usuario destinatario de la notificación.
     * @param pedido   Pedido relacionado con la notificación.
     * @param mensaje  Mensaje que se enviará al usuario.
     * @param fecha    Fecha y hora de la notificación.
     */
    public Notificaciones(long newId, Usuarios usuario, Pedidos pedido, String mensaje, Timestamp fecha) {
        super(newId);
        this.usuario = usuario;
        this.pedido = pedido;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }
    
}
