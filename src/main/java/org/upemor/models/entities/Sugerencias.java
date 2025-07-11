package org.upemor.models.entities;

import java.sql.Timestamp;
import org.upemor.models.Entity;
import lombok.Getter;

@Getter
public class Sugerencias extends Entity {
    private Usuarios usuario;
    private String tipoDieta;
    private String alergias;
    private String comentarios;
    private Timestamp fechaSugerencia;
    
    public Usuarios getUsuario() {return usuario;}
    
    public String getTipoDieta() {return tipoDieta;}
    
    public String getAlergias() {return alergias;}
    
    public String getComentarios() {return comentarios;}
    
    public Timestamp getFechaSugerencia() {return fechaSugerencia;}
    
    public Sugerencias(long newId, Usuarios usuario, String tipoDieta, String alergias, String comentarios, Timestamp fechaSugerencia) {
        super(newId);
        this.usuario = usuario;
        this.tipoDieta = tipoDieta;
        this.alergias = alergias;
        this.comentarios = comentarios;
        this.fechaSugerencia = fechaSugerencia;
    }
}
