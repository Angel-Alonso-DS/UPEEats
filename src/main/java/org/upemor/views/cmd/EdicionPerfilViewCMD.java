package org.upemor.views.cmd;

import java.util.Scanner;

import org.upemor.controllers.SesionControlador;
import org.upemor.models.entities.Usuarios;

public class EdicionPerfilViewCMD {
    private final SesionControlador sesionControlador = new SesionControlador();
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Editar Perfil del Sistema UPEEats ---");
            System.out.println("1. Editar estudiante");
            System.out.println("2. Editar empleado");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    editarEstudiante();
                    break;
                case 2:
                    editarEmpleado();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private void editarEmpleado() {
        
    }

    private void editarEstudiante() {
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenia = scanner.nextLine();
        Usuarios usuario = sesionControlador.accesoEstudiante(matricula, contrasenia);
        if (usuario == null) {
            System.out.println("Acceso denegado.");
            return;
        }

        System.out.println("*Editar perfil de estudiante*");
        System.out.print("Nuevo nombre (actual: " + usuario.getNombre() + "): ");
        String nuevoNombre = scanner.nextLine();
        if (nuevoNombre.isEmpty()) nuevoNombre = usuario.getNombre();

        System.out.print("Nuevo apellido paterno (actual: " + usuario.getApellidoPaterno() + "): ");
        String nuevoApellidoPaterno = scanner.nextLine();
        if (nuevoApellidoPaterno.isEmpty()) nuevoApellidoPaterno = usuario.getApellidoPaterno();

        System.out.print("Nuevo apellido materno (actual: " + usuario.getApellidoMaterno() + "): ");
        String nuevoApellidoMaterno = scanner.nextLine();
        if (nuevoApellidoMaterno.isEmpty()) nuevoApellidoMaterno = usuario.getApellidoMaterno();

        System.out.print("Nuevo teléfono (actual: " + usuario.getTelefono() + "): ");
        String nuevoTelefono = scanner.nextLine();
        if (nuevoTelefono.isEmpty()) nuevoTelefono = usuario.getTelefono();

        System.out.print("Nueva contraseña: ");
        String nuevaContrasenia = scanner.nextLine();
        if (nuevaContrasenia.isEmpty()) nuevaContrasenia = contrasenia;

        sesionControlador.editarPerfilEstudiante(usuario.getId(), nuevoNombre, nuevoApellidoPaterno, nuevoApellidoMaterno, nuevoTelefono, nuevaContrasenia);
    }
}
