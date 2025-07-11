/**
 * Repositorio para operaciones CRUD sobre la entidad Notificaciones.
 * Permite mapear, insertar y actualizar notificaciones.
 */
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

    /**
     * Inicializa las consultas SQL para operaciones CRUD.
     */
    @Override
    protected void inicializarQueries() {
        insertarQuery = "INSERT INTO Notificaciones (id_usuario, id_pedido, mensaje, fecha) VALUES (?, ?, ?, ?)";
        actualizarQuery = "UPDATE Notificaciones SET id_usuario=?, id_pedido=?, mensaje=?, fecha=? WHERE id_notificacion=?";
        eliminarQuery = "DELETE FROM Notificaciones WHERE id_notificacion=?";
        seleccionarTodoQuery = "SELECT * FROM Notificaciones";
        seleccionarPorIdQuery = "SELECT * FROM Notificaciones WHERE id_notificacion=?";
    }

    /**
     * Mapea un ResultSet a un objeto Notificaciones.
     * @param rs ResultSet de la consulta
     * @return Objeto Notificaciones
     * @throws SQLException si ocurre un error de SQL
     */
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

    /**
     * Prepara la sentencia para insertar una notificación.
     * @param stmt PreparedStatement
     * @param n Notificaciones a insertar
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    protected void prepararInsert(PreparedStatement stmt, Notificaciones n) throws SQLException {
        stmt.setObject(1, n.getUsuario());
        stmt.setObject(2, n.getPedido());
        stmt.setString(3, n.getMensaje());
        stmt.setTimestamp(4, n.getFecha());
    }

    /**
     * Prepara la sentencia para actualizar una notificación.
     * @param stmt PreparedStatement
     * @param n Notificaciones a actualizar
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    protected void prepararActualizar(PreparedStatement stmt, Notificaciones n) throws SQLException {
        prepararInsert(stmt, n);
        stmt.setLong(5, n.getId());
    }
}