package org.upemor.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.upemor.utils.BDConexion;

public abstract class Repository <T extends Entity>{
    protected Connection conexion;
    protected String seleccionarTodoQuery;
    protected String seleccionarPorIdQuery;
    protected String insertarQuery;
    protected String eliminarQuery;
    protected String actualizarQuery;
    
    protected abstract void inicializarQueries();
    protected abstract T mapear(ResultSet rs) throws SQLException;
    protected abstract void prepararInsert(PreparedStatement stmt, T entidad) throws SQLException;
    protected abstract void prepararActualizar(PreparedStatement stmt, T entidad) throws SQLException;
    
    
    protected void cargarRelaciones(T entidad) throws SQLException {
        
    }
    
    public void insertar(T entidad) {
        try (PreparedStatement stmt = conexion.prepareStatement(insertarQuery)) {
            prepararInsert(stmt, entidad);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void eliminar(long id) {
        try (PreparedStatement stmt = conexion.prepareStatement(eliminarQuery)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizar(T entidad) throws SQLException {
        try (PreparedStatement stmt = conexion.prepareStatement(actualizarQuery)) {
            prepararActualizar(stmt, entidad);
            stmt.executeUpdate();
            
        }
    }
    
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

    
    public Repository() {
        conexion = BDConexion.getInstance().getConexion();
        inicializarQueries();
    }
}
