package org.upemor.views.cmd;

import org.upemor.controllers.SesionControlador;
import java.util.Scanner;

public class RegistroUsuarioViewCMD {
    private final SesionControlador sesionControlador = new SesionControlador();
    private final Scanner scanner = new Scanner(System.in);

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
