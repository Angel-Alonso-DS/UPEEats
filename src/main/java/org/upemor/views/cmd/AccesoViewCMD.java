package org.upemor.views.cmd;

import org.upemor.controllers.SesionControlador;
import org.upemor.models.entities.Usuarios;
import java.util.Scanner;

public class AccesoViewCMD {
    private final SesionControlador sesionControlador = new SesionControlador();
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Acceso al Sistema UPEEats ---");
            System.out.println("1. Acceso Estudiante");
            System.out.println("2. Acceso Empleado");
            System.out.println("3. Editar Perfil");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    accesoEstudiante();
                    break;
                case 2:
                    accesoEmpleado();
                    break;
                case 3:
                    new EdicionPerfilViewCMD().mostrarMenu();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private void accesoEstudiante() {
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenia = scanner.nextLine();
        Usuarios usuario = sesionControlador.accesoEstudiante(matricula, contrasenia);
        if (usuario != null) {
            System.out.println("Bienvenido, " + usuario.getNombre() + " " + usuario.getApellidoPaterno());
        } else {
            System.out.println("Acceso denegado.");
        }
    }

    private void accesoEmpleado() {
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenia = scanner.nextLine();
        Usuarios usuario = sesionControlador.accesoEmpleado(correo, contrasenia);
        if (usuario != null) {
            System.out.println("Bienvenido, " + usuario.getNombre() + " " + usuario.getApellidoPaterno() + " (" + usuario.getRol() + ")");
        } else {
            System.out.println("Acceso denegado.");
        }
    }
}
