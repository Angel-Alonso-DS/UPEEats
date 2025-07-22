package org.upemor.controllers;

import org.upemor.models.entities.Usuarios;
import org.upemor.models.repositories.UsuarioRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para la gestión de usuarios en la cafetería.
 * Proporciona métodos para consultar, insertar, editar y eliminar usuarios.
 * Utiliza UsuarioRepository para interactuar con la base de datos.
 */
public class UsuariosController {
    // Repositorio para operaciones sobre usuarios
    private final UsuarioRepository usuarioRepository = new UsuarioRepository();

    /**
     * Obtiene la lista de todos los usuarios registrados.
     * @return Lista de usuarios.
     */
    public List<Usuarios> obtenerTodos() {
        return usuarioRepository.obtenerTodos();
    }

    /**
     * Busca un usuario por su ID.
     * @param id Identificador del usuario.
     * @return Usuario encontrado o null si no existe.
     */
    public Usuarios buscarPorId(long id) {
        try {
            return usuarioRepository.obtenerPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Busca un usuario por su matrícula.
     * @param matricula Matrícula del usuario.
     * @return Usuario encontrado o null si no existe.
     */
    public Usuarios buscarPorMatricula(String matricula) {
        return usuarioRepository.buscarPorMatricula(matricula);
    }

    /**
     * Busca un usuario por su correo electrónico.
     * @param correo Correo del usuario.
     * @return Usuario encontrado o null si no existe.
     */
    public Usuarios buscarPorCorreo(String correo) {
        return usuarioRepository.buscarPorCorreo(correo);
    }

    /**
     * Inserta un nuevo usuario en la base de datos.
     * @param usuario Usuario a insertar.
     */
    public void insertar(Usuarios usuario) {
        usuarioRepository.insertar(usuario);
    }

    /**
     * Edita la información personal de un usuario.
     * Busca el usuario por ID, actualiza los datos y guarda los cambios.
     *
     * @param id                    Identificador del usuario.
     * @param nuevoNombre           Nuevo nombre.
     * @param nuevoApellidoPaterno  Nuevo apellido paterno.
     * @param nuevoApellidoMaterno  Nuevo apellido materno.
     * @param nuevoTelefono         Nuevo teléfono.
     */
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

    /**
     * Elimina un usuario de la base de datos por su ID.
     * @param id Identificador del usuario a eliminar.
     */
    public void eliminar(int id) {
        usuarioRepository.eliminar(id);
    }
}
