package org.upemor.models.entities;

import java.sql.Timestamp;

import org.upemor.models.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase Usuarios
 * Representa un usuario registrado en la plataforma UPEEats.
 * Contiene información personal, credenciales, estado, rol, matrícula y fecha de registro.
 */
@Getter
@Setter
public class Usuarios extends Entity {
    // Nombre del usuario
    private String nombre;
    // Apellido paterno del usuario
    private String apellidoPaterno;
    // Apellido materno del usuario
    private String apellidoMaterno;
    // Correo electrónico del usuario
    private String correo;
    // Contraseña del usuario
    private String contrasenia;
    // Teléfono de contacto del usuario
    private String telefono;
    // Indica si el usuario está activo en la plataforma
    private boolean activo;
    // Rol del usuario (ejemplo: "cliente", "administrador")
    private String rol;
    // Matrícula del usuario (puede ser usada para identificación escolar)
    private String matricula;
    // Fecha en que el usuario se registró en la plataforma
    private Timestamp fechaRegistro;

    /**
     * Constructor de la clase Usuarios.
     * Inicializa un nuevo usuario con toda la información relevante.
     *
     * @param newId           Identificador único del usuario.
     * @param nombre          Nombre del usuario.
     * @param apellidoPaterno Apellido paterno del usuario.
     * @param apellidoMaterno Apellido materno del usuario.
     * @param correo          Correo electrónico del usuario.
     * @param contrasenia     Contraseña del usuario.
     * @param telefono        Teléfono de contacto.
     * @param activo          Estado de actividad del usuario.
     * @param rol             Rol del usuario en la plataforma.
     * @param matricula       Matrícula del usuario.
     * @param fechaRegistro   Fecha de registro en la plataforma.
     */
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
