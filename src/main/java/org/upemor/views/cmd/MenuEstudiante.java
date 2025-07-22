package org.upemor.views.cmd;

import java.util.Scanner;

import org.upemor.controllers.UsuariosController;
import org.upemor.models.entities.Usuarios;
import org.upemor.utils.Validadores;

/**
 * Clase MenuEstudiante
 * Proporciona la interfaz de menú por consola para estudiantes en UPEEats.
 * Permite ver y editar información personal, así como cerrar sesión.
 */
public class MenuEstudiante {
    // Controlador para operaciones sobre usuarios
    private final UsuariosController usuariosController = new UsuariosController();
    // Scanner para leer la entrada del usuario desde la consola
    private final Scanner scanner = new Scanner(System.in);
    // Usuario autenticado en el menú
    private Usuarios usuario;

    /**
     * Muestra el menú principal para estudiantes y gestiona las opciones seleccionadas.
     * Permite al estudiante ver y editar su información personal, o cerrar sesión.
     *
     * @param usuario Usuario autenticado que accede al menú.
     */
    public void mostrarMenu(Usuarios usuario) {
        this.usuario = usuario;
        int opcion;
        do{
            System.out.println("\n--- Menu Estudiante ---");
            System.out.println("1. Ver información personal");
            System.out.println("2. Editar información personal");
            System.out.println("3. Cerrar sesión");
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
                    cerrarSesion();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    /**
     * Permite al estudiante editar su información personal.
     * Solicita los nuevos datos, valida la entrada y actualiza la información en la base de datos.
     */
    private void editarInformacionPersonal() {
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
        
        try {
            Validadores.validarNombre(nuevoNombre);
            Validadores.validarApellido(nuevoApellidoPaterno);
            Validadores.validarApellido(nuevoApellidoMaterno);
            Validadores.validarTelefono(nuevoTelefono);

            usuariosController.editarUsuario(usuario.getId(), nuevoNombre, nuevoApellidoPaterno, nuevoApellidoMaterno, nuevoTelefono);
            System.out.println("Información actualizada exitosamente.");

        } catch (Exception e) {
            System.out.println("Error de validación: " + e);
            return;
        }
    }
    
    /**
     * Muestra la información personal del estudiante autenticado.
     */
    private void verInformacionPersonal() {
        Usuarios u = usuariosController.buscarPorId(usuario.getId());
        System.out.println("\n--- Información Personal ---");
        System.out.println("Matrícula: " + u.getMatricula());
        System.out.println("Nombre: " + u.getNombre());
        System.out.println("Apellido Paterno: " + u.getApellidoPaterno());
        System.out.println("Apellido Materno: " + u.getApellidoMaterno());
        System.out.println("Correo: " + u.getCorreo());
        System.out.println("Teléfono: " + u.getTelefono());
    }

    /**
     * Cierra la sesión del estudiante en el menú.
     */
    private void cerrarSesion() {
        System.out.println("Cerrando sesión...");
    }
}
