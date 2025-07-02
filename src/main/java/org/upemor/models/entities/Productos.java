package org.upemor.models.entities;

import java.time.LocalTime;

public class Productos {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private LocalTime tiempoPreparacion;
    private boolean disponible;
    private String imagenUrl;

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public LocalTime getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public Productos() {}
}
