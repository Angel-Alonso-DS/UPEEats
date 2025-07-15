package org.upemor.views.cmd;

import java.util.Scanner;

import org.upemor.controllers.SesionControlador;
import org.upemor.models.entities.Usuarios;
import org.upemor.utils.Validadores;

public class MenuEstudiante {
    private final SesionControlador sesionControlador = new SesionControlador();
    private final Scanner scanner = new Scanner(System.in);
    private Usuarios usuario;

    public void mostrarMenu(Usuarios usuario) {
        this.usuario = usuario;
        System.out.println("\n--- Menu Estudiante ---");
        System.out.println("1. Ver información personal");
        System.out.println("2. Editar información personal");
        System.out.println("3. Cerrar sesión");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        
        int opcion = scanner.nextInt();

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
    }

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
            
            sesionControlador.editarUsuario(usuario.getId(), nuevoNombre, nuevoApellidoPaterno, nuevoApellidoMaterno, nuevoTelefono);
            System.out.println("Información actualizada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error de validación: " + e.getMessage());
            return;
        }
    }
    
    private void verInformacionPersonal() {
        System.out.println("Información Personal:");
        System.out.println("Matrícula: " + usuario.getMatricula());
        System.out.println("Nombre: " + usuario.getNombre());
        System.out.println("Apellido Paterno: " + usuario.getApellidoPaterno());
        System.out.println("Apellido Materno: " + usuario.getApellidoMaterno());
        System.out.println("Correo: " + usuario.getCorreo());
        System.out.println("Teléfono: " + usuario.getTelefono());
    }

    private void cerrarSesion() {
        System.out.println("Cerrando sesión...");
    }
}
