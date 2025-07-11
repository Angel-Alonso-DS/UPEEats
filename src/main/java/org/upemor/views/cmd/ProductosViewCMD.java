package org.upemor.views.cmd;

import org.upemor.controllers.ProductosController;
import org.upemor.models.entities.Categorias;
import org.upemor.models.entities.Productos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductosViewCMD {
    private final org.upemor.controllers.CategoriasController categoriasController = new org.upemor.controllers.CategoriasController();
    private final ProductosController productosController = new ProductosController();
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Productos ---");
            System.out.println("1. Listar todos los productos");
            System.out.println("2. Buscar producto por nombre");
            System.out.println("3. Buscar producto por categoría");
            System.out.println("4. Agregar producto");
            System.out.println("5. Editar producto");
            System.out.println("6. Eliminar producto");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    listarTodos();
                    break;
                case 2:
                    buscarPorNombre();
                    break;
                case 3:
                    buscarPorCategoria();
                    break;
                case 4:
                    agregarProducto();
                    break;
                case 5:
                    editarProducto();
                    break;
                case 6:
                    eliminarProducto();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private void agregarProducto() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Imagen URL: ");
        String imagen = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Tiempo de preparación (hh:mm:ss): ");
        String tiempoStr = scanner.nextLine();
        String tiempo = tiempoStr;
        System.out.print("¿Disponible? (true/false): ");
        boolean disponible = scanner.nextBoolean();
        scanner.nextLine();
        System.out.print("IDs de categorías (separados por coma, puedes dejar vacío para ninguna): ");
        String idsCatStr = scanner.nextLine();
        List<Categorias> categorias = new ArrayList<>();
        if (!idsCatStr.isEmpty()) {
            for (String idStr : idsCatStr.split(",")) {
                idStr = idStr.trim();
                if (!idStr.isEmpty()) {
                    long idCat = Long.parseLong(idStr);
                    Categorias cat = categoriasController.buscarPorId(idCat);
                    if (cat != null) categorias.add(cat);
                }
            }
        }
        java.sql.Timestamp fecha = new java.sql.Timestamp(System.currentTimeMillis());
        productosController.insertar(nombre, imagen, descripcion, precio, tiempo, disponible, fecha, categorias);
        System.out.println("Producto agregado.");
    }

    private void editarProducto() {
        System.out.print("ID del producto a editar: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Productos p = productosController.buscarPorId(id);
        if (p == null) {
            System.out.println("Producto no encontrado.");
            return;
        }
        System.out.print("Nuevo nombre (actual: " + p.getNombreProducto() + "): ");
        String nombre = scanner.nextLine();
        if (nombre.isEmpty()) nombre = p.getNombreProducto();
        System.out.print("Nueva imagen URL (actual: " + p.getImagenUrl() + "): ");
        String imagen = scanner.nextLine();
        if (imagen.isEmpty()) imagen = p.getImagenUrl();
        System.out.print("Nueva descripción (actual: " + p.getDescripcion() + "): ");
        String descripcion = scanner.nextLine();
        if (descripcion.isEmpty()) descripcion = p.getDescripcion();
        System.out.print("Nuevo precio (actual: " + p.getPrecio() + "): ");
        String precioStr = scanner.nextLine();
        double precio = precioStr.isEmpty() ? p.getPrecio() : Double.parseDouble(precioStr);
        System.out.print("Nuevo tiempo de preparación (hh:mm:ss, actual: " + p.getTiempoPreparacion() + "): ");
        String tiempoStr = scanner.nextLine();
        String tiempo = tiempoStr.isEmpty() ? p.getTiempoPreparacion() : tiempoStr;
        System.out.print("¿Disponible? (true/false, actual: " + p.isDisponible() + "): ");
        String dispStr = scanner.nextLine();
        boolean disponible = dispStr.isEmpty() ? p.isDisponible() : Boolean.parseBoolean(dispStr);
        System.out.print("IDs de categorías (separados por coma, puedes dejar vacío para ninguna): ");
        String idsCatStr = scanner.nextLine();
        List<Categorias> categorias = new ArrayList<>();
        if (!idsCatStr.isEmpty()) {
            for (String idStr : idsCatStr.split(",")) {
                idStr = idStr.trim();
                if (!idStr.isEmpty()) {
                    long idCat = Long.parseLong(idStr);
                    Categorias cat = categoriasController.buscarPorId(idCat);
                    if (cat != null) categorias.add(cat);
                }
            }
        } else {
            categorias = p.getCategorias();
        }
        productosController.actualizar(id, nombre, imagen, descripcion, precio, tiempo, disponible, p.getFechaRegistro(), categorias);
        System.out.println("Producto actualizado.");
    }

    private void eliminarProducto() {
        System.out.print("ID del producto a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        productosController.eliminar(id);
        System.out.println("Producto eliminado.");
    }

    private void listarTodos() {
        List<Productos> productos = productosController.obtenerTodos();
        System.out.println("\n--- Lista de Productos ---");
        for (Productos p : productos) {
            System.out.println(p.getId() + " - " + p.getNombreProducto() + " | $" + p.getPrecio());
        }
    }

    private void buscarPorNombre() {
        System.out.print("Ingrese el nombre o parte del nombre: ");
        String nombre = scanner.nextLine();
        List<Productos> productos = productosController.buscarPorNombre(nombre);
        System.out.println("\n--- Resultados ---");
        for (Productos p : productos) {
            System.out.println(p.getId() + " - " + p.getNombreProducto() + " | $" + p.getPrecio());
        }
    }

    private void buscarPorCategoria() {
        System.out.print("Ingrese el ID de la categoría: ");
        long idCat = scanner.nextLong();
        scanner.nextLine();
        List<Productos> productos = productosController.buscarPorCategoria(idCat);
        System.out.println("\n--- Resultados ---");
        for (Productos p : productos) {
            System.out.println(p.getId() + " - " + p.getNombreProducto() + " | $" + p.getPrecio());
        }
    }
}
