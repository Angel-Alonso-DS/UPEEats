package org.upemor.models.repositories;

import org.upemor.models.Repository;
import org.upemor.models.entities.Usuarios;

import java.sql.*;
import java.util.List;

/**
 * Repositorio para operaciones CRUD y consultas específicas sobre la entidad Usuarios.
 * Permite buscar por matrícula, correo y verificar existencia de usuarios.
 */
public class UsuarioRepository extends Repository<Usuarios> {
    
    /**
     * Obtiene usuarios por rol y estado de activación.
     * @param rol rol del usuario (ejemplo: "empleado")
     * @param activo true si está activo, false si está inactivo
     * @return lista de usuarios que cumplen con el filtro
     */
    public List<Usuarios> obtenerPorRolYActivo(String rol, boolean activo) {
        List<Usuarios> lista = new java.util.ArrayList<>();
        String sql = "SELECT * FROM Usuarios WHERE rol = ? AND activo = ?";
        try (java.sql.PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, rol);
            stmt.setBoolean(2, activo);
            try (java.sql.ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Usuarios usuario = mapear(rs);
                    lista.add(usuario);
                }
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Verifica si existe una matrícula en la base de datos.
     * @param matricula Matrícula a buscar
     * @return true si existe, false en caso contrario
     */
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

    /**
     * Busca un usuario por matrícula.
     * @param matricula Matrícula a buscar
     * @return Usuario encontrado o null
     */
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

    /**
     * Verifica si existe un correo en la base de datos.
     * @param correo Correo a buscar
     * @return true si existe, false en caso contrario
     */
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

    /**
     * Busca un usuario por correo electrónico.
     * @param correo Correo a buscar
     * @return Usuario encontrado o null
     */
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

    /**
     * Inicializa las consultas SQL para operaciones CRUD.
     */
    @Override
    protected void inicializarQueries() {
        insertarQuery = "INSERT INTO Usuarios (nombre, apellido_paterno, apellido_materno, correo, contrasenia, telefono, activo, rol, matricula, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        actualizarQuery = "UPDATE Usuarios SET nombre=?, apellido_paterno=?, apellido_materno=?, telefono=? WHERE id_usuario=?";
        eliminarQuery = "DELETE FROM Usuarios WHERE id_usuario=?";
        seleccionarTodoQuery = "SELECT * FROM Usuarios";
        seleccionarPorIdQuery = "SELECT * FROM Usuarios WHERE id_usuario=?";
    }

    /**
     * Mapea un ResultSet a un objeto Usuarios.
     * @param rs ResultSet de la consulta
     * @return Objeto Usuarios
     * @throws SQLException si ocurre un error de SQL
     */
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

    /**
     * Prepara la sentencia para insertar un usuario.
     * @param stmt PreparedStatement
     * @param u Usuario a insertar
     * @throws SQLException si ocurre un error de SQL
     */
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

    /**
     * Prepara la sentencia para actualizar un usuario.
     * @param stmt PreparedStatement
     * @param u Usuario a actualizar
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    protected void prepararActualizar(PreparedStatement stmt, Usuarios u) throws SQLException {
        stmt.setString(1, u.getNombre());
        stmt.setString(2, u.getApellidoPaterno());
        stmt.setString(3, u.getApellidoMaterno());
        stmt.setString(4, u.getTelefono());
        stmt.setLong(5, u.getId());
    }
}
