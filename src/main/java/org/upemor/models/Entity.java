package org.upemor.models;

public abstract class Entity {
    protected long id;

    public long getId() {return id;}

    public Entity(long newId){
        id = newId;
    }
}
