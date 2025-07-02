package org.upemor.models.entities;

import java.time.LocalDateTime;

public class Sugerencias {
    private int idSugerencia;
    private String asunto;
    private String sugerencia;
    private LocalDateTime fechaSugerencia;
    private Usuarios usuario;

    public int getIdSugerencia() {
        return idSugerencia;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getSugerencia() {
        return sugerencia;
    }

    public LocalDateTime getFechaSugerencia() {
        return fechaSugerencia;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public Sugerencias() {}
}
