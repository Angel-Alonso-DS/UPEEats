package org.upemor.models.repositories;

import java.sql.*;

import org.upemor.models.Repository;
import org.upemor.models.entities.Categorias;

public class CategoriasRepository extends Repository<Categorias> {

    @Override
    protected void inicializarQueries() {
        insertarQuery = "INSERT INTO Categorias (nombre, descripcion) VALUES (?, ?)";
        actualizarQuery = "UPDATE Categorias SET nombre = ?, descripcion = ? WHERE id_categoria = ?";
        eliminarQuery = "DELETE FROM Categorias WHERE id_categoria = ?";
        seleccionarTodoQuery = "SELECT * FROM Categorias";
        seleccionarPorIdQuery = "SELECT * FROM Categorias WHERE id_categoria = ?";
    }

    @Override
    protected Categorias mapear(ResultSet rs) throws SQLException {
        return new Categorias(
            rs.getLong("id_categoria"),
            rs.getString("nombre"),
            rs.getString("descripcion")
        );
    }

    @Override
    protected void prepararInsert(PreparedStatement stmt, Categorias c) throws SQLException {
        stmt.setString(1, c.getNombre());
        stmt.setString(2, c.getDescripcion());
    }

    @Override
    protected void prepararActualizar(PreparedStatement stmt, Categorias c) throws SQLException {
        prepararInsert(stmt, c);
        stmt.setLong(3, c.getId());
    }
}