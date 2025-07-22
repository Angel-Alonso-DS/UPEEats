package org.upemor.views.cmd;

import org.upemor.controllers.SesionControlador;
import org.upemor.models.entities.Usuarios;
import org.upemor.utils.Validadores;

import java.util.Scanner;

/**
 * Clase AccesoViewCMD
 * Proporciona la interfaz de acceso por consola para estudiantes y empleados en la plataforma UPEEats.
 * Permite la autenticación de usuarios mediante la entrada de credenciales y muestra mensajes de bienvenida o error.
 */
public class AccesoViewCMD {
    // Controlador de sesión para gestionar el acceso de usuarios
    private final SesionControlador sesionControlador = new SesionControlador();
    // Scanner para leer la entrada del usuario desde la consola
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Realiza el proceso de acceso para estudiantes.
     * Solicita matrícula y contraseña, valida los datos y autentica al usuario.
     * Muestra un mensaje de bienvenida si el acceso es exitoso, o un mensaje de error si falla.
     *
     * @return Usuario autenticado o null si el acceso falla.
     */
    public Usuarios accesoEstudiante() {
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenia = scanner.nextLine();
        
        try {
            // Validación de la matrícula y contraseña
            Validadores.validarMatricula(matricula);
            Validadores.validarContrasenia(contrasenia);
            
            // Autenticación del estudiante
            Usuarios usuario = sesionControlador.accesoEstudiante(matricula, contrasenia);
            
            // Mensaje de bienvenida
            System.out.println("Bienvenido, " + usuario.getNombre() + " " + usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno());
            return usuario;

        } catch (Exception e) {
            // Mensaje de error en caso de fallo
            System.err.println("Error al acceder: " + e.getMessage());
        }
        return null;
    }

    /**
     * Realiza el proceso de acceso para empleados.
     * Solicita correo y contraseña, valida los datos y autentica al usuario.
     * Muestra un mensaje de bienvenida si el acceso es exitoso, o un mensaje de error si falla.
     *
     * @return Usuario autenticado o null si el acceso falla.
     */
    public Usuarios accesoEmpleado() {
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenia = scanner.nextLine();
        
        try {
            // Validación del correo y contraseña
            Validadores.validarCorreo(correo);
            Validadores.validarContrasenia(contrasenia);
            
            // Autenticación del empleado
            Usuarios usuario = sesionControlador.accesoEmpleado(correo, contrasenia);
            
            // Mensaje de bienvenida con rol
            System.out.println("Bienvenido, " + usuario.getNombre() + " " + usuario.getApellidoPaterno() + " (" + usuario.getRol() + ")");
            return usuario;

        } catch (Exception e) {
            // Mensaje de error en caso de fallo
            System.err.println("Error al acceder: " + e.getMessage());
        }
        return null;
    }
}
