package org.upemor.utils;

/**
 * Interfaz usada por las clases para realizar los 
 * cambios notificados por NotificadorGlobal
 * @implSpec 
 * <pre>
 *      public class Gestor implements Receptor {
 * 
 *          -@Override
 *          public void onReceptor(String msg, Object[] objetos) {
 *              if(msg.equals(" notificacion ")) {
 *                  // instrucciones
 *              }
 *          }
 *      }
 * </pre>
 */
public interface Receptor {
    /**
     * Funcion que notifica a la clase sobre el cambio realizado
     * @param msg Mensaje del cambio
     * @param objetos Datos que se necesiten transmitir
     */
   void onReceptor(String msg, Object[] objetos);
}
