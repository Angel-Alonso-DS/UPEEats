package org.upemor.controllers;

import org.upemor.models.entities.Categorias;
import org.upemor.models.entities.Productos;
import org.upemor.models.repositories.ProductoCategoriaRepository;
import org.upemor.models.repositories.ProductoRepository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Controlador para la gestión de productos en la cafetería.
 */
public class ProductosController {
    private final ProductoRepository productoRepository = new ProductoRepository();
    private final ProductoCategoriaRepository productoCategoriaRepository = new ProductoCategoriaRepository();

    /**
     * Obtiene todos los productos registrados.
     * @return lista de productos
     */
    public List<Productos> obtenerTodos() {
        return productoRepository.obtenerTodos();
    }
    
    /**
     * Busca productos por nombre (coincidencia parcial, case-insensitive).
     * @param nombre nombre o parte del nombre a buscar
     * @return lista de productos que coinciden
     */
    public List<Productos> buscarPorNombre(String nombre) {
        return productoRepository.buscarPorNombre(nombre);
    }
    
    /**
     * Busca productos por id de categoría.
     * @param idCategoria id de la categoría
     * @return lista de productos que pertenecen a la categoría
     */
    public List<Productos> buscarPorCategoria(long idCategoria) {
        return productoRepository.buscarPorCategoria(idCategoria);
    }
    
    /**
     * Busca un producto por su ID.
     * @param id identificador del producto
     * @return Productos encontrado o null
     */
    public Productos buscarPorId(long id) {
        try {
            return productoRepository.obtenerPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Inserta un nuevo producto.
     * @param nombre nombre del producto
     * @param descripcion descripción del producto
     * @param precio precio del producto
     * @param idCategoria id de la categoría
     */
    public void insertar(String nombreProducto, String imagenUrl, String descripcion, double precio, String tiempoPreparacion, boolean disponible, Timestamp fechaRegistro, List<Categorias> categorias) {
        Productos producto = new Productos(0, nombreProducto, imagenUrl, descripcion, precio, tiempoPreparacion, disponible, fechaRegistro, categorias);
        productoRepository.insertar(producto);
        // Obtener el ID generado
        long idProducto = producto.getId();
        try {
            // Si el repositorio no actualiza el ID, obtén el último ID insertado
            if (idProducto == 0) {
                idProducto = productoRepository.obtenerUltimoIdInsertado();
            }
            productoCategoriaRepository.asociarCategorias(idProducto, categorias);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza un producto existente.
     * @param id identificador del producto
     * @param nombre nuevo nombre
     * @param descripcion nueva descripción
     * @param precio nuevo precio
     * @param idCategoria nuevo id de la categoría
     */
    public void actualizar(long id, String nombreProducto, String imagenUrl, String descripcion, double precio, String tiempoPreparacion, boolean disponible, Timestamp fechaRegistro, List<Categorias> categorias) {
        Productos producto = new Productos(id, nombreProducto, imagenUrl, descripcion, precio, tiempoPreparacion, disponible, fechaRegistro, categorias);
        try {
            productoRepository.actualizar(producto);
            productoCategoriaRepository.asociarCategorias(id, categorias);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un producto por su ID.
     * @param id identificador del producto
     */
    public void eliminar(int id) {
        productoRepository.eliminar(id);
    }
}
