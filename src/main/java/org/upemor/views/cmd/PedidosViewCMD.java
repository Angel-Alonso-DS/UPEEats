package org.upemor.views.cmd;

import org.upemor.controllers.PedidosController;
import org.upemor.models.entities.Pedidos;
import java.util.List;
import java.util.Scanner;

/**
 * Clase PedidosViewCMD
 * Proporciona la interfaz de gestión por consola para los pedidos en UPEEats.
 * Permite listar, buscar y crear pedidos mediante opciones interactivas.
 */
public class PedidosViewCMD {
    // Controlador para operaciones sobre pedidos
    private final PedidosController pedidosController = new PedidosController();
    // Scanner para leer la entrada del usuario desde la consola
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Muestra el menú principal de gestión de pedidos y gestiona las opciones seleccionadas.
     * Permite al usuario listar, buscar y crear pedidos.
     */
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Pedidos ---");
            System.out.println("1. Listar todos los pedidos");
            System.out.println("2. Buscar pedido por ID");
            System.out.println("3. Crear nuevo pedido");
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
                case 3:
                    crearPedido();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    /**
     * Permite crear un nuevo pedido.
     * Solicita el ID del usuario, los productos y cantidades, y un comentario opcional.
     * Llama al controlador para registrar el pedido.
     */
    private void crearPedido() {
        System.out.print("ID del usuario: ");
        long idUsuario = scanner.nextLong();
        scanner.nextLine();
        java.util.List<Long> productos = new java.util.ArrayList<>();
        java.util.List<Integer> cantidades = new java.util.ArrayList<>();
        String agregarOtro;
        do {
            System.out.print("ID del producto: ");
            long idProducto = scanner.nextLong();
            System.out.print("Cantidad: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine();
            productos.add(idProducto);
            cantidades.add(cantidad);
            System.out.print("¿Agregar otro producto? (s/n): ");
            agregarOtro = scanner.nextLine();
        } while (agregarOtro.equalsIgnoreCase("s"));
        System.out.print("Comentario (opcional): ");
        String comentario = scanner.nextLine();
        boolean ok = pedidosController.crearPedido(idUsuario, productos, cantidades, comentario);
        System.out.println(ok ? "Pedido registrado exitosamente." : "No se pudo registrar el pedido.");
    }

    /**
     * Muestra el listado de todos los pedidos registrados en la plataforma.
     */
    private void listarTodos() {
        List<Pedidos> pedidos = pedidosController.obtenerTodos();
        System.out.println("\n--- Lista de Pedidos ---");
        for (Pedidos p : pedidos) {
            System.out.println(p.getId() + " | Usuario: " + p.getId() + " | Total: $" + p.getTotal());
        }
    }

    /**
     * Busca y muestra un pedido por su ID.
     * Solicita el ID al usuario y muestra la información si existe.
     */
    private void buscarPorId() {
        System.out.print("Ingrese el ID del pedido: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Pedidos pedido = pedidosController.buscarPorId(id);
        if (pedido != null) {
            System.out.println("ID: " + pedido.getId() + " | Usuario: " + pedido.getId() + " | Total: $" + pedido.getTotal());
        } else {
            System.out.println("Pedido no encontrado.");
        }
    }
}
