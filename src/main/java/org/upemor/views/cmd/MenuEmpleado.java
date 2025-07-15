package org.upemor.views.cmd;

import java.util.Scanner;

import org.upemor.controllers.SesionControlador;
import org.upemor.controllers.UsuariosController;
import org.upemor.models.entities.Usuarios;
import org.upemor.utils.Validadores;

public class MenuEmpleado {
    private final SesionControlador sesionControlador = new SesionControlador();
    private final UsuariosController usuariosController = new UsuariosController();
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
        
            if (usuario.getRol().equals("adminsitrador")) System.out.println("6. Gestion de usuarios");
            
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    if (confirmarAcceso()) verInformacionPersonal();
                    break;
                case 2:
                    if (confirmarAcceso()) editarInformacionPersonal();
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
                    if (usuario.getRol().equals("adminsitrador")) {
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

    private boolean confirmarAcceso() {
        System.out.println("Ingrese su correo electrónico: ");
        String correo = scanner.nextLine();
        System.out.println("Ingrese su contraseña: ");
        String contrasenia = scanner.nextLine();

        try {
            Validadores.validarCorreo(correo);
            Validadores.validarContrasenia(contrasenia);
            sesionControlador.accesoEmpleado(correo, contrasenia);
        } catch (Exception e) {
            System.err.println("Error al acceder: " + e.getMessage());
            return false;
        }
        return true;
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

            usuariosController.editarUsuario(usuario.getId(), nuevoNombre, nuevoApellidoPaterno, nuevoApellidoMaterno, nuevoTelefono);
            System.out.println("Información actualizada exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al actualizar la información: " + e.getMessage());
        }
    }

    private void verInformacionPersonal() {
        Usuarios u = usuariosController.buscarPorId(usuario.getId());
        System.out.println("\n--- Información Personal ---");
        System.out.println("ID: " + u.getId());
        System.out.println("Nombre: " + u.getNombre());
        System.out.println("Apellido Paterno: " + u.getApellidoPaterno());
        System.out.println("Apellido Materno: " + u.getApellidoMaterno());
        System.out.println("Correo: " + u.getCorreo());
        System.out.println("Teléfono: " + u.getTelefono());
        System.out.println("Rol: " + u.getRol());
    }

    private void cerrarSesion() {
        System.out.println("Cerrando sesión...");
    }
}
