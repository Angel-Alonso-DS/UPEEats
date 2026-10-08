package org.upemor.controllers;

import java.util.List;

import org.upemor.models.Categorias;
import org.upemor.models.Productos;
import org.upemor.repositories.ProductoCategoriaRepositorio;
import org.upemor.repositories.ProductosRepositorio;

/**
 * Controlador para la gestión de productos en la cafetería.
 * Actúa como coordinador entre la vista y los repositorios.
 */
public class ProductosControlador {
    private final ProductosRepositorio productosRPS = new ProductosRepositorio();
    private final ProductoCategoriaRepositorio productoCategoriaRPS = new ProductoCategoriaRepositorio();

    /**
     * Obtiene todos los productos registrados.
     * @return lista de productos
     */
    public List<Productos> obtenerTodos() {
        return productosRPS.obtenerTodos();
    }

    /**
     * Busca productos por nombre (coincidencia parcial, case-insensitive).
     * @param nombre nombre o parte del nombre a buscar
     * @return lista de productos que coinciden
     */
    public List<Productos> buscarPorNombre(String nombre) {
        return productosRPS.buscarPorNombre(nombre);
    }
    
    /**
     * Busca productos por id de categoría.
     * @param idCategoria id de la categoría
     * @return lista de productos que pertenecen a la categoría
     */
    public List<Productos> buscarPorCategoria(long idCategoria) {
        return productosRPS.buscarPorCategoria(idCategoria);
    }
    
    /**
     * Busca productos por id de categorías.
     * @param idCategoria id de la categoría
     * @return lista de productos que pertenecen a la categoría
     */
    public List<Productos> buscarPorCategorias(List<Long> idCategorias) {
        return productosRPS.buscarPorCategorias(idCategorias);
    }

    /**
     * Busca los productos que esten en el rango de precios
     * @param min Precio minimo
     * @param max Precio maximo
     * @return Lista de productos dentro del rango de precios
     */
    public List<Productos> buscarPorPrecio(double min, double max) {
        return productosRPS.buscarPorPrecio(min, max);
    }

    /**
     * Busca los productos disponibles o no disponibles
     * @param disponible true si se buscan disponibles, false si se buscan inactivos
     * @return Listado de los productos
     */
    public List<Productos> buscarPorDisponibilidad(boolean disponible) {
        return productosRPS.buscarPorDisponibilidad(disponible);
    }

    /**
     * Busca un producto por su ID.
     * @param id identificador del producto
     * @return Productos encontrado o null
     */

    public Productos buscarPorId(long id) {
        return productosRPS.obtenerPorId(id);
    }

    /**
     * Inserta un nuevo producto.
     * @param nombre nombre del producto
     * @param descripcion descripción del producto
     * @param precio precio del producto
     * @param categorias id de la categoría
     */
    public void insertar(String nombreProducto, String imagen, String descripcion, double precio, String tiempoPreparacion, boolean disponible, List<Categorias> categorias) {
        Productos producto = new Productos(0, nombreProducto, imagen, descripcion, precio, tiempoPreparacion, disponible, null, categorias);

        productosRPS.insertar(producto);

        long id = producto.getId();
        if (id == 0) {
            id = productosRPS.obtenerUltimoIdInsertado();
        }

        productoCategoriaRPS.asociarCategorias(id, producto.getCategorias());
    }

    /**
     * Actualiza un producto existente.
     * @param id identificador del producto
     * @param nombre nuevo nombre
     * @param descripcion nueva descripción
     * @param precio nuevo precio
     * @param idCategoria nuevo id de la categoría
     */
    public void actualizar(long id, String nombreProducto, String imagen, String descripcion, double precio, String tiempoPreparacion, boolean disponible, List<Categorias> categorias) {
        Productos producto = new Productos(id, nombreProducto, imagen, descripcion, precio, tiempoPreparacion, disponible, null, categorias);
        try {
            productosRPS.actualizar(producto);
            productoCategoriaRPS.asociarCategorias(id, categorias);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Elimina un producto por su ID.
     * @param id identificador del producto
     */
    public void eliminar(long id) {
        productosRPS.eliminar(id);
    }
}
