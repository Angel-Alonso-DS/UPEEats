package org.upemor.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto usado para poder usar un Obvervador que notifica sobre cambios realizados
 */
public class NotificadorGlobal {
    private static final NotificadorGlobal INSTANCIA = new NotificadorGlobal();
    private final List<Receptor> receptores = new ArrayList<>();

    private NotificadorGlobal() {}
    
    /**
     * Obtiene la instancia del objeto
     * @return Devuelve la clase NotificadorGlobal (singelton)
     */
    public static NotificadorGlobal getInstancia() {
        return INSTANCIA;
    }

    /**
     * Registra en la calse las clases que captaran los cambios
     * @param r Objeto que recibira las notificacione
     */
    public void registrar(Receptor r) {
        if (!receptores.contains(r)) {
            receptores.add(r);
        }
    }
    /**
     * 
     * @param r Objeto que no escuchara las notificaciones
     */
    public void remover(Receptor r) {
        receptores.remove(r);
    }

    /**
     * Remueve todos los receptores de notificaciones
     */
    public void removerTodos() {
        receptores.clear();
    }

    /**
     * Notifica a los receptores el cambio realizado
     * @param msg Mensaje de notificacion
     * @param obj Datos a transmitir entre los receptores
     * @implSpec 
     * <pre>
     * NotificadorGlobal.getInstancia().notificarCambio(" notificacion ", new Object[] {datos, ... })
     * </pre>
     */
    public void notificarCambio(String msg, Object[] obj) {

        for (Receptor receptor : new ArrayList<>(receptores)) receptor.onReceptor(msg, obj);
    }
}
