package org.upemor.controllers;

import org.upemor.models.entities.Sugerencias;
import org.upemor.models.repositories.SugerenciasRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para la gestión de sugerencias en la cafetería.
 */
public class SugerenciasController {
    private final SugerenciasRepository sugerenciasRepository = new SugerenciasRepository();

    public List<Sugerencias> obtenerTodos() {
        return sugerenciasRepository.obtenerTodos();
    }

    public Sugerencias buscarPorId(long id) {
        try {
            return sugerenciasRepository.obtenerPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void insertar(Sugerencias sugerencia) {
        sugerenciasRepository.insertar(sugerencia);
    }

    public void actualizar(Sugerencias sugerencia) {
        try {
            sugerenciasRepository.actualizar(sugerencia);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        sugerenciasRepository.eliminar(id);
    }
}
