package org.upemor.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.upemor.models.Categorias;
import org.upemor.repositories.base.Repositorio;

/**
 * Repositorio para operaciones CRUD sobre la entidad Categorias.
 */
public class CategoriasRepositorio extends Repositorio<Categorias>{
    /**
     * Inicializa las consultas SQL para operaciones CRUD.
     */
    @Override
    protected void inicializarQueries() {
        insertarQuery = "INSERT INTO Categorias (nombre, descripcion) VALUES (?, ?)";
        actualizarQuery = "UPDATE Categorias SET nombre = ?, descripcion = ? WHERE id_categoria = ?";
        eliminarQuery = "DELETE FROM Categorias WHERE id_categoria = ?";
        seleccionarTodoQuery = "SELECT * FROM Categorias";
        seleccionarPorIdQuery = "SELECT * FROM Categorias WHERE id_categoria = ?";
    }

    /**
     * Mapea un ResultSet a un objeto Categorias.
     * @param rs ResultSet de la consulta
     * @return Objeto Categorias
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    protected Categorias mapear(ResultSet rs) throws SQLException {
        return new Categorias(
            rs.getLong("id_categoria"),
            rs.getString("nombre"),
            rs.getString("descripcion"),
            rs.getTimestamp("fecha_creacion")
        );
    }

    /**
     * Prepara la sentencia para insertar una categoría.
     * @param stmt PreparedStatement
     * @param c Categoría a insertar
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    protected void prepararInsert(PreparedStatement stmt, Categorias c) throws SQLException {
        stmt.setString(1, c.getNombre());
        stmt.setString(2, c.getDescripcion());
    }

    /**
     * Prepara la sentencia para actualizar una categoría.
     * @param stmt PreparedStatement
     * @param c Categoría a actualizar
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    protected void prepararActualizar(PreparedStatement stmt, Categorias c) throws SQLException {
        prepararInsert(stmt, c);
        stmt.setLong(3, c.getId());
    }
    
    public List<Categorias> buscarPorNombre(String nombre) {
        final String sql = "SELECT * FROM Categorias WHERE LOWER(nombre) LIKE ?";
        return ejecutarConsulta(sql, new Object[] {"%" + nombre.toLowerCase() + "%"});
    }
}
