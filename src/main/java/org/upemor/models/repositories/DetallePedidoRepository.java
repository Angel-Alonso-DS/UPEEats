/**
 * Repositorio para operaciones CRUD sobre la entidad DetallePedido.
 * Permite mapear, insertar y actualizar detalles de pedidos.
 */
package org.upemor.models.repositories;

import java.sql.*;

import org.upemor.models.Repository;
import org.upemor.models.entities.DetallePedido;

public class DetallePedidoRepository extends Repository<DetallePedido> {
    private PedidosRepository pedidosR;
    private ProductoRepository productosR;

    public DetallePedidoRepository() {
        pedidosR = new PedidosRepository();
        productosR = new ProductoRepository();
    }

    /**
     * Inicializa las consultas SQL para operaciones CRUD.
     */
    @Override
    protected void inicializarQueries() {
        insertarQuery = "INSERT INTO DetallePedido (id_pedido, id_producto, cantidad, subtotal, observaciones) VALUES (?, ?, ?, ?, ?)";
        actualizarQuery = "UPDATE DetallePedido SET id_pedido = ?, id_producto = ?, cantidad = ?, subtotal = ?, observaciones = ? WHERE id_detalle = ?";
        eliminarQuery = "DELETE FROM DetallePedido WHERE id_detalle = ?";
        seleccionarTodoQuery = "SELECT * FROM DetallePedido";
        seleccionarPorIdQuery = "SELECT * FROM DetallePedido WHERE id_detalle = ?";
    }

    /**
     * Mapea un ResultSet a un objeto DetallePedido.
     * @param rs ResultSet de la consulta
     * @return Objeto DetallePedido
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    protected DetallePedido mapear(ResultSet rs) throws SQLException {
        return new DetallePedido(
            rs.getLong("id_detalle"),
            pedidosR.obtenerPorId(rs.getLong("id_pedido")),
            productosR.obtenerPorId(rs.getLong("id_producto")),
            rs.getInt("cantidad"),
            rs.getDouble("subtotal"),
            rs.getString("observaciones")
        );
    }

    /**
     * Prepara la sentencia para insertar un detalle de pedido.
     * @param stmt PreparedStatement
     * @param d DetallePedido a insertar
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    protected void prepararInsert(PreparedStatement stmt, DetallePedido d) throws SQLException {
        stmt.setObject(1, d.getPedido());
        stmt.setObject(2, d.getProducto());
        stmt.setInt(3, d.getCantidad());
        stmt.setDouble(4, d.getSubtotal());
        stmt.setString(5, d.getObservaciones());
    }

    /**
     * Prepara la sentencia para actualizar un detalle de pedido.
     * @param stmt PreparedStatement
     * @param d DetallePedido a actualizar
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    protected void prepararActualizar(PreparedStatement stmt, DetallePedido d) throws SQLException {
        prepararInsert(stmt, d);
        stmt.setLong(6, d.getId());
    }
}