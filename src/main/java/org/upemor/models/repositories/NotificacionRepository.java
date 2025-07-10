package org.upemor.models.repositories;

import java.sql.*;

import org.upemor.models.Repository;
import org.upemor.models.entities.Notificaciones;

public class NotificacionRepository extends Repository<Notificaciones> {
    private UsuarioRepository usuarioR;
    private PedidosRepository pedidosR;

    public NotificacionRepository() {
        usuarioR = new UsuarioRepository();
        pedidosR = new PedidosRepository();
    }

    @Override
    protected void inicializarQueries() {
        insertarQuery = "INSERT INTO Notificaciones (id_usuario, id_pedido, mensaje, fecha) VALUES (?, ?, ?, ?)";
        actualizarQuery = "UPDATE Notificaciones SET id_usuario=?, id_pedido=?, mensaje=?, fecha=? WHERE id_notificacion=?";
        eliminarQuery = "DELETE FROM Notificaciones WHERE id_notificacion=?";
        seleccionarTodoQuery = "SELECT * FROM Notificaciones";
        seleccionarPorIdQuery = "SELECT * FROM Notificaciones WHERE id_notificacion=?";
    }

    @Override
    protected Notificaciones mapear(ResultSet rs) throws SQLException {
        return new Notificaciones(
            rs.getLong("id_notificacion"),
            usuarioR.obtenerPorId(rs.getLong("id_usuario")),
            pedidosR.obtenerPorId(rs.getLong("id_pedido")),
            rs.getString("mensaje"),
            rs.getTimestamp("fecha")
        );
    }

    @Override
    protected void prepararInsert(PreparedStatement stmt, Notificaciones n) throws SQLException {
        stmt.setObject(1, n.getUsuario());
        stmt.setObject(2, n.getPedido());
        stmt.setString(3, n.getMensaje());
        stmt.setTimestamp(4, n.getFecha());
    }

    @Override
    protected void prepararActualizar(PreparedStatement stmt, Notificaciones n) throws SQLException {
        prepararInsert(stmt, n);
        stmt.setLong(5, n.getId());
    }
}