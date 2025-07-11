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

    public void insertar(Usuarios usuario) {
        usuarioRepository.insertar(usuario);
    }

    public void actualizar(Usuarios usuario) {
        try {
            usuarioRepository.actualizar(usuario);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        usuarioRepository.eliminar(id);
    }
}
