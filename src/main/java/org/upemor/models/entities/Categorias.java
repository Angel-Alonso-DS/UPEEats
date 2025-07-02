package org.upemor.models.entities;

public class Categorias {
    private int idCategoria;
    private String nombre;
    private String descripcion;
    private boolean activa;
    private Productos producto;

    public int getIdCategoria() {
        return idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isActiva() {
        return activa;
    }

    public Productos getProducto() {
        return producto;
    }

    public Categorias() {}

}
