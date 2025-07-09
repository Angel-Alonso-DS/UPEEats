package org.upemor.models.entities;

import org.upemor.models.Entity;

public class Categorias extends Entity {
    private String nombre;
    private String descripcion;

    public String getNombre() {return nombre;}

    public String getDescripcion() {return descripcion;}

    public Categorias() {
        super(0);
    }

}
