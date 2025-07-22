package org.upemor.views.cmd;

import java.util.Scanner;

import org.upemor.controllers.SesionControlador;
import org.upemor.controllers.UsuariosController;
import org.upemor.models.entities.Usuarios;
import org.upemor.utils.Validadores;

/**
 * Clase MenuEmpleado
 * Proporciona la interfaz de menú por consola para empleados en UPEEats.
 * Permite ver y editar información personal, gestionar productos y categorías, y acceder a opciones administrativas si el rol lo permite.
 */
public class MenuEmpleado {
    // Controlador de sesión para autenticación de empleados
    private final SesionControlador sesionControlador = new SesionControlador();
    // Controlador para operaciones sobre usuarios
    private final UsuariosController usuariosController = new UsuariosController();
    // Scanner para leer la entrada del usuario desde la consola
    private final Scanner scanner = new Scanner(System.in);

    // Usuario autenticado en el menú
    private Usuarios usuario;

    /**
     * Muestra el menú principal para empleados y gestiona las opciones seleccionadas.
     * Permite al empleado realizar acciones sobre su información y acceder a gestiones según su rol.
     *
     * @param usuario Usuario autenticado que accede al menú.
     */
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
        
            // Solo los administradores pueden gestionar usuarios
            if (usuario.getRol().equals("adminsitrador")) System.out.println("6. Gestion de usuarios");
            
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    // Ver información personal, requiere confirmación de acceso
                    if (confirmarAcceso()) verInformacionPersonal();
                    break;
                case 2:
                    // Editar información personal, requiere confirmación de acceso
                    if (confirmarAcceso()) editarInformacionPersonal();
                    break;
                case 3:
                    // Acceso al menú de gestión de productos
                    MenuProductos menuProductos = new MenuProductos();
                    menuProductos.mostrarMenu();
                    break;
                case 4:
                    // Acceso al menú de gestión de categorías
                    MenuCategorias menuCategorias = new MenuCategorias();
                    menuCategorias.mostrarMenu();
                    break;
                case 5:
                    // Cerrar sesión
                    cerrarSesion();
                    break;
                case 6:
                    // Acceso al menú de administración solo si el usuario es administrador
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

    /**
     * Solicita credenciales al usuario para confirmar el acceso antes de realizar acciones sensibles.
     * Valida el correo y la contraseña ingresados.
     *
     * @return true si el acceso es confirmado y válido, false en caso contrario.
     */
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

    /**
     * Permite al usuario editar su información personal.
     * Solicita los nuevos datos, valida la entrada y actualiza la información en la base de datos.
     */
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

    /**
     * Muestra la información personal del usuario autenticado.
     */
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

    /**
     * Cierra la sesión del usuario en el menú.
     */
    private void cerrarSesion() {
        System.out.println("Cerrando sesión...");
    }
}
