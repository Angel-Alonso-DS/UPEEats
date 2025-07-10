package org.upemor.models.repositories;

import java.sql.*;

import org.upemor.models.Repository;
import org.upemor.models.entities.Pedidos;

public class PedidosRepository extends Repository<Pedidos> {
    private UsuarioRepository ur;

    public PedidosRepository() {
        ur = new UsuarioRepository();
    }

    @Override
    protected void inicializarQueries() {
        insertarQuery = "INSERT INTO Pedidos (id_usuario, tiempo_estimado, tiempo_entrega, estado, comentario, total, fecha) VALUES (?, ?, ?, ?, ?, ?, ?)";
        actualizarQuery = "UPDATE Pedidos SET id_usuario=?, tiempo_estimado=?, tiempo_entrega=?, estado=?, comentario=?, total=?, fecha=? WHERE id_pedido=?";
        eliminarQuery = "DELETE FROM Pedidos WHERE id_pedido=?";
        seleccionarTodoQuery = "SELECT * FROM Pedidos";
        seleccionarPorIdQuery = "SELECT * FROM Pedidos WHERE id_pedido=?";
    }

    @Override
    protected Pedidos mapear(ResultSet rs) throws SQLException {
        return new Pedidos(
            rs.getLong("id_pedido"),
            ur.obtenerPorId(rs.getLong("id_usuario")),
            rs.getTimestamp("tiempo_estimado"),
            rs.getTimestamp("tiempo_entrega"),
            rs.getString("estado"),
            rs.getString("comentario"),
            rs.getDouble("total"),
            rs.getTimestamp("fecha")
        );
    }

    @Override
    protected void prepararInsert(PreparedStatement stmt, Pedidos p) throws SQLException {
        stmt.setObject(1, p.getUsuario());
        stmt.setTimestamp(2, p.getTiempoEstimado());
        stmt.setTimestamp(3, p.getTiempoEntrega());
        stmt.setString(4, p.getEstado());
        stmt.setString(5, p.getComentario());
        stmt.setDouble(6, p.getTotal());
        stmt.setTimestamp(7, p.getFecha());
    }

    @Override
    protected void prepararActualizar(PreparedStatement stmt, Pedidos p) throws SQLException {
        prepararInsert(stmt, p);
        stmt.setLong(8, p.getId());
    }
}