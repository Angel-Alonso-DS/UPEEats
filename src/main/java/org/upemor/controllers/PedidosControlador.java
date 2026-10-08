package org.upemor.controllers;

// import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.upemor.models.DetallePedido;
import org.upemor.models.ItemDetallePedido;
import org.upemor.models.Pedidos;
import org.upemor.models.Productos;
import org.upemor.models.Usuarios;
import org.upemor.repositories.DetallePedidoRepositorio;
import org.upemor.repositories.NotificacionesRespositorio;
import org.upemor.repositories.PedidosRepositorio;

/**
 * Controlador para la gestión de pedidos en el sistema UpeEats.
 * <p>
 * Esta clase centraliza la lógica de negocio relacionada con la creación, actualización,
 * consulta y eliminación de pedidos, así como la gestión de notificaciones y detalles asociados.
 */
public class PedidosControlador {

    /**
     * Repositorio para gestionar notificaciones relacionadas a pedidos.
     */
    private final NotificacionesRespositorio notificacionesRPS = new NotificacionesRespositorio();

    /**
     * Repositorio para operaciones CRUD sobre pedidos.
     */
    private final PedidosRepositorio pedidosRPS = new PedidosRepositorio();

    /**
     * Repositorio para operaciones sobre los detalles de cada pedido.
     */
    private final DetallePedidoRepositorio detallePedidoRPS = new DetallePedidoRepositorio();


    /**
     * Obtiene todos los pedidos registrados en el sistema.
     * @return lista de pedidos
     */
    public List<Pedidos> obtenerTodos() {
        return pedidosRPS.obtenerTodos();
    }


    /**
     * Busca un pedido por su ID.
     * @param id identificador del pedido
     * @return Pedido encontrado o null
     */
    public Pedidos buscarPorId(long id) {
        return pedidosRPS.obtenerPorId(id);
    }


    /**
     * Inserta un nuevo pedido y sus detalles asociados en la base de datos.
     * @param pedido objeto Pedidos a insertar
     * @param detallePedidos lista de detalles del pedido
     */
    private void insertar(Pedidos pedido, List<DetallePedido> detallePedidos) {
        pedidosRPS.insertar(pedido);

        long id = pedido.getId();
        if (id == 0) id = pedidosRPS.obtenerUltimoIdInsertado();
        pedido.setId(id);

        for (DetallePedido detallePedido : detallePedidos) {
            detallePedido.setPedido(pedido);
            detallePedidoRPS.insertar(detallePedido);
        }
    }


    /**
     * Crea un pedido completo con productos, cantidades y detalles, y lo almacena en la base de datos.
     * Genera un folio único, calcula el total y el tiempo estimado, y notifica al usuario.
     * @param usuario Usuario que realiza el pedido
     * @param listaDetallePedidos Lista de ítems con productos y cantidades
     * @param comentario Comentario adicional para el pedido
     * @return true si el pedido se creó correctamente, false en caso de error
     */
    public boolean crearPedido(Usuarios usuario, List<ItemDetallePedido> listaDetallePedidos, String comentario) {
        double total = 0;
        int tiempoTotalEstimadoSegundos = 0;
        List<DetallePedido> detalles = new ArrayList<>();
        try {
            // Generar folio único usando UUID
            String folio = "PED-" + UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();
            for (int i = 0; i < listaDetallePedidos.size(); i++) {
                Productos producto = listaDetallePedidos.get(i).getProducto();
                int cantidad = listaDetallePedidos.get(i).getCantidad();
                int tiempoSegundos = convertirTiempoASegundos(producto.getTiempoPreparacion());
                double subtotal = producto.getPrecio() * cantidad;
                total += subtotal;
                tiempoTotalEstimadoSegundos += tiempoSegundos * cantidad;
                detalles.add(new DetallePedido(i, null, producto, cantidad, producto.getPrecio(), subtotal, listaDetallePedidos.get(i).getObservacion()));
            }
            String tiempoEstimado = segundosATiempo(tiempoTotalEstimadoSegundos);
            Timestamp ahora = new Timestamp(System.currentTimeMillis());
            // Usar el folio generado
            Pedidos pedido = new Pedidos(0, usuario, folio, tiempoEstimado, tiempoEstimado, "pendiente", comentario, total, ahora);
            insertar(pedido, detalles);
            notificacionesRPS.notificar(buscarPorFolio(folio).get(0));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Convierte un tiempo en formato "hh:mm:ss" a segundos.
     * @param tiempo Cadena con el tiempo en formato "hh:mm:ss"
     * @return Tiempo total en segundos
     */
    private int convertirTiempoASegundos(String tiempo) {
        String[] partes = tiempo.split(":");
        int hr = 0;
        int min = 0;
        int seg = 0;
        hr = Integer.parseInt(partes[0]);
        min = Integer.parseInt(partes[1]);
        seg = Integer.parseInt(partes[2]);
        return hr * 3600 + min * 60 + seg;
    }

    /**
     * Convierte un tiempo en segundos a formato "hh:mm:ss".
     * @param totalSegundos Tiempo total en segundos
     * @return Cadena con el tiempo en formato "hh:mm:ss"
     */
    private String segundosATiempo(int totalSegundos) {
        int hr = totalSegundos / 3600;
        int min = (totalSegundos % 3600) / 60;
        int seg = totalSegundos % 60;
        return String.format("%02d:%02d:%02d", hr, min, seg);
    }


    /**
     * Actualiza un pedido existente y notifica al usuario.
     * @param pedido objeto Pedidos actualizado
     */
    public void actualizar(Pedidos pedido) {
        notificacionesRPS.notificar(pedido);
        pedidosRPS.actualizar(pedido);
    }


    /**
     * Actualiza el estado de un pedido y notifica al usuario.
     * @param id Id del pedido a actualizar
     * @param estado Nuevo estado
     */
    public void actualizarEstado(long id, String estado) {
        pedidosRPS.actualizarEstado(id, estado);
        Pedidos pedido = buscarPorId(id);
        notificacionesRPS.notificar(pedido);
    }


    /**
     * Obtiene la lista de detalles de un pedido por su ID.
     * @param id ID del pedido
     * @return Lista de detalles del pedido
     */
    public List<DetallePedido> obtenerDetallesPorIdPedido(long id) {
        return detallePedidoRPS.obtenerPorIdPedido(id);
    }


    /**
     * Busca los pedidos por el ID del usuario.
     * @param idUsuario ID del usuario
     * @return Lista de pedidos del usuario
     */
    public List<Pedidos> buscarPorIdUsuario(long idUsuario) {
        return pedidosRPS.buscarPorIdUsuario(idUsuario);
    }


    /**
     * Busca los pedidos por el folio.
     * @param folio Folio del pedido
     * @return Lista de pedidos con el folio especificado
     */
    public List<Pedidos> buscarPorFolio(String folio) {
        return pedidosRPS.buscarPorFolio(folio);
    }


    /**
     * Busca los pedidos por el ID del usuario y el folio.
     * @param idUsuario ID del usuario
     * @param folio Folio del pedido
     * @return Lista de pedidos que coinciden con el ID de usuario y el folio especificado
     */
    public List<Pedidos> buscarPorIdUsuarioConFolio(long idUsuario, String folio) {
        return pedidosRPS.buscarPorIdUsuarioConFolio(idUsuario, folio);
    }


    /**
     * Elimina un pedido por su ID.
     * @param id identificador del pedido
     */
    public void eliminar(long id) {
        pedidosRPS.eliminar(id);
    }

}
