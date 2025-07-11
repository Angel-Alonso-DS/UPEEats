package org.upemor.controllers;

import org.upemor.models.entities.Pedidos;
import org.upemor.models.entities.Productos;
import org.upemor.models.entities.Usuarios;
import org.upemor.models.entities.DetallePedido;
import org.upemor.models.repositories.PedidosRepository;
import org.upemor.models.repositories.DetallePedidoRepository;

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

    // Crea un pedido y sus detalles
    public boolean crearPedido(long idUsuario, java.util.List<Long> productos, java.util.List<Integer> cantidades, String comentario) {
        try {
            // Obtener usuario
            UsuariosController usuariosController = new UsuariosController();
            Usuarios usuario = usuariosController.buscarPorId(idUsuario);
            if (usuario == null) return false;

            // Calcular total (puedes mejorar esto sumando el precio real de cada producto)
            double total = 0;
            ProductosController productosController = new ProductosController();
            for (int i = 0; i < productos.size(); i++) {
                Productos prod = productosController.buscarPorId(productos.get(i));
                if (prod == null) return false;
                total += prod.getPrecio() * cantidades.get(i);
            }

            java.sql.Timestamp ahora = new java.sql.Timestamp(System.currentTimeMillis());
            Pedidos pedido = new Pedidos(0, usuario, ahora, ahora, "pendiente", comentario, total, ahora);
            pedidosRepository.insertar(pedido);
            long idPedido = pedidosRepository.obtenerUltimoIdInsertado();

            // Actualizar el objeto pedido con el id real
            Pedidos pedidoConId = new Pedidos(idPedido, usuario, ahora, ahora, "pendiente", comentario, total, ahora);

            // Insertar detalles
            DetallePedidoRepository detalleRepo = new DetallePedidoRepository();
            for (int i = 0; i < productos.size(); i++) {
                Productos prod = productosController.buscarPorId(productos.get(i));
                DetallePedido detalle = new DetallePedido(
                    0, pedidoConId, prod, cantidades.get(i), prod.getPrecio() * cantidades.get(i), null
                );
                detalleRepo.insertar(detalle);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
