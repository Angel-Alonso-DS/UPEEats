package org.upemor.views.cmd;

import org.upemor.controllers.CategoriasController;
import org.upemor.models.entities.Categorias;
import java.util.List;
import java.util.Scanner;

public class CategoriasViewCMD {
    private final CategoriasController categoriasController = new CategoriasController();
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Categorías ---");
            System.out.println("1. Listar todas las categorías");
            System.out.println("2. Buscar categoría por ID");
            System.out.println("3. Agregar categoría");
            System.out.println("4. Editar categoría");
            System.out.println("5. Eliminar categoría");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    listarTodas();
                    break;
                case 2:
                    buscarPorId();
                    break;
                case 3:
                    agregarCategoria();
                    break;
                case 4:
                    editarCategoria();
                    break;
                case 5:
                    eliminarCategoria();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private void listarTodas() {
        List<Categorias> categorias = categoriasController.obtenerTodas();
        System.out.println("\n--- Lista de Categorías ---");
        for (Categorias c : categorias) {
            System.out.println(c.getId() + " | " + c.getNombre() + " | " + c.getDescripcion());
        }
    }

    private void buscarPorId() {
        System.out.print("Ingrese el ID de la categoría: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Categorias cat = categoriasController.buscarPorId(id);
        if (cat != null) {
            System.out.println(cat.getId() + " | " + cat.getNombre() + " | " + cat.getDescripcion());
        } else {
            System.out.println("Categoría no encontrada.");
        }
    }

    private void agregarCategoria() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        categoriasController.insertar(nombre, descripcion);
        System.out.println("Categoría agregada.");
    }

    private void editarCategoria() {
        System.out.print("ID de la categoría a editar: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Categorias cat = categoriasController.buscarPorId(id);
        if (cat == null) {
            System.out.println("Categoría no encontrada.");
            return;
        }
        System.out.print("Nuevo nombre (actual: " + cat.getNombre() + "): ");
        String nombre = scanner.nextLine();
        if (nombre.isEmpty()) nombre = cat.getNombre();
        System.out.print("Nueva descripción (actual: " + cat.getDescripcion() + "): ");
        String descripcion = scanner.nextLine();
        if (descripcion.isEmpty()) descripcion = cat.getDescripcion();
        categoriasController.actualizar(id, nombre, descripcion);
        System.out.println("Categoría actualizada.");
    }

    private void eliminarCategoria() {
        System.out.print("ID de la categoría a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        categoriasController.eliminar(id);
        System.out.println("Categoría eliminada.");
    }
}
