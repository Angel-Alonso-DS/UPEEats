package org.upemor.models.entities;

import java.time.LocalDateTime;

import org.upemor.models.Entity;

public class Sugerencias extends Entity {
    private Usuarios usuario;
    private String tipoDieta;
    private String alergias;
    private String comentarios;
    private LocalDateTime fechaSugerencia;

    public Usuarios getUsuario() {return usuario;}

    public String getTipoDieta() {return tipoDieta;}

    public String getAlergias() {return alergias;}

    public String getComentarios() {return comentarios;}

    public LocalDateTime getFechaSugerencia() {return fechaSugerencia;}

    public Sugerencias() {
        super(0);
    }
}
