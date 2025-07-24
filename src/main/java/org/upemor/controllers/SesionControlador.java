package org.upemor.controllers;

import java.sql.Timestamp;

import org.upemor.models.entities.Usuarios;
import org.upemor.models.repositories.UsuarioRepository;

/**
 * Controlador para la gestión de sesiones y registro de usuarios en UPEEats.
 * Permite registrar y autenticar estudiantes y empleados/administradores.
 */
public class SesionControlador {
    private final UsuarioRepository usuarioRepository = new UsuarioRepository();

    /**
     * Registra un nuevo usuario estudiante si los datos son válidos y no existen duplicados.
     * @param nombre Nombre del estudiante
     * @param apellidoPaterno Apellido paterno
     * @param apellidoMaterno Apellido materno
     * @param correo Correo electrónico
     * @param contrasenia Contraseña
     * @param telefono Teléfono
     * @param matricula Matrícula única
     * @return true si el registro fue exitoso, en caso contrario lanza una excepción
     */
    public boolean registrarEstudiante(String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String contrasenia, String telefono, String matricula) {
        if (usuarioRepository.existeCorreo(correo)) throw new IllegalArgumentException("Correo ya registrado");

        if (usuarioRepository.existeMatricula(matricula)) throw new IllegalArgumentException("Matricula ya registrada");

        Timestamp fechaRegistro = new Timestamp(System.currentTimeMillis());

        try {
            Usuarios nuevoUsuario = new Usuarios(0, nombre, apellidoPaterno, apellidoMaterno, correo, contrasenia, telefono, true, "estudiante", matricula, fechaRegistro);

            usuarioRepository.insertar(nuevoUsuario);
            System.out.println("Registro exitoso");
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error al registrar estudiante ", e);
        }

    }

    /**
     * Registra un nuevo usuario empleado o administrador si los datos son válidos y no existe el correo.
     * @param nombre Nombre del empleado/administrador
     * @param apellidoPaterno Apellido paterno
     * @param apellidoMaterno Apellido materno
     * @param correo Correo electrónico
     * @param contrasenia Contraseña
     * @param telefono Teléfono
     * @param rol Rol del usuario (Empleado o Administrador)
     * @return true si el registro fue exitoso, false en caso contrario
     */
    public boolean registrarEmpleado(String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String contrasenia, String telefono, String rol) {
        if (usuarioRepository.existeCorreo(correo)) throw new IllegalArgumentException("Correo ya registrado");

        Timestamp fechaRegistro = new Timestamp(System.currentTimeMillis());
        
        try {
            Usuarios nuevoUsuario = new Usuarios(0, nombre, apellidoPaterno, apellidoMaterno, correo, contrasenia, telefono, false, rol, "", fechaRegistro);
            
            usuarioRepository.insertar(nuevoUsuario);
            System.out.println("Registro exitoso");
            
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error al registrar empleado", e);
        }
    }
    
    /**
     * Permite el acceso de un estudiante validando matrícula y contraseña.
     * @param matricula Matrícula del estudiante
     * @param contrasenia Contraseña
     * @return Objeto Usuarios si el acceso es exitoso, de caso contrario lanza una excepción
     */
    public Usuarios accesoEstudiante(String matricula, String contrasenia) {
        Usuarios usuario = usuarioRepository.buscarPorMatricula(matricula);

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
        Usuarios usuario = usuarioRepository.buscarPorCorreo(correo);

        if (usuario == null) throw new IllegalArgumentException("Correo o contraseña incorrectos");

        if (!usuario.getContrasenia().equals(contrasenia)) throw new IllegalArgumentException("Correo o contraseña incorrectos");

        if (!usuario.isActivo()) throw new IllegalArgumentException("Cuenta inactiva");

        System.out.println("Acceso consedido");
        return usuario;
    }

    
}
