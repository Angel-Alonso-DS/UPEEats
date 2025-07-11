package org.upemor.models.entities;

import java.sql.Timestamp;
import java.util.List;

import org.upemor.models.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Productos extends Entity {
    private String nombreProducto;
    private String imagenUrl;
    private String descripcion;
    private double precio;
    private String tiempoPreparacion;
    private boolean disponible;
    private Timestamp fechaRegistro;
    private List<Categorias> categorias;
    
    public Productos(long id, String nombreProducto, String imagenUrl, String descripcion, double precio, String tiempoPreparacion, boolean disponible, Timestamp fechaRegistro, List<Categorias> categorias) {
        super(id);
        this.nombreProducto = nombreProducto;
        this.imagenUrl = imagenUrl;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tiempoPreparacion = tiempoPreparacion;
        this.disponible = disponible;
        this.fechaRegistro = fechaRegistro;
        this.categorias = categorias;
    }
}
