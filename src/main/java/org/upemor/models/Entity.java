package org.upemor.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase abstracta Entity
 * Sirve como clase base para las entidades del modelo, proporcionando un identificador único.
 * Todas las entidades que heredan de Entity tendrán un campo 'id' para su identificación.
 */
@Setter
@Getter
public abstract class Entity {
    // Identificador único de la entidad
    protected long id;

    /**
     * Constructor de la clase Entity.
     * Inicializa la entidad con el identificador proporcionado.
     *
     * @param newId Identificador único de la entidad.
     */
    public Entity(long newId){
        id = newId;
    }
}
