package org.upemor.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.upemor.utils.BDConexion;

/**
 * Clase abstracta Repository
 * Proporciona métodos genéricos para la gestión de entidades en la base de datos.
 * Define operaciones CRUD (crear, leer, actualizar, eliminar) y requiere que las subclases implementen el mapeo y la preparación de sentencias.
 *
 * @param <T> Tipo de entidad que extiende de Entity.
 */
public abstract class Repository <T extends Entity>{
    // Conexión a la base de datos
    protected Connection conexion;
    // Query para seleccionar todos los registros
    protected String seleccionarTodoQuery;
    // Query para seleccionar por ID
    protected String seleccionarPorIdQuery;
    // Query para insertar un registro
    protected String insertarQuery;
    // Query para eliminar un registro
    protected String eliminarQuery;
    // Query para actualizar un registro
    protected String actualizarQuery;
    
    /**
     * Inicializa las consultas SQL necesarias para la entidad.
     * Debe ser implementado por las subclases.
     */
    protected abstract void inicializarQueries();
    /**
     * Mapea un ResultSet a una entidad.
     * Debe ser implementado por las subclases.
     */
    protected abstract T mapear(ResultSet rs) throws SQLException;
    /**
     * Prepara la sentencia para insertar una entidad.
     * Debe ser implementado por las subclases.
     */
    protected abstract void prepararInsert(PreparedStatement stmt, T entidad) throws SQLException;
    /**
     * Prepara la sentencia para actualizar una entidad.
     * Debe ser implementado por las subclases.
     */
    protected abstract void prepararActualizar(PreparedStatement stmt, T entidad) throws SQLException;
    
    /**
     * Permite cargar relaciones adicionales para la entidad.
     * Puede ser sobrescrito por las subclases si es necesario.
     */
    protected void cargarRelaciones(T entidad) throws SQLException {
        // Implementación opcional en subclases
    }
    
    /**
     * Inserta una entidad en la base de datos.
     * @param entidad Entidad a insertar.
     */
    public void insertar(T entidad) {
        try (PreparedStatement stmt = conexion.prepareStatement(insertarQuery)) {
            prepararInsert(stmt, entidad);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Elimina una entidad por su ID.
     * @param id Identificador de la entidad a eliminar.
     */
    public void eliminar(long id) {
        try (PreparedStatement stmt = conexion.prepareStatement(eliminarQuery)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza una entidad en la base de datos.
     * @param entidad Entidad a actualizar.
     * @throws SQLException Si ocurre un error en la actualización.
     */
    public void actualizar(T entidad) throws SQLException {
        try (PreparedStatement stmt = conexion.prepareStatement(actualizarQuery)) {
            prepararActualizar(stmt, entidad);
            stmt.executeUpdate();
        }
    }
    
    /**
     * Obtiene todas las entidades de la base de datos.
     * @return Lista de entidades.
     */
    public List<T> obtenerTodos() {
        List<T> lista = new ArrayList<>();
        try (Statement stmt = conexion.createStatement();
        ResultSet rs = stmt.executeQuery(seleccionarTodoQuery)) {
            while (rs.next()) {
                T entidad = mapear(rs);
                cargarRelaciones(entidad);
                lista.add(entidad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    /**
     * Obtiene una entidad por su ID.
     * @param id Identificador de la entidad.
     * @return Entidad encontrada o null si no existe.
     * @throws SQLException Si ocurre un error en la consulta.
     */
    public T obtenerPorId(long id) throws SQLException {
        try (PreparedStatement stmt = conexion.prepareStatement(seleccionarPorIdQuery)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    T entidad = mapear(rs);
                    cargarRelaciones(entidad);
                    return entidad;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Constructor de la clase Repository.
     * Inicializa la conexión y las consultas SQL.
     */
    public Repository() {
        conexion = BDConexion.getInstance().getConexion();
        inicializarQueries();
    }
}
