package org.upemor.views.cmd;

import org.upemor.controllers.SesionControlador;
import java.util.Scanner;

public class RegistroUsuarioViewCMD {
    private final SesionControlador sesionControlador = new SesionControlador();
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Registro de Usuario ---");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Registrar empleado");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    registrarEstudiante();
                    break;
                case 2:
                    registrarEmpleado();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private void registrarEstudiante() {
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

    private void registrarEmpleado() {
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
