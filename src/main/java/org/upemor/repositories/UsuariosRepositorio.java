package org.upemor.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.upemor.models.Usuarios;
import org.upemor.repositories.base.Repositorio;

public class UsuariosRepositorio extends Repositorio<Usuarios> {
    /**
     * Inicializa las consultas SQL para operaciones CRUD.
     */
    @Override
    protected void inicializarQueries() {
        insertarQuery = "INSERT INTO Usuarios (nombre, apellido_paterno, apellido_materno, correo, contrasenia, telefono, activo, rol, matricula) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        actualizarQuery = "UPDATE Usuarios SET nombre=?, apellido_paterno=?, apellido_materno=?, telefono=?, activo=? WHERE id_usuario=?";
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
        stmt.setBoolean(5, u.isActivo());
        stmt.setLong(6, u.getId());
    }
    
    /**
     * Busca usuarios por nombre (coincidencia parcial).
     * @param nombre nombre o parte del nombre a buscar
     * @return lista de usuarios que coinciden
     */
    public List<Usuarios> buscarPorNombre(String nombre) {
        final String sql = "SELECT * FROM Usuarios WHERE LOWER(nombre) LIKE ?";
        return ejecutarConsulta(sql, new Object[] {"%" + nombre.toLowerCase() + "%"});
    }
    
    /**
     * Busca el usuario que contenga la matricula solicitada
     * @param matricula matricula a buscar
     * @return Usuario encontrado o null si no existe
     */
    public Usuarios buscarPorMatricula(String matricula) {
        final String sql = "SELECT * FROM Usuarios WHERE matricula = ?";
        
        List<Usuarios> usuarios = ejecutarConsulta(sql, new Object[] {matricula});
        
        return usuarios.size() > 0 ? usuarios.get(0) : null;
    }

    /**
     * Busca un usuario por correo electrónico.
     * @param correo Correo a buscar
     * @return Usuario encontrado o null
     */
    public Usuarios buscarPorCorreo(String correo) {
        final String sql = "SELECT * FROM Usuarios WHERE correo = ?";
        List<Usuarios> usuarios = ejecutarConsulta(sql, new Object[] {correo});
        return usuarios.size() > 0 ? usuarios.get(0) : null;
    }

    /**
     * Obtiene usuarios por rol y estado de activación.
     * @param rol rol del usuario (ejemplo: "empleado")
     * @param activo true si está activo, false si está inactivo
     * @return lista de usuarios que cumplen con el filtro
     */
    public List<Usuarios> obtenerPorRolConActivo(String rol, boolean activo) {
        final String sql = "SELECT * FROM Usuarios WHERE rol = ? AND activo = ?";
        return ejecutarConsulta(sql, new Object[] {rol, activo});
    }
    /**
     * Cambia el estado de activo o desactivo del usuario
     * @param id Id del usuario
     * @param activo true para activar el usuario, o false para desactivar
     * @return true si se ejecuto con exito false si no
     */
    public boolean cambiarActivoUsuario(long id, boolean activo) {
        return actualizarCampo("Usuarios", "activo", activo, id, "id_usuario");
    }

}
