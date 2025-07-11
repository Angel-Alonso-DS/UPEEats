package org.upemor.controllers;

import org.upemor.models.entities.DetallePedido;
import org.upemor.models.repositories.DetallePedidoRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para la gestión de detalles de pedidos en la cafetería.
 */
public class DetallePedidoController {
    private final DetallePedidoRepository detallePedidoRepository = new DetallePedidoRepository();

    /**
     * Obtiene todos los detalles de pedidos registrados.
     * @return lista de detalles de pedidos
     */
    public List<DetallePedido> obtenerTodos() {
        return detallePedidoRepository.obtenerTodos();
    }

    /**
     * Busca un detalle de pedido por su ID.
     * @param id identificador del detalle
     * @return DetallePedido encontrado o null
     */
    public DetallePedido buscarPorId(long id) {
        try {
            return detallePedidoRepository.obtenerPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Inserta un nuevo detalle de pedido.
     * @param detalle objeto DetallePedido a insertar
     */
    public void insertar(DetallePedido detalle) {
        detallePedidoRepository.insertar(detalle);
    }

    /**
     * Actualiza un detalle de pedido existente.
     * @param detalle objeto DetallePedido actualizado
     */
    public void actualizar(DetallePedido detalle) {
        try {
            detallePedidoRepository.actualizar(detalle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un detalle de pedido por su ID.
     * @param id identificador del detalle
     */
    public void eliminar(int id) {
        detallePedidoRepository.eliminar(id);
    }
}
