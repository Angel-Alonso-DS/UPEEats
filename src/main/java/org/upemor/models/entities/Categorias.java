package org.upemor.models.entities;


import org.upemor.models.Entity;
import lombok.Getter;

/**
 * Entidad que representa una categoría de productos en el sistema UPEEats.
 * Contiene nombre y descripción de la categoría.
 */
@Getter
public class Categorias extends Entity {
    private String nombre;
    private String descripcion;
    
    /**
     * Constructor de la entidad Categorias.
     * @param newId identificador único
     * @param nombre nombre de la categoría
     * @param descripcion descripción de la categoría
     */
    public Categorias(long newId, String nombre, String descripcion) {
        super(newId);
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
