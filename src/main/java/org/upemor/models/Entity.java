package org.upemor.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Entity {
    protected long id;

    public Entity(long newId){
        id = newId;
    }
}
