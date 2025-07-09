package org.upemor.models.entities;

import java.time.LocalTime;

import org.upemor.models.Entity;

public class Productos extends Entity {
    private String nombreProducto;
    private String imagenUrl;
    private String descripcion;
    private double precio;
    private LocalTime tiempoPreparacion;
    private boolean disponible;

    public String getNombreProducto() {return nombreProducto;}

    public String getImagenUrl() {return imagenUrl;}

    public String getDescripcion() {return descripcion;}

    public double getPrecio() {return precio;}

    public LocalTime getTiempoPreparacion() {return tiempoPreparacion;}

    public boolean isDisponible() {return disponible;}

    public Productos() {
        super(0);
    }
}
