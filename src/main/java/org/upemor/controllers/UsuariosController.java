package org.upemor.controllers;

import org.upemor.models.entities.Usuarios;
import org.upemor.models.repositories.UsuarioRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para la gestión de usuarios en la cafetería.
 */
public class UsuariosController {
    private final UsuarioRepository usuarioRepository = new UsuarioRepository();

    public List<Usuarios> obtenerTodos() {
        return usuarioRepository.obtenerTodos();
    }

    public Usuarios buscarPorId(long id) {
        try {
            return usuarioRepository.obtenerPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Usuarios buscarPorMatricula(String matricula) {
        return usuarioRepository.buscarPorMatricula(matricula);
    }
    public Usuarios buscarPorCorreo(String correo) {
        return usuarioRepository.buscarPorCorreo(correo);
    }


    public void insertar(Usuarios usuario) {
        usuarioRepository.insertar(usuario);
    }

    public void editarUsuario(long id, String nuevoNombre, String nuevoApellidoPaterno, String nuevoApellidoMaterno, String nuevoTelefono) {
        try {
            Usuarios usuario = usuarioRepository.obtenerPorId(id);
            
            if (usuario == null) throw new IllegalArgumentException("Usuario no encontrado");
    
            usuario.setNombre(nuevoNombre);
            usuario.setApellidoPaterno(nuevoApellidoPaterno);
            usuario.setApellidoMaterno(nuevoApellidoMaterno);
            usuario.setTelefono(nuevoTelefono);
            usuarioRepository.actualizar(usuario);

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el usuario", e);
        }
    }

    public void eliminar(int id) {
        usuarioRepository.eliminar(id);
    }
}
