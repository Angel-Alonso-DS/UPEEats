package org.upemor.models;

import java.sql.Timestamp;

import org.upemor.models.base.Entidad;
import org.upemor.widgets.ItemEntidad;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase Sugerencias
 * Representa una sugerencia realizada por un usuario en la plataforma UPEEats.
 * Contiene información sobre el usuario, tipo de dieta, alergias, comentarios y la fecha de la sugerencia.
 */

@Getter
@Setter

public class Sugerencias extends Entidad{
    // Usuario que realiza la sugerencia
    private Usuarios usuario;
    // Asunto del que se realiza la sugerencia
    private String asunto;
    // Comentario de la sugerencia
    private String comentario;
    // Estado de la revision de los empleados/Adminstradores
    private String estado;
    // Fecha del momento en el cual se realizo la sugerencia
    private Timestamp fechaRegistro;
    // Fecha de la revision
    private Timestamp fechaRevision;

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
    
     public Sugerencias(long id, Usuarios usuario, String asunto, String comentario, String estado, Timestamp fechaRegistro, Timestamp fechaRevision) {
        super(id);
        this.usuario = usuario;
        this.asunto = asunto;
        this.comentario = comentario;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.fechaRevision = fechaRevision;
        tipoEntidad = "Sugerencias";
    }

    @Override
    public String[][] toInfo() {
        return new String[][] {
            {ItemEntidad.TITULO, "Asunto", asunto},
            {ItemEntidad.SUBTITULO, "Autor", usuario.getNombre()},
            {ItemEntidad.CONTENIDO, "Comentario", comentario},
            {ItemEntidad.SUBTITULO, "Estado", estado},
            {ItemEntidad.FECHA, "Fecha registro", fechaRegistro + ""},
        };
    }
}
