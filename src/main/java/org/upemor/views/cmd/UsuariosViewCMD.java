package org.upemor.views.cmd;

import org.upemor.controllers.UsuariosController;
import org.upemor.models.entities.Usuarios;
import java.util.List;
import java.util.Scanner;

public class UsuariosViewCMD {
    private final UsuariosController usuariosController = new UsuariosController();
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Usuarios ---");
            System.out.println("1. Listar todos los usuarios");
            System.out.println("2. Buscar usuario por ID");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    listarTodos();
                    break;
                case 2:
                    buscarPorId();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private void listarTodos() {
        List<Usuarios> usuarios = usuariosController.obtenerTodos();
        System.out.println("\n--- Lista de Usuarios ---");
        for (Usuarios u : usuarios) {
            System.out.println(u.getId() + " | " + u.getNombre() + " " + u.getApellidoPaterno() + " | Rol: " + u.getRol() + " | Activo: " + u.isActivo());
        }
    }

    private void buscarPorId() {
        System.out.print("Ingrese el ID del usuario: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Usuarios usuario = usuariosController.buscarPorId(id);
        if (usuario != null) {
            System.out.println(usuario.getId() + " | " + usuario.getNombre() + " " + usuario.getApellidoPaterno() + " | Rol: " + usuario.getRol() + " | Activo: " + usuario.isActivo());
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
}
