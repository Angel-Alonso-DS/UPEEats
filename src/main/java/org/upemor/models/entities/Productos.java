package org.upemor.models.entities;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import org.upemor.models.Entity;

public class Productos extends Entity {
    private String nombreProducto;
    private String imagenUrl;
    private String descripcion;
    private double precio;
    private Time tiempoPreparacion;
    private boolean disponible;
    private Timestamp fechaRegistro;
    private List<Categorias> categorias;
    
    
    public String getNombreProducto() {return nombreProducto;}
    
    public String getImagenURL() {return imagenUrl;}
    
    public String getDescripcion() {return descripcion;}
    
    public double getPrecio() {return precio;}
    
    public Time getTiempoPreparacion() {return tiempoPreparacion;}
    
    public boolean isDisponible() {return disponible;}
    
    public Timestamp getFechaRegistro() {return fechaRegistro;}
    
    public List<Categorias> getCategorias() {return categorias;}
    
    public void setCategorias(List<Categorias> categorias) {this.categorias = categorias;}
    
    public Productos(long id, String nombreProducto, String imagenUrl, String descripcion, double precio, Time tiempoPreparacion, boolean disponible, Timestamp fechaRegistro, List<Categorias> categorias) {
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
