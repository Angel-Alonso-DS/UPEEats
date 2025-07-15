package org.upemor.views.cmd;

import org.upemor.controllers.AdministradorController;
import org.upemor.models.entities.Usuarios;
import java.util.List;
import java.util.Scanner;

public class MenuAdministrador {
    private final AdministradorController adminController = new AdministradorController();
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Panel de Administrador ---");
            System.out.println("1. Listar empleados pendientes");
            System.out.println("2. Listado de usuarios");
            System.out.println("3. Aprobar empleado");
            System.out.println("4. Desactivar usuario");
            System.out.println("5. Eliminar usuario");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    listarEmpleadosPendientes();
                    break;
                case 2:
                    listarUsuarios();
                    break;
                case 3:
                    aprobarEmpleado();
                    break;
                case 4:
                    desactivarUsuario();
                    break;
                case 5:
                    eliminarUsuario();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private void eliminarUsuario() {
        System.out.print("Ingrese el ID del usuario a eliminar: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        try {
            boolean ok = adminController.desactivarUsuario(id);
            System.out.println(ok ? "Usuario eliminado." : "No se pudo eliminar el usuario.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarUsuarios() {
        List<Usuarios> usuarios = adminController.obtenerTodosUsuarios();
        System.out.println("\n--- Listado de Usuarios ---");
        for (Usuarios u : usuarios) {
            System.out.println(u.getId() + " | " + u.getNombre() + " " + u.getApellidoPaterno() + " " + u.getApellidoMaterno() + " | " + u.getCorreo() + " | " + (u.isActivo() ? "Activo" : "Inactivo") + " | " + u.getRol());
        }
    }

    private void listarEmpleadosPendientes() {
        List<Usuarios> empleados = adminController.obtenerEmpleadosPendientes();
        System.out.println("\n--- Empleados pendientes de aprobación ---");
        for (Usuarios u : empleados) {
            System.out.println(u.getId() + " | " + u.getNombre() + " " + u.getApellidoPaterno());
        }
    }

    private void aprobarEmpleado() {
        System.out.print("Ingrese el ID del empleado a aprobar: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        try {
            boolean ok = adminController.aprobarEmpleado(id);
            System.out.println(ok ? "Empleado aprobado." : "No se pudo aprobar el empleado.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void desactivarUsuario() {
        System.out.print("Ingrese el ID del usuario a desactivar: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        try {
            boolean ok = adminController.desactivarUsuario(id);
            System.out.println(ok ? "Usuario desactivado." : "No se pudo desactivar el usuario.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
