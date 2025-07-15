package org.upemor.views.cmd;

import java.util.Scanner;

import org.upemor.controllers.SesionControlador;
import org.upemor.models.entities.Usuarios;
import org.upemor.utils.Validadores;

public class MenuEmpleado {
    private final SesionControlador sesionControlador = new SesionControlador();
    private final Scanner scanner = new Scanner(System.in);

    private Usuarios usuario;

    public void mostrarMenu(Usuarios usuario) {
        this.usuario = usuario;
        int opcion;
        do {
            System.out.println("\n--- Menú Empleado ---");
            System.out.println("1. Ver información personal");
            System.out.println("2. Editar información personal");
            System.out.println("3. Gestion de productos");
            System.out.println("4. Gestion de categorías");
            System.out.println("5. Cerrar sesión");
        
            if (usuario.getRol().equals("Administrador")) System.out.println("6. Gestion de usuarios");
            
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    verInformacionPersonal();
                    break;
                case 2:
                    editarInformacionPersonal();
                    break;
                case 3:
                    MenuProductos menuProductos = new MenuProductos();
                    menuProductos.mostrarMenu();
                    break;
                case 4:
                    MenuCategorias menuCategorias = new MenuCategorias();
                    menuCategorias.mostrarMenu();
                    break;
                case 5:
                    cerrarSesion();
                    break;
                case 6:
                    if (usuario.getRol().equals("Administrador")) {
                        MenuAdministrador menuAdministrador = new MenuAdministrador();
                        menuAdministrador.mostrarMenu();
                    } else {
                        System.out.println("Opción no disponible para su rol.");
                    }
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private void editarInformacionPersonal() {
        System.out.println("Editar Información Personal:");

        System.out.print("Nuevo Nombre: ");
        String nuevoNombre = scanner.nextLine();
        if (nuevoNombre.isEmpty()) nuevoNombre = usuario.getNombre();
        System.out.print("Nuevo Apellido Paterno: ");
        String nuevoApellidoPaterno = scanner.nextLine();
        if (nuevoApellidoPaterno.isEmpty()) nuevoApellidoPaterno = usuario.getApellidoPaterno();
        System.out.print("Nuevo Apellido Materno: ");
        String nuevoApellidoMaterno = scanner.nextLine();
        if (nuevoApellidoMaterno.isEmpty()) nuevoApellidoMaterno = usuario.getApellidoMaterno();
        System.out.print("Nuevo Teléfono: ");
        String nuevoTelefono = scanner.nextLine();
        if (nuevoTelefono.isEmpty()) nuevoTelefono = usuario.getTelefono();

        try {
            Validadores.validarNombre(nuevoNombre);
            Validadores.validarApellido(nuevoApellidoPaterno);
            Validadores.validarApellido(nuevoApellidoMaterno);
            Validadores.validarTelefono(nuevoTelefono);

            sesionControlador.editarUsuario(usuario.getId(), nuevoNombre, nuevoApellidoPaterno, nuevoApellidoMaterno, nuevoTelefono);
            System.out.println("Información actualizada exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al actualizar la información: " + e.getMessage());
        }
    }

    private void verInformacionPersonal() {
        System.out.println("Información Personal:");
        System.out.println("Nombre: " + usuario.getNombre());
        System.out.println("Apellido Paterno: " + usuario.getApellidoPaterno());
        System.out.println("Apellido Materno: " + usuario.getApellidoMaterno());
        System.out.println("Correo: " + usuario.getCorreo());
        System.out.println("Teléfono: " + usuario.getTelefono());
        System.out.println("Rol: " + usuario.getRol());
    }

    private void cerrarSesion() {
        System.out.println("Cerrando sesión...");
    }
}
