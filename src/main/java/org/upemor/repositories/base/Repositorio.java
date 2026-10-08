package org.upemor.repositories.base;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.upemor.config.ConexionBD;
import org.upemor.models.base.Entidad;

/**
 * Repositorio base con definición de operaciones CRUD para cualquier entidad.
 * @param <T> Tipo de entidad que maneja el repositorio
 */
public abstract class Repositorio <T extends Entidad> {
    // Conexión a la base de datos
    protected Connection conexion;
    // Tipo de conexion de la base de datos
    protected String tipoConexion;
    
    public Repositorio() {
        ConexionBD db = ConexionBD.getInstania();

        this.conexion = db.getConexion();
        this.tipoConexion = db.getTipoConexion();

        inicializarQueries();
    }

    protected String insertarQuery;
    protected String actualizarQuery;
    protected String eliminarQuery;
    protected String seleccionarTodoQuery;
    protected String seleccionarPorIdQuery;

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
    protected void cargarRelaciones(T entidad) throws SQLException {}

    /**
     * Inserta una entidad en la base de datos.
     * @param entidad Entidad a insertar.
     */
    public boolean insertar(T entidad) {
        try (PreparedStatement stmt = conexion.prepareStatement(insertarQuery)) {
            prepararInsert(stmt, entidad);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al hacer el registro", e);
        }
    }

    /**
     * Actualiza una entidad en la base de datos.
     * @param entidad Entidad a actualizar.
     * @throws SQLException Si ocurre un error en la actualización.
     */
    public boolean actualizar(T entidad) {
        try (PreparedStatement stmt = conexion.prepareStatement(actualizarQuery)) {
            prepararActualizar(stmt, entidad);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar entidad", e);
        }
    }

    /**
     * Elimina una entidad por su ID.
     * @param id Identificador de la entidad a eliminar.
     */
    public boolean eliminar(long id) {
        try (PreparedStatement stmt = conexion.prepareStatement(eliminarQuery)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar entidad", e);
        }
    }

    /**
     * Obtiene todas las entidades de la base de datos.
     * @return Lista de entidades.
     */
    public List<T> obtenerTodos() {
        return ejecutarConsulta(seleccionarTodoQuery, new Object[0]);
    }

    /**
     * Obtiene una entidad por su ID.
     * @param id Identificador de la entidad.
     * @return Entidad encontrada o null si no existe.
     * @throws SQLException Si ocurre un error en la consulta.
     */
    public T obtenerPorId(long id) {
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
     * Ejecuta una consulta SQL parametrizada y devuelve una lista de entidades mapeadas.
     * Este método es útil para realizar búsquedas personalizadas fuera del CRUD,
     * como filtros por atributos, relaciones entre tablas o condiciones complejas.
     *
     * @param sql Consulta SQL con placeholders (?)
     * @param parametros Parámetros que serán insertados en la consulta en orden
     * @return Lista de objetos del tipo T mapeados desde el resultado de la consulta
     */
    public List<T> ejecutarConsulta(String sql, Object[] parametros) {
        List<T> lista = new ArrayList<>();

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            
            for (int i = 0; i < parametros.length; i++) stmt.setObject(i + 1, parametros[i]);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    T entidad = mapear(rs);
                    cargarRelaciones(entidad);
                    lista.add(entidad);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return lista;
    }

    /**
     * Actualiza un campo específico de una fila en la tabla indicada, utilizando su clave primaria.
     * Este método es útil para realizar actualizaciones parciales sin necesidad de modificar
     * todos los campos de una entidad.
     *
     * @param tabla Nombre de la tabla en la base de datos
     * @param campo Nombre del campo a actualizar
     * @param valor Nuevo valor que se asignará al campo
     * @param id ID de la fila que se desea actualizar
     * @param idCampo Nombre de la columna que actúa como clave primaria o identificador
     * @return true si la operación se ejecutó con éxito, false en caso contrario
     */
    public boolean actualizarCampo(String tabla, String campo, Object valor, long id, String idCampo) {
        String sql = "UPDATE " + tabla + " SET " + campo + " = ? WHERE " + idCampo + " = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setObject(1, valor);
            stmt.setLong(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene el último ID insertado en la base de datos para una tabla.
     * Utiliza el comando correspondiente según el tipo de base de datos:
     * - Para MariaDB: LAST_INSERT_ID()
     * - Para SQLite: last_insert_rowid()
     * 
     * Este método es útil cuando se requiere conocer el identificador autogenerado
     * después de una inserción para enlazar registros relacionados o confirmar la creación.
     * @return ID generado automáticamente en la última inserción o 0 si no se encuentra
     */
    public long obtenerUltimoIdInsertado() {
        String query = tipoConexion.equals("mariadb")
            ? "SELECT LAST_INSERT_ID() AS id"
            : "SELECT last_insert_rowid() AS id";

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) return rs.getLong("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
