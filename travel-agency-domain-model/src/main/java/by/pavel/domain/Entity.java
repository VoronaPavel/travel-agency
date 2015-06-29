package by.pavel.domain;

import by.pavel.aspects.bean.Bean;

public abstract class Entity implements Bean {

    private final long id;

    protected Entity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
