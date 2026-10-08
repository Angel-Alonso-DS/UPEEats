package org.upemor.controllers;

import java.util.List;

import org.upemor.models.Usuarios;
import org.upemor.repositories.UsuariosRepositorio;

public class UsuariosControlador {
    private final UsuariosRepositorio usuariosRPS = new UsuariosRepositorio();

    /**
     * Obtiene la lista de todos los usuarios registrados.
     * @return Lista de usuarios.
     */
    public List<Usuarios> obtenerTodos() {
        return usuariosRPS.obtenerTodos();
    }

    /**
     * Busca un usuario por su ID.
     * @param id Identificador del usuario.
     * @return Usuario encontrado o null si no existe.
     */
    public Usuarios buscarPorId(long id) {
        return usuariosRPS.obtenerPorId(id);
    }

    public List<Usuarios> buscarPorNombre(String nombre) {
        return usuariosRPS.buscarPorNombre(nombre);
    }

    /**
     * Busca un usuario por su matrícula.
     * @param matricula Matrícula del usuario.
     * @return Usuario encontrado o null si no existe.
     */
    public Usuarios buscarPorMatricula(String matricula) {
        return usuariosRPS.buscarPorMatricula(matricula);
    }

    /**
     * Busca un usuario por su correo electrónico.
     * @param correo Correo del usuario.
     * @return Usuario encontrado o null si no existe.
     */
    public Usuarios buscarPorCorreo(String correo) {
        return usuariosRPS.buscarPorCorreo(correo);
    }

    /**
     * Se consulta en la base de datos si el usuario esta activo o no
     * @param idUsuario id del usuario a buscar
     * @return retorna true o false si el usuario esta activo o si no existe
     */
    public boolean isUsuarioActivo(long idUsuario) {
        Usuarios u = usuariosRPS.obtenerPorId(idUsuario);
        
        if (u == null) return false;
        
        return u.isActivo();
    }

    /**
     * Obtiene todos los usuarios empleados pendientes de confirmación.
     * @return lista de empleados inactivos
     */
    public List<Usuarios> obtenerEmpleadosPendientes() {
        return usuariosRPS.obtenerPorRolConActivo("empleado", false);
    }

    /**
     * Obtiene todos los usuarios adminstradores pendientes de confirmación.
     * @return lista de empleados inactivos
     */
    public List<Usuarios> obtenerAdministradoresPendientes() {
        return usuariosRPS.obtenerPorRolConActivo("adminstrador", false);
    }

    /**
     * Permite el acceso de un estudiante validando matrícula y contraseña.
     * @param matricula Matrícula del estudiante
     * @param contrasenia Contraseña
     * @return Objeto Usuarios si el acceso es exitoso, de caso contrario lanza una excepción
     */
    public Usuarios accesoEstudiante(String matricula, String contrasenia) {
        Usuarios usuario = usuariosRPS.buscarPorMatricula(matricula);

        if (usuario == null) throw new IllegalArgumentException("Matrícula o contraseña invalida");

        if (!usuario.getContrasenia().equals(contrasenia)) throw new IllegalArgumentException("Matrícula o contraseña invalida");
        
        if (!usuario.isActivo()) throw new IllegalArgumentException("Cuenta inactiva");

        return usuario;
    }

    /**
     * Permite el acceso de un empleado o administrador validando correo y contraseña.
     * @param correo Correo electrónico
     * @param contrasenia Contraseña
     * @return Objeto Usuarios si el acceso es exitoso, null en caso contrario
     */
    public Usuarios accesoEmpleado(String correo, String contrasenia) {
        Usuarios usuario = usuariosRPS.buscarPorCorreo(correo);

        if (usuario == null) throw new IllegalArgumentException("Correo o contraseña incorrectos");

        if (!usuario.getContrasenia().equals(contrasenia)) throw new IllegalArgumentException("Correo o contraseña incorrectos");

        if (!usuario.isActivo()) throw new IllegalArgumentException("Cuenta inactiva");

        System.out.println("Acceso consedido");
        return usuario;
    }
    
    /**
     * Inserta un nuevo usuario estudiante en la base de datos.
     * @param usuario Usuario a insertar.
     */
    public void insertarEstudiante(Usuarios usuario) {
        if (usuariosRPS.buscarPorCorreo(usuario.getCorreo()) != null) throw new IllegalArgumentException("Correo ya registrado");

        if (usuariosRPS.buscarPorMatricula(usuario.getMatricula()) != null) throw new IllegalArgumentException("Matricula ya registrada");

        usuariosRPS.insertar(usuario);
    }
    
    /**
     * Inserta un nuevo usuario estudiante en la base de datos.
     * @param usuario Usuario a insertar.
     */
    public void insertarEmpleado(Usuarios usuario) {
        if (usuariosRPS.buscarPorCorreo(usuario.getCorreo()) != null) throw new IllegalArgumentException("Correo ya registrado");

        usuariosRPS.insertar(usuario);
    }

    /**
     * Aprueba (activa) un usuario para que pueda acceder al sistema.
     * @param idUsuario identificador del usuario
     * @return true si la operación fue exitosa
     */
    public boolean aprobarUsuario(long idUsuario) {
        return usuariosRPS.cambiarActivoUsuario(idUsuario, true);
    }

    /**
     * Desactiva un usuario para que no acceda al sistema.
     * @param idUsuario identificador del usuario
     * @return true si la operación fue exitosa
     */
    public boolean desactivarUsuario(long idUsuario) {
        return usuariosRPS.cambiarActivoUsuario(idUsuario, false);
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
        Usuarios usuario = usuariosRPS.obtenerPorId(id);
        if (usuario == null) throw new IllegalArgumentException("Usuario no encontrado");

        usuario.setNombre(nuevoNombre);
        usuario.setApellidoPaterno(nuevoApellidoPaterno);
        usuario.setApellidoMaterno(nuevoApellidoMaterno);
        usuario.setTelefono(nuevoTelefono);
        
        usuariosRPS.actualizar(usuario);
    }

    /**
     * Elimina un usuario de la base de datos por su ID.
     * @param id Identificador del usuario a eliminar.
     */
    public void eliminar(long id) {
        usuariosRPS.eliminar(id);
    }
}
