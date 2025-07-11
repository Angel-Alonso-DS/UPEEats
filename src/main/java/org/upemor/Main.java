package org.upemor;

import org.upemor.controllers.SesionControlador;

public class Main {
    public static void main(String[] args) {
        SesionControlador controlador = new SesionControlador();
        
        String nombre, apellidoPaterno, apellidoMaterno, correo, contrasenia, telefono, matricula;
        int option;
        
        System.out.println("Iniciando la aplicación...");
        System.out.println("Bienvenido al sistema de gestión de sesiones.");
        
        do {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Registrar Estudiante");
            System.out.println("2. Registrar Empleado");
            System.out.println("3. Acceso Estudiante");
            System.out.println("4. Acceso Empleado");
            System.out.println("0. Salir");

            option = Integer.parseInt(System.console().readLine());

            switch (option) {
                case 1:
                    System.out.println("Registro de Estudiante");
                    System.out.print("Nombre: ");
                    nombre = System.console().readLine();
                    System.out.print("Apellido Paterno: ");
                    apellidoPaterno = System.console().readLine();
                    System.out.print("Apellido Materno: ");
                    apellidoMaterno = System.console().readLine();
                    System.out.print("Correo: ");
                    correo = System.console().readLine();
                    System.out.print("Contraseña: ");
                    contrasenia = System.console().readLine();
                    System.out.print("Teléfono: ");
                    telefono = System.console().readLine();
                    System.out.print("Matrícula: ");
                    matricula = System.console().readLine();
                    controlador.registrarEstudiante(
                        nombre, apellidoPaterno, apellidoMaterno, correo, contrasenia, telefono, matricula
                    );
                    break;
                case 2:
                    String rol;
                    System.out.print("Nombre: ");
                    nombre = System.console().readLine();
                    System.out.print("Apellido Paterno: ");
                    apellidoPaterno = System.console().readLine();
                    System.out.print("Apellido Materno: ");
                    apellidoMaterno = System.console().readLine();
                    System.out.print("Correo: ");
                    correo = System.console().readLine();
                    System.out.print("Contraseña: ");
                    contrasenia = System.console().readLine();
                    System.out.print("Teléfono: ");
                    telefono = System.console().readLine();
                    System.out.print("Rol: ");
                    rol = System.console().readLine();
                    controlador.registrarEmpleado(
                        nombre, apellidoPaterno, apellidoMaterno, correo, contrasenia, telefono, rol
                    );
                    break;
                case 3:
                    System.out.print("Matrícula: ");
                    matricula = System.console().readLine();
                    System.out.print("Contraseña: ");
                    contrasenia = System.console().readLine();
                    controlador.accesoEstudiante("qwer123456", "contrasenia123");
                    break;
                case 4:
                System.out.print("Correo: ");
                    correo = System.console().readLine();
                    System.out.print("Contraseña: ");
                    contrasenia = System.console().readLine();
                    controlador.accesoEmpleado(correo, contrasenia);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (option != 0);


    }
}