package org.upemor;

import org.upemor.controllers.SesionControlador;
import org.upemor.models.entities.Usuarios;
import org.upemor.utils.Validadores;
import org.upemor.views.cmd.*;
import java.util.Scanner;

public class MainCMD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SesionControlador sesionControlador = new SesionControlador();
        int opcion;
        do {
            System.out.println("\n--- UPEEats Sistema de Pruebas CMD ---");
            System.out.println("1. Acceso al sistema como estudiante");
            System.out.println("2. Acceso al sistema como empleado");
            System.out.println("3. Registro de nuevo estudiante");
            System.out.println("4. Registro de nuevo empleado");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    accesoEstudiante(sesionControlador, scanner);
                    break;
                case 2:
                    accesoEmpleado(sesionControlador, scanner);
                    break;
                case 3:
                    RegistroUsuarioViewCMD registroEstudiante = new RegistroUsuarioViewCMD();
                    registroEstudiante.registrarEstudiante();
                    break;
                case 4:
                    RegistroUsuarioViewCMD registroEmpleado = new RegistroUsuarioViewCMD();
                    registroEmpleado.registrarEmpleado();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 0);
        
        scanner.close();
    }

    private static void accesoEstudiante(SesionControlador sesionControlador, Scanner scanner) {
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenia = scanner.nextLine();

        try {
            Validadores.validarMatricula(matricula);
            Validadores.validarContrasenia(contrasenia);
            
            Usuarios usuario = sesionControlador.accesoEstudiante(matricula, contrasenia);
            
            System.out.println("Bienvenido, " + usuario.getNombre() + " " + usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno());
            
            MenuEstudiante menuEstudiante = new MenuEstudiante();
            menuEstudiante.mostrarMenu(usuario);

        } catch (Exception e) {
            System.err.println("Error al acceder: " + e);
        }
    }

    private static void accesoEmpleado(SesionControlador sesionControlador, Scanner scanner) {
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenia = scanner.nextLine();
        
        try {
            Validadores.validarCorreo(correo);
            Validadores.validarContrasenia(contrasenia);
            
            Usuarios usuario = sesionControlador.accesoEmpleado(correo, contrasenia);
            
            System.out.println("Bienvenido, " + usuario.getNombre() + " " + usuario.getApellidoPaterno() + " (" + usuario.getRol() + ")");
            MenuEmpleado menuEmpleado = new MenuEmpleado();
            menuEmpleado.mostrarMenu(usuario);
        } catch (Exception e) {
            System.err.println("Error al acceder: " + e.getMessage());
        }
    }
}
