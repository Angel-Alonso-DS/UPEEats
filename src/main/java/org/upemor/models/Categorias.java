package org.upemor.models;

import java.sql.Timestamp;

import org.upemor.models.base.Entidad;
import org.upemor.widgets.ItemEntidad;

import lombok.Getter;
import lombok.Setter;

/**
 * Entidad que representa una categoría de productos en el sistema UPEEats.
 * Contiene nombre y descripción de la categoría.
 */

@Getter
@Setter

public class Categorias extends Entidad{
    // Nombre unico de la categoria
    private String nombre;
    // Descripcion de la categoria
    private String descripcion;
    // fecha en la que se creo la categoria
    private Timestamp fechaCreacion;

    /**
     * Constructor de la entidad Categorias.
     * @param newId identificador único
     * @param nombre nombre de la categoría
     * @param descripcion descripción de la categoría
     * @param fechaCreacion fecha en la que se creo la categoria
     */
    public Categorias(long newId, String nombre, String descripcion, Timestamp fechaCreacion) {
        super(newId);
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        tipoEntidad = "Categorias";
    }

    @Override
    public String[][] toInfo() {
        return new String[][] {
            {ItemEntidad.TITULO, "Nombre", nombre},
            {ItemEntidad.CONTENIDO, "Descripcion", descripcion},
            {ItemEntidad.FECHA,"Fecha registro",fechaCreacion + ""}
        };
    }
}
