package org.upemor.models.entities;

import java.sql.Timestamp;
import org.upemor.models.Entity;
import lombok.Getter;

/**
 * Clase Sugerencias
 * Representa una sugerencia realizada por un usuario en la plataforma UPEEats.
 * Contiene información sobre el usuario, tipo de dieta, alergias, comentarios y la fecha de la sugerencia.
 */
@Getter
public class Sugerencias extends Entity {
    // Usuario que realiza la sugerencia
    private Usuarios usuario;
    // Tipo de dieta sugerida por el usuario (ejemplo: vegetariana, vegana, etc.)
    private String tipoDieta;
    // Alergias alimentarias indicadas por el usuario
    private String alergias;
    // Comentarios adicionales sobre la sugerencia
    private String comentarios;
    // Fecha en que se realizó la sugerencia
    private Timestamp fechaSugerencia;
    
    /**
     * Obtiene el usuario que realizó la sugerencia.
     * @return usuario que realizó la sugerencia.
     */
    public Usuarios getUsuario() {return usuario;}
    
    /**
     * Obtiene el tipo de dieta sugerido.
     * @return tipo de dieta.
     */
    public String getTipoDieta() {return tipoDieta;}
    
    /**
     * Obtiene las alergias indicadas en la sugerencia.
     * @return alergias alimentarias.
     */
    public String getAlergias() {return alergias;}
    
    /**
     * Obtiene los comentarios adicionales de la sugerencia.
     * @return comentarios de la sugerencia.
     */
    public String getComentarios() {return comentarios;}
    
    /**
     * Obtiene la fecha en que se realizó la sugerencia.
     * @return fecha de la sugerencia.
     */
    public Timestamp getFechaSugerencia() {return fechaSugerencia;}
    
    /**
     * Constructor de la clase Sugerencias.
     * Inicializa una nueva sugerencia con toda la información relevante.
     *
     * @param newId            Identificador único de la sugerencia.
     * @param usuario          Usuario que realiza la sugerencia.
     * @param tipoDieta        Tipo de dieta sugerida.
     * @param alergias         Alergias alimentarias indicadas.
     * @param comentarios      Comentarios adicionales.
     * @param fechaSugerencia  Fecha en que se realizó la sugerencia.
     */
    public Sugerencias(long newId, Usuarios usuario, String tipoDieta, String alergias, String comentarios, Timestamp fechaSugerencia) {
        super(newId);
        this.usuario = usuario;
        this.tipoDieta = tipoDieta;
        this.alergias = alergias;
        this.comentarios = comentarios;
        this.fechaSugerencia = fechaSugerencia;
    }
}
