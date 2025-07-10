package org.upemor.controllers;

import java.sql.Timestamp;

import org.upemor.models.entities.Usuarios;
import org.upemor.models.repositories.UsuarioRepository;

public class sesionControlador {
    private final UsuarioRepository usuarioRepository = new UsuarioRepository();

    /** Registro para un nuevo usuario estudiante **/

    public boolean registrarEstudiante(String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String contrasenia, String telefono, String matricula) {
        if (nombre == null || apellidoPaterno == null || apellidoMaterno == null || correo == null || contrasenia == null || telefono == null || matricula == null) {
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
            return false; // Manejo de excepciones
        }

    }

    /** Registro para un nuevo usuario empleado o administrador **/

    public boolean registrar(String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String contrasenia, String telefono, String rol) {
        if (nombre == null || apellidoPaterno == null || apellidoMaterno == null || correo == null || contrasenia == null || telefono == null || rol == null) {
            System.err.println("Datos incorrectos");
            return false;
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
    
    public Usuarios accesoEstudiante(String matricula, String contrasenia) {
        if (matricula == null || contrasenia == null) return null;

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
    
    public Usuarios acceso(String correo, String contrasenia) {
        if (correo == null || contrasenia == null) return null;

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
