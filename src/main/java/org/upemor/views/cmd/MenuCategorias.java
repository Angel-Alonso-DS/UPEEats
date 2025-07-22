package org.upemor.views.cmd;

import org.upemor.controllers.CategoriasController;
import org.upemor.models.entities.Categorias;
import org.upemor.utils.Validadores;

import java.util.List;
import java.util.Scanner;

/**
 * Clase MenuCategorias
 * Proporciona la interfaz de gestión por consola para las categorías en UPEEats.
 * Permite listar, buscar, agregar, editar y eliminar categorías mediante opciones interactivas.
 */
public class MenuCategorias {
    // Controlador para operaciones sobre categorías
    private final CategoriasController categoriasController = new CategoriasController();
    // Scanner para leer la entrada del usuario desde la consola
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Muestra el menú principal de gestión de categorías y gestiona las opciones seleccionadas.
     * Permite al usuario realizar acciones CRUD sobre las categorías.
     */
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

    /**
     * Solicita confirmación al usuario antes de realizar una acción sensible.
     * @param mensaje Mensaje de confirmación.
     * @return true si el usuario confirma, false en caso contrario.
     */
    private boolean continuarAccion(String mensaje) {
        System.out.print(mensaje + " (s/n): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();
        return respuesta.equals("s") || respuesta.equals("si");
    }

    /**
     * Muestra el listado de todas las categorías registradas.
     */
    private void listarTodas() {
        List<Categorias> categorias = categoriasController.obtenerTodas();
        System.out.println("\n--- Lista de Categorías ---");
        for (Categorias c : categorias) {
            System.out.println(c.getId() + " | " + c.getNombre() + "\t| " + c.getDescripcion());
        }
    }

    /**
     * Busca y muestra una categoría por su ID.
     * Solicita el ID al usuario y muestra la información si existe.
     */
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

    /**
     * Agrega una nueva categoría a la plataforma.
     * Solicita nombre y descripción, valida los datos y realiza la inserción.
     */
    private void agregarCategoria() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        try {
            Validadores.validarNombre(nombre);
            Validadores.validarTexto(descripcion);
            categoriasController.insertar(nombre, descripcion);
            System.out.println("Categoría agregada.");
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }

    /**
     * Edita una categoría existente.
     * Solicita el ID, muestra los datos actuales y permite modificar nombre y descripción.
     * Valida los datos antes de actualizar.
     */
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

        if(!continuarAccion("¿Deseas editar?")) return;

        try {
            Validadores.validarNombre(nombre);
            Validadores.validarTexto(descripcion);
            categoriasController.actualizar(id, nombre, descripcion);
            System.out.println("Categoría actualizada.");
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }

    /**
     * Elimina una categoría de la plataforma.
     * Solicita el ID y confirma la acción antes de eliminar.
     */
    private void eliminarCategoria() {
        System.out.print("ID de la categoría a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        if(!continuarAccion("¿Deseas eliminar?")) return;
        
        categoriasController.eliminar(id);
        System.out.println("Categoría eliminada.");
    }
}
