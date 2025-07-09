package org.upemor.models.entities;

import java.time.LocalDateTime;

import org.upemor.models.Entity;

public class Usuarios extends Entity {
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String contrasenia;
    private String telefono;
    private boolean activo;
    private String rol;
    private String matricula;
    private LocalDateTime fechaRegistro;

    public String getNombres() {return nombres;}

    public String getApellidoPaterno() {return apellidoPaterno;}

    public String getApellidoMaterno() {return apellidoMaterno;}

    public String getCorreo() {return correo;}

    public String getContrasenia() {return contrasenia;}

    public String getTelefono() {return telefono;}

    public boolean isActivo() {return activo;}

    public String getRol() {return rol;}

    public String getMatricula() {return matricula;}

    public LocalDateTime getFechaRegistro() {return fechaRegistro;}

    public Usuarios() {
        super(0);
    }
}
