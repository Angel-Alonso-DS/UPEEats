package org.upemor.controllers;

import org.upemor.models.entities.Sugerencias;
import org.upemor.models.repositories.SugerenciasRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para la gestión de sugerencias en la cafetería.
 * Proporciona métodos para consultar, insertar, actualizar y eliminar sugerencias.
 * Utiliza SugerenciasRepository para interactuar con la base de datos.
 */
public class SugerenciasController {
    // Repositorio para operaciones sobre sugerencias
    private final SugerenciasRepository sugerenciasRepository = new SugerenciasRepository();

    /**
     * Obtiene la lista de todas las sugerencias registradas.
     * @return Lista de sugerencias.
     */
    public List<Sugerencias> obtenerTodos() {
        return sugerenciasRepository.obtenerTodos();
    }

    /**
     * Busca una sugerencia por su ID.
     * @param id Identificador de la sugerencia.
     * @return Sugerencia encontrada o null si no existe.
     */
    public Sugerencias buscarPorId(long id) {
        try {
            return sugerenciasRepository.obtenerPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Inserta una nueva sugerencia en la base de datos.
     * @param sugerencia Sugerencia a insertar.
     */
    public void insertar(Sugerencias sugerencia) {
        sugerenciasRepository.insertar(sugerencia);
    }

    /**
     * Actualiza una sugerencia existente en la base de datos.
     * @param sugerencia Sugerencia con los datos actualizados.
     */
    public void actualizar(Sugerencias sugerencia) {
        try {
            sugerenciasRepository.actualizar(sugerencia);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina una sugerencia de la base de datos por su ID.
     * @param id Identificador de la sugerencia a eliminar.
     */
    public void eliminar(int id) {
        sugerenciasRepository.eliminar(id);
    }
}
