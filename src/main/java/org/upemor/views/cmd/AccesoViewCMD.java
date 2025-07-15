package org.upemor.views.cmd;

import org.upemor.controllers.SesionControlador;
import org.upemor.models.entities.Usuarios;
import org.upemor.utils.Validadores;

import java.util.Scanner;

public class AccesoViewCMD {
    private final SesionControlador sesionControlador = new SesionControlador();
    private final Scanner scanner = new Scanner(System.in);

    public Usuarios accesoEstudiante() {
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenia = scanner.nextLine();
        
        try {
            Validadores.validarMatricula(matricula);
            Validadores.validarContrasenia(contrasenia);
            
            Usuarios usuario = sesionControlador.accesoEstudiante(matricula, contrasenia);
            
            System.out.println("Bienvenido, " + usuario.getNombre() + " " + usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno());
            return usuario;

        } catch (Exception e) {
            System.err.println("Error al acceder: " + e.getMessage());
        }
        return null;
    }

    public Usuarios accesoEmpleado() {
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenia = scanner.nextLine();
        
        try {
            Validadores.validarCorreo(correo);
            Validadores.validarContrasenia(contrasenia);
            
            Usuarios usuario = sesionControlador.accesoEmpleado(correo, contrasenia);
            
            System.out.println("Bienvenido, " + usuario.getNombre() + " " + usuario.getApellidoPaterno() + " (" + usuario.getRol() + ")");
            return usuario;

        } catch (Exception e) {
            System.err.println("Error al acceder: " + e.getMessage());
        }
        return null;
    }
}
