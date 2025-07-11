package org.upemor.controllers;

import java.sql.Timestamp;

import org.upemor.models.entities.Usuarios;
import org.upemor.models.repositories.UsuarioRepository;
import org.upemor.utils.Validadores;

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
     * @return true si el registro fue exitoso, false en caso contrario
     */
    public boolean registrarEstudiante(String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String contrasenia, String telefono, String matricula) {
        if (!Validadores.validarNombre(nombre) && 
            !Validadores.validarApellido(apellidoPaterno) && 
            !Validadores.validarApellido(apellidoMaterno) && 
            !Validadores.validarCorreo(correo) && 
            !Validadores.validarContrasenia(contrasenia) && 
            !Validadores.validarTelefono(telefono) && 
            !Validadores.validarMatricula(matricula)
        ) {
            System.err.println("Datos incorrectos");
            return false;
        }

        if (usuarioRepository.existeCorreo(correo)) {
            System.err.println("Correo ya registrado");
            return false;
        }
        
        if (usuarioRepository.existeMatricula(matricula)) {
            System.err.println("Matricula ya registrada");
            return false;
        }

        Timestamp fechaRegistro = new Timestamp(System.currentTimeMillis());

        try {
            Usuarios nuevoUsuario = new Usuarios(0, nombre, apellidoPaterno, apellidoMaterno, correo, contrasenia, telefono, true, "Estudiante", matricula, fechaRegistro);
            usuarioRepository.insertar(nuevoUsuario);
            System.out.println("Registro exitoso");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
        if (Validadores.validarNombre(nombre) && 
            Validadores.validarApellido(apellidoPaterno) &&
            Validadores.validarApellido(apellidoMaterno) &&
            Validadores.validarCorreo(correo) &&
            Validadores.validarContrasenia(contrasenia) &&
            Validadores.validarTelefono(telefono) &&
            Validadores.validarRol(rol)
        ) {
            if (nombre == null || apellidoPaterno == null || apellidoMaterno == null || correo == null || contrasenia == null || telefono == null || rol == null) {
                System.err.println("Datos incorrectos");
                return false;
            }
        }

        if (usuarioRepository.existeCorreo(correo)) {
            System.err.println("Correo ya registrado");
            return false;
        }

        Timestamp fechaRegistro = new Timestamp(System.currentTimeMillis());
        
        try {
            Usuarios nuevoUsuario = new Usuarios(0, nombre, apellidoPaterno, apellidoMaterno, correo, contrasenia, telefono, true, rol, "", fechaRegistro);
            usuarioRepository.insertar(nuevoUsuario);
            System.out.println("Registro exitoso");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Manejo de excepciones
        }
    }
    
    /**
     * Permite el acceso de un estudiante validando matrícula y contraseña.
     * @param matricula Matrícula del estudiante
     * @param contrasenia Contraseña
     * @return Objeto Usuarios si el acceso es exitoso, null en caso contrario
     */
    public Usuarios accesoEstudiante(String matricula, String contrasenia) {
        if (!Validadores.validarMatricula(matricula) || !Validadores.validarContrasenia(contrasenia)) {
            System.err.println("Datos invalidos");
            return null;
        }

        Usuarios usuario = usuarioRepository.buscarPorMatricula(matricula);

        if (usuario == null) {
            System.err.println("Matricula no encontrada");
            return null;
        }

        if (!usuario.getContrasenia().equals(contrasenia)) {
            System.err.println("Contraseña incorrecta");
            return null;
        }

        if (!usuario.isActivo()) {
            System.err.println("Cuenta inactiva");
            return null;
        }

        System.out.println("Login exitoso");
        return usuario;
    }
    
    /**
     * Permite el acceso de un empleado o administrador validando correo y contraseña.
     * @param correo Correo electrónico
     * @param contrasenia Contraseña
     * @return Objeto Usuarios si el acceso es exitoso, null en caso contrario
     */
    public Usuarios accesoEmpleado(String correo, String contrasenia) {
        if (!Validadores.validarCorreo(correo) || !Validadores.validarContrasenia(contrasenia)) return null;

        Usuarios usuario = usuarioRepository.buscarPorCorreo(correo);

        if (usuario == null) {
            System.err.println("Correo no encontrado");
            return null;
        }

        if (!usuario.getContrasenia().equals(contrasenia)) {
            System.err.println("Contraseña incorrecta");
            return null;
        }

        if (!usuario.isActivo()) {
            System.err.println("Cuenta inactiva");
            return null;
        }

        System.out.println("Login exitoso");
        return usuario;
    }
}
