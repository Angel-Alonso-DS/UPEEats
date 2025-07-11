package org.upemor.controllers;

import org.upemor.models.entities.ReseniaProducto;
import org.upemor.models.repositories.ReseniaProductoRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para la gestión de reseñas de productos en la cafetería.
 */
public class ReseniaProductoController {
    private final ReseniaProductoRepository reseniaProductoRepository = new ReseniaProductoRepository();

    public List<ReseniaProducto> obtenerTodos() {
        return reseniaProductoRepository.obtenerTodos();
    }

    public ReseniaProducto buscarPorId(long id) {
        try {
            return reseniaProductoRepository.obtenerPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void insertar(ReseniaProducto resenia) {
        reseniaProductoRepository.insertar(resenia);
    }

    public void actualizar(ReseniaProducto resenia) {
        try {
            reseniaProductoRepository.actualizar(resenia);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        reseniaProductoRepository.eliminar(id);
    }
}
