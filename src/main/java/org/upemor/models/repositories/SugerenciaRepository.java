package org.upemor.models.repositories;

import java.sql.*;

import org.upemor.models.Repository;
import org.upemor.models.entities.Sugerencias;

public class SugerenciaRepository extends Repository<Sugerencias> {
    private UsuarioRepository usuarioR;

    public SugerenciaRepository() {
        usuarioR = new UsuarioRepository();
    }

    @Override
    protected void inicializarQueries() {
        insertarQuery = "INSERT INTO Sugerencias (id_usuario, tipo_dieta, alergias, comentarios, fecha_registro) VALUES (?, ?, ?, ?, ?)";
        actualizarQuery = "UPDATE Sugerencias SET id_usuario=?, tipo_dieta=?, alergias=?, comentarios=?, fecha_registro=? WHERE id_sugerencia=?";
        eliminarQuery = "DELETE FROM Sugerencias WHERE id_sugerencia=?";
        seleccionarTodoQuery = "SELECT * FROM Sugerencias";
        seleccionarPorIdQuery = "SELECT * FROM Sugerencias WHERE id_sugerencia=?";
    }

    @Override
    protected Sugerencias mapear(ResultSet rs) throws SQLException {
        return new Sugerencias(
            rs.getLong("id_sugerencia"),
            usuarioR.obtenerPorId(rs.getLong("id_usuario")),
            rs.getString("tipo_dieta"),
            rs.getString("alergias"),
            rs.getString("comentarios"),
            rs.getTimestamp("fecha_registro")
        );
    }

    @Override
    protected void prepararInsert(PreparedStatement stmt, Sugerencias s) throws SQLException {
        stmt.setObject(1, s.getUsuario());
        stmt.setString(2, s.getTipoDieta());
        stmt.setString(3, s.getAlergias());
        stmt.setString(4, s.getComentarios());
        stmt.setTimestamp(5, s.getFechaSugerencia());
    }

    @Override
    protected void prepararActualizar(PreparedStatement stmt, Sugerencias s) throws SQLException {
        prepararInsert(stmt, s);
        stmt.setLong(6, s.getId());
    }
}