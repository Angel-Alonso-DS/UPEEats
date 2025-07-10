package org.upemor.models.repositories;

import java.sql.*;

import org.upemor.models.Repository;
import org.upemor.models.entities.ReseniaPedido;

public class ReseniaPedidoRepository extends Repository<ReseniaPedido> {
    private UsuarioRepository usuarioR;
    private PedidosRepository pedidosR;

    public ReseniaPedidoRepository() {
        usuarioR = new UsuarioRepository();
        pedidosR = new PedidosRepository();
    }

    @Override
    protected void inicializarQueries() {
        insertarQuery = "INSERT INTO ReseniaPedido (id_usuario, id_pedido, calificacion, comentario, fecha) VALUES (?, ?, ?, ?, ?)";
        actualizarQuery = "UPDATE ReseniaPedido SET id_usuario=?, id_pedido=?, calificacion=?, comentario=?, fecha=? WHERE id_resenia=?";
        eliminarQuery = "DELETE FROM ReseniaPedido WHERE id_resenia=?";
        seleccionarTodoQuery = "SELECT * FROM ReseniaPedido";
        seleccionarPorIdQuery = "SELECT * FROM ReseniaPedido WHERE id_resenia=?";
    }

    @Override
    protected ReseniaPedido mapear(ResultSet rs) throws SQLException {
        return new ReseniaPedido(
            rs.getLong("id_resenia"),
            usuarioR.obtenerPorId(rs.getLong("id_usuario")),
            pedidosR.obtenerPorId(rs.getLong("id_pedido")),
            rs.getString("calificacion"),
            rs.getString("comentario"),
            rs.getTimestamp("fecha")
        );
    }

    @Override
    protected void prepararInsert(PreparedStatement stmt, ReseniaPedido r) throws SQLException {
        stmt.setObject(1, r.getUsuario());
        stmt.setObject(2, r.getPedido());
        stmt.setString(3, r.getCalificacion());
        stmt.setString(4, r.getComentario());
        stmt.setTimestamp(5, r.getFechaResenia());
    }

    @Override
    protected void prepararActualizar(PreparedStatement stmt, ReseniaPedido r) throws SQLException {
        prepararInsert(stmt, r);
        stmt.setLong(6, r.getId());
    }
}