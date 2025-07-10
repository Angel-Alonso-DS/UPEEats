package org.upemor.models.entities;

import java.sql.Timestamp;

import org.upemor.models.Entity;

public class Usuarios extends Entity {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String contrasenia;
    private String telefono;
    private boolean activo;
    private String rol;
    private String matricula;
    private Timestamp fechaRegistro;

    public String getNombre() {return nombre;}
    
    public String getApellidoPaterno() {return apellidoPaterno;}
    
    public String getApellidoMaterno() {return apellidoMaterno;}
    
    public String getCorreo() {return correo;}
    
    public String getContrasenia() {return contrasenia;}
    
    public String getTelefono() {return telefono;}
    
    public boolean isActivo() {return activo;}
    
    public String getRol() {return rol;}
    
    public String getMatricula() {return matricula;}
    
    public Timestamp getFechaRegistro() {return fechaRegistro;}

    public Usuarios(long newId, String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String contrasenia, String telefono, boolean activo, String rol, String matricula, Timestamp fechaRegistro) {
        super(newId);
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.activo = activo;
        this.rol = rol;
        this.matricula = matricula;
        this.fechaRegistro = fechaRegistro;
    }
}
