package org.upemor.controllers;

import org.upemor.models.entities.Pedidos;
import org.upemor.models.repositories.PedidosRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para la gestión de pedidos en la cafetería.
 */
public class PedidosController {
    private final PedidosRepository pedidosRepository = new PedidosRepository();

    /**
     * Obtiene todos los pedidos registrados.
     * @return lista de pedidos
     */
    public List<Pedidos> obtenerTodos() {
        return pedidosRepository.obtenerTodos();
    }

    /**
     * Busca un pedido por su ID.
     * @param id identificador del pedido
     * @return Pedido encontrado o null
     */
    public Pedidos buscarPorId(long id) {
        try {
            return pedidosRepository.obtenerPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Inserta un nuevo pedido.
     * @param pedido objeto Pedidos a insertar
     */
    public void insertar(Pedidos pedido) {
        pedidosRepository.insertar(pedido);
    }

    /**
     * Actualiza un pedido existente.
     * @param pedido objeto Pedidos actualizado
     */
    public void actualizar(Pedidos pedido) {
        try {
            pedidosRepository.actualizar(pedido);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un pedido por su ID.
     * @param id identificador del pedido
     */
    public void eliminar(int id) {
        pedidosRepository.eliminar(id);
    }
}
