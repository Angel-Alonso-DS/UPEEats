package org.upemor.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.upemor.models.Sugerencias;
import org.upemor.repositories.base.Repositorio;


/**
 * Repositorio para la gestión de entidades Sugerencias en la base de datos.
 * Proporciona métodos CRUD y consultas especializadas para sugerencias de usuarios.
 */
public class SugerenciasRepositorio extends Repositorio<Sugerencias> {
    /** Repositorio auxiliar para operaciones sobre usuarios. */
    private static final UsuariosRepositorio usuariosRPS = new UsuariosRepositorio();

    /**
     * Inicializa las consultas SQL utilizadas por el repositorio.
     */
    @Override
    protected void inicializarQueries() {
        insertarQuery = "INSERT INTO Sugerencias (id_usuario, asunto, comentario) VALUES (?, ?, ?)";
        actualizarQuery = "UPDATE Sugerencias SET id_usuario=?, asunto=?, comentario=?, estado=?, fecha_revision=? WHERE id_sugerencia=?";
        eliminarQuery = "DELETE FROM Sugerencias WHERE id_sugerencia=?";
        seleccionarTodoQuery = "SELECT * FROM Sugerencias";
        seleccionarPorIdQuery = "SELECT * FROM Sugerencias WHERE id_sugerencia=?";
    }

    /**
     * Mapea un ResultSet de SQL a una instancia de Sugerencias.
     * @param rs ResultSet con los datos de la consulta.
     * @return Instancia de Sugerencias mapeada.
     * @throws SQLException Si ocurre un error al acceder a los datos.
     */
    @Override
    protected Sugerencias mapear(ResultSet rs) throws SQLException {
        return new Sugerencias(
            rs.getLong("id_sugerencia"),
            usuariosRPS.obtenerPorId(rs.getLong("id_usuario")),
            rs.getString("asunto"),
            rs.getString("comentario"),
            rs.getString("estado"),
            rs.getTimestamp("fecha_registro"),
            rs.getTimestamp("fecha_revision")
        );
    }

    /**
     * Prepara un PreparedStatement para insertar una Sugerencia en la base de datos.
     * @param stmt PreparedStatement a preparar.
     * @param s Sugerencias a insertar.
     * @throws SQLException Si ocurre un error al establecer los parámetros.
     */
    @Override
    protected void prepararInsert(PreparedStatement stmt, Sugerencias s) throws SQLException {
        stmt.setLong(1, s.getUsuario().getId());
        stmt.setString(2, s.getAsunto());
        stmt.setString(3, s.getComentario());
    }

    /**
     * Prepara un PreparedStatement para actualizar una Sugerencia en la base de datos.
     * @param stmt PreparedStatement a preparar.
     * @param s Sugerencias a actualizar.
     * @throws SQLException Si ocurre un error al establecer los parámetros.
     */
    @Override
    protected void prepararActualizar(PreparedStatement stmt, Sugerencias s) throws SQLException {
        stmt.setLong(1, s.getUsuario().getId());
        stmt.setString(2, s.getAsunto());
        stmt.setString(3, s.getComentario());
        stmt.setString(4, s.getEstado());
        stmt.setTimestamp(5, s.getFechaRevision());
        stmt.setLong(6, s.getId());
    }

    /**
     * Obtiene todos las sugerencias que tengan el id del usuario
     * @param idUsuario id Del usuario
     * @return Una lista de las sugerencias con el mismo id
     */
    public List<Sugerencias> obtenerTodoPorIdUsuario(long idUsuario) {
        final String sql = "SELECT * FROM Sugerencias WHERE id_usuario=?";
        return ejecutarConsulta(sql, new Object[] {idUsuario});
    }

    /**
     * Bsuca de todas las sugerencias cuales tienen un asunto parecido
     * @param asunto Asunto a buscar
     * @return Lista de sugerencias
     */
    public List<Sugerencias> buscarPorAsunto(String asunto) {
        final String sql = "SELECT * FROM Sugerencias WHERE LOWER(asunto) LIKE ?";
        return ejecutarConsulta(sql, new Object[] {"%" + asunto.toLowerCase() + "%"});
    }

    /**
     * Bsuca de todas las sugerencias cuales tienen un asunto parecido
     * @param asunto Asunto a buscar
     * @param idUsuario id del usuario asociado
     * @return Lista de sugerencias
     */
    public List<Sugerencias> buscarPorAsuntoConIdUsuario(long idUsuario, String asunto) {
        final String sql = "SELECT * FROM Sugerencias WHERE id_usuario=? AND LOWER(asunto) LIKE ?";
        return ejecutarConsulta(sql, new Object[] {idUsuario, "%" + asunto.toLowerCase() + "%"});
    }






    
}
