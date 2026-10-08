package org.upemor.controllers;

import java.util.List;

import org.upemor.models.Categorias;
import org.upemor.repositories.CategoriasRepositorio;

/**
 * Controlador para la gestión de categorías de productos en la cafetería.
 */
public class CategoriasControlador {
    private final CategoriasRepositorio categoriasRPS = new CategoriasRepositorio();

    /**
     * Obtiene todas las categorías registradas.
     * @return lista de categorías
     */

    public List<Categorias> obtenerTodas() {
        return categoriasRPS.obtenerTodos();
    }

    /**
     * Busca una categoría por su ID.
     * @param id identificador de la categoría
     * @return Categorias encontrada o null
     */

    public Categorias buscarPorId(long id) {
        return categoriasRPS.obtenerPorId(id);
    }

    public List<Categorias> buscarPorNombre(String nombre){
        return categoriasRPS.buscarPorNombre(nombre);
    }

    /**
     * Inserta una nueva categoría.
     * @param nombre nombre de la categoría
     * @param descripcion descripción de la categoría
     * @return true si se insertó correctamente
     */

    public void insertar(String nombre, String descripcion) {
        Categorias categoria = new Categorias(0, nombre, descripcion, null);
        categoriasRPS.insertar(categoria);
    }

    /**
     * Actualiza una categoría existente.
     * @param id identificador de la categoría
     * @param nombre nuevo nombre
     * @param descripcion nueva descripción
     * @return true si se actualizó correctamente
     */

    public void actualizar(long id, String nombre, String descripcion) {
        Categorias categoria = new Categorias(id, nombre, descripcion, null);
        categoriasRPS.actualizar(categoria);
    }

    /**
     * Elimina una categoría por su ID.
     * @param id identificador de la categoría
     * @return true si se eliminó correctamente
     */

    public void eliminar(long id) {
        categoriasRPS.eliminar(id);
    }
}
