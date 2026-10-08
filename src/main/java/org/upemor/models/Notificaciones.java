package org.upemor.models;

import java.sql.Timestamp;

import org.upemor.models.base.Entidad;
import org.upemor.widgets.ItemEntidad;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase Notificaciones
 * Representa una notificación enviada a un usuario relacionada con un pedido.
 * Contiene información sobre el usuario, el pedido, el mensaje y la fecha de la notificación.
 */

 @Getter
@Setter

public class Notificaciones extends Entidad {
    // Usuario al que va dirigida la notificación
    private Usuarios usuario;
    // Pedido relacionado con la notificación
    private Pedidos pedido;
    // Mensaje de la notificación
    private String mensaje;
    // Fecha y hora en que se generó la notificación
    private Timestamp fechaCreacion;
    
    /**
     * Constructor de la clase Notificaciones.
     * 
     * @param newId    Identificador único de la notificación.
     * @param usuario  Usuario destinatario de la notificación.
     * @param pedido   Pedido relacionado con la notificación.
     * @param mensaje  Mensaje que se enviará al usuario.
     * @param fechaCreacion    Fecha y hora de la notificación.
     */
    public Notificaciones(long newId, Usuarios usuario, Pedidos pedido, String mensaje, Timestamp fechaCreacion) {
        super(newId);
        this.usuario = usuario;
        this.pedido = pedido;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        tipoEntidad = "Notificaciones";
    }
    
    @Override
    public String[][] toInfo() {
        return new String[][] {
            {ItemEntidad.TITULO, "Pedido", pedido.getFolio()},
            {ItemEntidad.CONTENIDO, "Mensaje", mensaje},
            {ItemEntidad.FECHA, "Fecha registro", fechaCreacion + ""},
            {ItemEntidad.SUBTITULO, "Usuario", usuario.getNombre()}
        };
    }
}
