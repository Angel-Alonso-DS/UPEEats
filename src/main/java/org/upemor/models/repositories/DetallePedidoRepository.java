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

    @Override
    protected void inicializarQueries() {
        insertarQuery = "INSERT INTO DetallePedido (id_pedido, id_producto, cantidad, subtotal, observaciones) VALUES (?, ?, ?, ?, ?)";
        actualizarQuery = "UPDATE DetallePedido SET id_pedido = ?, id_producto = ?, cantidad = ?, subtotal = ?, observaciones = ? WHERE id_detalle = ?";
        eliminarQuery = "DELETE FROM DetallePedido WHERE id_detalle = ?";
        seleccionarTodoQuery = "SELECT * FROM DetallePedido";
        seleccionarPorIdQuery = "SELECT * FROM DetallePedido WHERE id_detalle = ?";
    }

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

    @Override
    protected void prepararInsert(PreparedStatement stmt, DetallePedido d) throws SQLException {
        stmt.setObject(1, d.getPedido());
        stmt.setObject(2, d.getProducto());
        stmt.setInt(3, d.getCantidad());
        stmt.setDouble(4, d.getSubtotal());
        stmt.setString(5, d.getObservaciones());
    }

    @Override
    protected void prepararActualizar(PreparedStatement stmt, DetallePedido d) throws SQLException {
        prepararInsert(stmt, d);
        stmt.setLong(6, d.getId());
    }
}