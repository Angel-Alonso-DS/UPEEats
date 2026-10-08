package org.upemor.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.upemor.models.ReseniaPedido;
import org.upemor.repositories.base.Repositorio;


/**
 * Repositorio para la gestión de entidades ReseniaPedido en la base de datos.
 * Proporciona métodos CRUD y mapeo de datos para reseñas de pedidos.
 */
public class ReseniaPedidoRepositorio extends Repositorio<ReseniaPedido> {
    /** Repositorio auxiliar para operaciones sobre usuarios. */
    private final UsuariosRepositorio usuariosRPS = new UsuariosRepositorio();
    /** Repositorio auxiliar para operaciones sobre pedidos. */
    private final PedidosRepositorio pedidosRPS = new PedidosRepositorio();

    /**
     * Inicializa las consultas SQL utilizadas por el repositorio.
     */
    @Override
    protected void inicializarQueries() {
        insertarQuery = "INSERT INTO ReseniaPedido (id_usuario, id_pedido, calificacion, comentario, fecha) VALUES (?, ?, ?, ?, ?)";
        actualizarQuery = "UPDATE ReseniaPedido SET id_usuario=?, id_pedido=?, calificacion=?, comentario=?, fecha=? WHERE id_resenia=?";
        eliminarQuery = "DELETE FROM ReseniaPedido WHERE id_resenia=?";
        seleccionarTodoQuery = "SELECT * FROM ReseniaPedido";
        seleccionarPorIdQuery = "SELECT * FROM ReseniaPedido WHERE id_resenia=?";
    }

    /**
     * Mapea un ResultSet de SQL a una instancia de ReseniaPedido.
     * @param rs ResultSet con los datos de la consulta.
     * @return Instancia de ReseniaPedido mapeada.
     * @throws SQLException Si ocurre un error al acceder a los datos.
     */
    @Override
    protected ReseniaPedido mapear(ResultSet rs) throws SQLException {
        return new ReseniaPedido(
            rs.getLong("id_resenia"),
            usuariosRPS.obtenerPorId(rs.getLong("id_usuario")),
            pedidosRPS.obtenerPorId(rs.getLong("id_pedido")),
            rs.getInt("calificacion"),
            rs.getString("comentario"),
            rs.getTimestamp("fecha")
        );
    }

    /**
     * Prepara un PreparedStatement para insertar una ReseniaPedido en la base de datos.
     * @param stmt PreparedStatement a preparar.
     * @param r ReseniaPedido a insertar.
     * @throws SQLException Si ocurre un error al establecer los parámetros.
     */
    @Override
    protected void prepararInsert(PreparedStatement stmt, ReseniaPedido r) throws SQLException {
        stmt.setObject(1, r.getUsuario());
        stmt.setObject(2, r.getPedido());
        stmt.setInt(3, r.getCalificacion());
        stmt.setString(4, r.getComentario());
        stmt.setTimestamp(5, r.getFechaResenia());
    }

    /**
     * Prepara un PreparedStatement para actualizar una ReseniaPedido en la base de datos.
     * @param stmt PreparedStatement a preparar.
     * @param r ReseniaPedido a actualizar.
     * @throws SQLException Si ocurre un error al establecer los parámetros.
     */
    @Override
    protected void prepararActualizar(PreparedStatement stmt, ReseniaPedido r) throws SQLException {
        prepararInsert(stmt, r);
        stmt.setLong(6, r.getId());
    }
    
}
