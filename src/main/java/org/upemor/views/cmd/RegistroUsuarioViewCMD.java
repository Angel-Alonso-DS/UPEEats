package org.upemor.views.cmd;

import org.upemor.controllers.SesionControlador;
import java.util.Scanner;

/**
 * Clase RegistroUsuarioViewCMD
 * Proporciona la interfaz por consola para el registro de estudiantes y empleados en la plataforma UPEEats.
 * Solicita los datos necesarios al usuario y realiza el registro mediante el controlador de sesión.
 */
public class RegistroUsuarioViewCMD {
    // Controlador para gestionar el registro de usuarios
    private final SesionControlador sesionControlador = new SesionControlador();
    // Scanner para leer la entrada del usuario desde la consola
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Realiza el proceso de registro de un estudiante.
     * Solicita los datos personales y académicos, y llama al controlador para registrar al estudiante.
     * Muestra un mensaje indicando si el registro fue exitoso o fallido.
     */
    public void registrarEstudiante() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido paterno: ");
        String apPat = scanner.nextLine();
        System.out.print("Apellido materno: ");
        String apMat = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenia = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        boolean ok = sesionControlador.registrarEstudiante(nombre, apPat, apMat, correo, contrasenia, telefono, matricula);
        System.out.println(ok ? "Registro exitoso." : "No se pudo registrar el estudiante.");
    }

    /**
     * Realiza el proceso de registro de un empleado.
     * Solicita los datos personales y el rol, y llama al controlador para registrar al empleado.
     * Muestra un mensaje indicando si el registro fue exitoso o fallido.
     */
    public void registrarEmpleado() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido paterno: ");
        String apPat = scanner.nextLine();
        System.out.print("Apellido materno: ");
        String apMat = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenia = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Rol (empleado/administrador): ");
        String rol = scanner.nextLine();
        boolean ok = sesionControlador.registrarEmpleado(nombre, apPat, apMat, correo, contrasenia, telefono, rol);
        System.out.println(ok ? "Registro exitoso." : "No se pudo registrar el empleado.");
    }
}
