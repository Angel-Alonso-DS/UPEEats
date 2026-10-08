package org.upemor.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.upemor.models.DetallePedido;
import org.upemor.repositories.base.Repositorio;


/**
 * Repositorio para la gestión de entidades DetallePedido en la base de datos.
 * Proporciona métodos CRUD y consultas especializadas para DetallePedido.
 */
public class DetallePedidoRepositorio extends Repositorio<DetallePedido>{
    /** Repositorio auxiliar para operaciones sobre pedidos. */
    private static final PedidosRepositorio pedidosRPS = new PedidosRepositorio();
    /** Repositorio auxiliar para operaciones sobre productos. */
    private static final ProductosRepositorio productosRPS = new ProductosRepositorio();

    /**
     * Inicializa las consultas SQL utilizadas por el repositorio.
     */
    @Override
    protected void inicializarQueries() {
        insertarQuery = "INSERT INTO DetallePedido (id_pedido, id_producto, cantidad, precio_unitario, subtotal, observaciones) VALUES (?, ?, ?, ?, ?, ?)";
        actualizarQuery = "UPDATE DetallePedido SET id_pedido = ?, id_producto = ?, cantidad = ?, precio_unitario = ?, subtotal = ?, observaciones = ? WHERE id_detalle = ?";
        eliminarQuery = "DELETE FROM DetallePedido WHERE id_detalle = ?";
        seleccionarTodoQuery = "SELECT * FROM DetallePedido";
        seleccionarPorIdQuery = "SELECT * FROM DetallePedido WHERE id_detalle = ?";
    }

    /**
     * Mapea un ResultSet de SQL a una instancia de DetallePedido.
     * @param rs ResultSet con los datos de la consulta.
     * @return Instancia de DetallePedido mapeada.
     * @throws SQLException Si ocurre un error al acceder a los datos.
     */
    @Override
    protected DetallePedido mapear(ResultSet rs) throws SQLException {
        return new DetallePedido(
            rs.getLong("id_detalle"),
            pedidosRPS.obtenerPorId(rs.getLong("id_pedido")),
            productosRPS.obtenerPorId(rs.getLong("id_producto")),
            rs.getInt("cantidad"),
            rs.getDouble("precio_unitario"),
            rs.getDouble("subtotal"),
            rs.getString("observaciones")
        );
    }

    /**
     * Prepara un PreparedStatement para insertar un DetallePedido en la base de datos.
     * @param stmt PreparedStatement a preparar.
     * @param d DetallePedido a insertar.
     * @throws SQLException Si ocurre un error al establecer los parámetros.
     */
    @Override
    protected void prepararInsert(PreparedStatement stmt, DetallePedido d) throws SQLException {
        stmt.setLong(1, d.getPedido().getId());
        stmt.setLong(2, d.getProducto().getId());
        stmt.setInt(3, d.getCantidad());
        stmt.setDouble(4, d.getPrecioUnitario());
        stmt.setDouble(5, d.getSubtotal());
        stmt.setString(6, d.getObservaciones());
    }

    /**
     * Prepara un PreparedStatement para actualizar un DetallePedido en la base de datos.
     * @param stmt PreparedStatement a preparar.
     * @param d DetallePedido a actualizar.
     * @throws SQLException Si ocurre un error al establecer los parámetros.
     */
    @Override
    protected void prepararActualizar(PreparedStatement stmt, DetallePedido d) throws SQLException {
        prepararInsert(stmt, d);
        stmt.setLong(7, d.getId());
    }

    /**
     * Obtiene una lista de DetallePedido asociados a un pedido específico.
     * @param idPedido ID del pedido a consultar.
     * @return Lista de DetallePedido relacionados con el pedido.
     */
    public List<DetallePedido> obtenerPorIdPedido(long idPedido) {
        final String sql = "SELECT * FROM DetallePedido WHERE id_pedido = ?";
        return ejecutarConsulta(sql, new Object[] {idPedido});
    }
   
}
