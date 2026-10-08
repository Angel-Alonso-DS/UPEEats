package org.upemor.models.base;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase abstracta Entidad
 * Sirve como clase base para las entidades del modelo, proporcionando un identificador único.
 * Todas las entidades que heredan de "Entidad" tendrán un campo 'id' para su identificación.
 */

@Getter
@Setter

public abstract class Entidad {
    protected long id;
    protected String tipoEntidad;

    /**
     * Constructor de la clase "Entidad".
     * Inicializa la entidad con el identificador proporcionado.
     *
     * @param id Identificador único de la entidad.
     */
    public Entidad(long id) {
        this.id = id;
    }

    /**
     * Cada entidad requiere de esta funcion para devolver sus atributos
     * para ser usados en ItemEntidad
     * @return Una Matriz de los atributos dado por:
     * <pre>
     * return new String[][] {
     *      {ItemEntidad.TIPO, " nombre atributo ", atributo},
     *      {}
     *      ...
     * };
     * </pre>
     */
    public abstract String[][] toInfo();
}
