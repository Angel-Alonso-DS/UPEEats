package org.upemor.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.upemor.utils.BDConexion;

public abstract class Repository <T extends Entity>{
    private BDConexion conexion;
    private String seleccionarTodoSQL;
    private String seleccionSQL;
    private String insertarSQL;
    private String eliminarSQL;
    private String actualizarSQL;

    public Repository() {
        conexion = BDConexion.getInstance();
        setSQL();
    }

    protected abstract void setSQL();
    protected abstract T mapear(ResultSet rs) throws SQLException;
    protected abstract void prepararInsert(PreparedStatement stmt, T entidad) throws SQLException;

    public List<T> obtenerTodos() {
        List<T> lista = new ArrayList<>();
        try (Statement stmt = conexion.getConexion().createStatement();
             ResultSet rs = stmt.executeQuery(seleccionarTodoSQL)) {

            while (rs.next()) lista.add(mapear(rs));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void guardar(T entidad) {
        try (PreparedStatement stmt = conexion.getConexion().prepareStatement(insertarSQL)) {
            prepararInsert(stmt, entidad);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        try (PreparedStatement stmt = conexion.getConexion().prepareStatement(eliminarSQL)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
