package org.upemor.models.repositories;

import java.sql.*;

import org.upemor.models.Repository;
import org.upemor.models.entities.ReseniaProducto;

public class ReseniaProductoRepository extends Repository<ReseniaProducto> {
    private UsuarioRepository usuarioR;
    private ProductoRepository productoR;

    public ReseniaProductoRepository() {
        usuarioR = new UsuarioRepository();
        productoR = new ProductoRepository();
    }

    @Override
    protected void inicializarQueries() {
        insertarQuery = "INSERT INTO ReseniaProductos (id_usuario, id_producto, calificacion, comentario, fecha) VALUES (?, ?, ?, ?, ?)";
        actualizarQuery = "UPDATE ReseniaProductos SET id_usuario=?, id_producto=?, calificacion=?, comentario=?, fecha=? WHERE id_resenia=?";
        eliminarQuery = "DELETE FROM ReseniaProductos WHERE id_resenia=?";
        seleccionarTodoQuery = "SELECT * FROM ReseniaProductos";
        seleccionarPorIdQuery = "SELECT * FROM ReseniaProductos WHERE id_resenia=?";
    }

    @Override
    protected ReseniaProducto mapear(ResultSet rs) throws SQLException {
        return new ReseniaProducto(
            rs.getLong("id_resenia"),
            usuarioR.obtenerPorId(rs.getLong("id_usuario")),
            productoR.obtenerPorId(rs.getLong("id_producto")),
            rs.getString("calificacion"),
            rs.getString("comentario"),
            rs.getTimestamp("fecha")
        );
    }

    @Override
    protected void prepararInsert(PreparedStatement stmt, ReseniaProducto r) throws SQLException {
        stmt.setObject(1, r.getUsuario());
        stmt.setObject(2, r.getProducto());
        stmt.setString(3, r.getCalificacion());
        stmt.setString(4, r.getComentario());
        stmt.setTimestamp(5, r.getFechaResenia());
    }

    @Override
    protected void prepararActualizar(PreparedStatement stmt, ReseniaProducto r) throws SQLException {
        prepararInsert(stmt, r);
        stmt.setLong(6, r.getId());
    }
}