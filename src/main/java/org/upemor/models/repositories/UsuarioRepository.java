package org.upemor.models.repositories;

import org.upemor.models.Repository;
import org.upemor.models.entities.Usuarios;

import java.sql.*;

public class UsuarioRepository extends Repository<Usuarios> {

    public boolean existeMatricula(String matricula) {
        String sql = "SELECT COUNT(*) FROM Usuarios WHERE matricula = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, matricula);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Usuarios buscarPorMatricula(String matricula) {
        String sql = "SELECT * FROM Usuarios WHERE matricula = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, matricula);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return mapear(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean existeCorreo(String correo) {
        String sql = "SELECT COUNT(*) FROM Usuarios WHERE correo = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Usuarios buscarPorCorreo(String correo) {
        String sql = "SELECT * FROM Usuarios WHERE correo = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return mapear(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void inicializarQueries() {
        insertarQuery = "INSERT INTO Usuarios (nombre, apellido_paterno, apellido_materno, correo, contrasenia, telefono, activo, rol, matricula, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        actualizarQuery = "UPDATE Usuarios SET nombre=?, apellido_paterno=?, apellido_materno=?, correo=?, contrasenia=?, telefono=?, activo=?, rol=?, matricula=?, fecha_registro=? WHERE id_usuario=?";
        eliminarQuery = "DELETE FROM Usuarios WHERE id_usuario=?";
        seleccionarTodoQuery = "SELECT * FROM Usuarios";
        seleccionarPorIdQuery = "SELECT * FROM Usuarios WHERE id_usuario=?";
    }

    @Override
    protected Usuarios mapear(ResultSet rs) throws SQLException {
        return new Usuarios(
            rs.getLong("id_usuario"),
            rs.getString("nombre"),
            rs.getString("apellido_paterno"),
            rs.getString("apellido_materno"),
            rs.getString("correo"),
            rs.getString("contrasenia"),
            rs.getString("telefono"),
            rs.getBoolean("activo"),
            rs.getString("rol"),
            rs.getString("matricula"),
            rs.getTimestamp("fecha_registro")
        );
    }

    @Override
    protected void prepararInsert(PreparedStatement stmt, Usuarios u) throws SQLException {
        stmt.setString(1, u.getNombre());
        stmt.setString(2, u.getApellidoPaterno());
        stmt.setString(3, u.getApellidoMaterno());
        stmt.setString(4, u.getCorreo());
        stmt.setString(5, u.getContrasenia());
        stmt.setString(6, u.getTelefono());
        stmt.setBoolean(7, u.isActivo());
        stmt.setString(8, u.getRol());
        stmt.setString(9, u.getMatricula());
        stmt.setTimestamp(10, u.getFechaRegistro());
    }

    @Override
    protected void prepararActualizar(PreparedStatement stmt, Usuarios u) throws SQLException {
        prepararInsert(stmt, u);
        stmt.setLong(11, u.getId());
    }
}
