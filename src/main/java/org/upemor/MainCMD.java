package org.upemor;

import org.upemor.views.cmd.*;
import java.util.Scanner;

public class MainCMD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- UPEEats Sistema de Pruebas CMD ---");
            System.out.println("1. Acceso al sistema");
            System.out.println("2. Registro de usuario");
            System.out.println("3. Gestión de productos");
            System.out.println("4. Gestión de pedidos");
            System.out.println("5. Gestión de sugerencias");
            System.out.println("6. Panel de administrador");
            System.out.println("7. Gestión de categorías");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    new AccesoViewCMD().mostrarMenu();
                    break;
                case 2:
                    new RegistroUsuarioViewCMD().mostrarMenu();
                    break;
                case 3:
                    new ProductosViewCMD().mostrarMenu();
                    break;
                case 4:
                    new PedidosViewCMD().mostrarMenu();
                    break;
                case 5:
                    new SugerenciasViewCMD().mostrarMenu();
                    break;
                case 6:
                    new AdministradorViewCMD().mostrarMenu();
                    break;
                case 7:
                    new CategoriasViewCMD().mostrarMenu();
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
}
