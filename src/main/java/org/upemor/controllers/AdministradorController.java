package org.upemor.controllers;

import org.upemor.models.entities.Usuarios;
import org.upemor.models.repositories.UsuarioRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para operaciones administrativas sobre usuarios.
 * Permite aprobar empleados y gestionar usuarios.
 */
public class AdministradorController {
    private final UsuarioRepository usuarioRepository = new UsuarioRepository();

    /**
     * Obtiene todos los usuarios empleados pendientes de confirmación.
     * @return lista de empleados inactivos
     */
    public List<Usuarios> obtenerEmpleadosPendientes() {
        return usuarioRepository.obtenerPorRolYActivo("empleado", false);
    }

    /**
     * Aprueba (activa) un empleado para que pueda acceder al sistema.
     * @param idUsuario identificador del usuario empleado
     * @return true si la operación fue exitosa
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public boolean aprobarEmpleado(long idUsuario) throws SQLException {
        Usuarios empleado = usuarioRepository.obtenerPorId(idUsuario);
        if (empleado != null && "empleado".equalsIgnoreCase(empleado.getRol()) && !empleado.isActivo()) {
            empleado.setActivo(true);
            usuarioRepository.actualizar(empleado);
            return true;
        }
        return false;
    }

    /**
     * Desactiva (suspende) un usuario.
     * @param idUsuario identificador del usuario
     * @return true si la operación fue exitosa
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public boolean desactivarUsuario(long idUsuario) throws SQLException {
        Usuarios usuario = usuarioRepository.obtenerPorId(idUsuario);
        if (usuario != null && usuario.isActivo()) {
            usuario.setActivo(false);
            usuarioRepository.actualizar(usuario);
            return true;
        }
        return false;
    }
}
