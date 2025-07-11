package org.upemor.controllers;

import org.upemor.models.entities.Categorias;
import org.upemor.models.repositories.CategoriasRepository;

import java.util.List;

/**
 * Controlador para la gestión de categorías de productos en la cafetería.
 */
public class CategoriasController {
    private final CategoriasRepository categoriasRepository = new CategoriasRepository();

    /**
     * Obtiene todas las categorías registradas.
     * @return lista de categorías
     */

    public List<Categorias> obtenerTodas() {
        return categoriasRepository.obtenerTodos();
    }

    /**
     * Busca una categoría por su ID.
     * @param id identificador de la categoría
     * @return Categorias encontrada o null
     */

    public Categorias buscarPorId(long id) {
        try {
            return categoriasRepository.obtenerPorId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Inserta una nueva categoría.
     * @param nombre nombre de la categoría
     * @param descripcion descripción de la categoría
     * @return true si se insertó correctamente
     */

    public void insertar(String nombre, String descripcion) {
        Categorias categoria = new Categorias(0, nombre, descripcion);
        categoriasRepository.insertar(categoria);
    }

    /**
     * Actualiza una categoría existente.
     * @param id identificador de la categoría
     * @param nombre nuevo nombre
     * @param descripcion nueva descripción
     * @return true si se actualizó correctamente
     */

    public void actualizar(long id, String nombre, String descripcion) {
        Categorias categoria = new Categorias(id, nombre, descripcion);
        try {
            categoriasRepository.actualizar(categoria);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina una categoría por su ID.
     * @param id identificador de la categoría
     * @return true si se eliminó correctamente
     */

    public void eliminar(int id) {
        categoriasRepository.eliminar(id);
    }
}
