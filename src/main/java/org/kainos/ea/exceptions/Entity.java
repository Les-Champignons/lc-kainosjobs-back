package org.kainos.ea.exceptions;

public enum Entity {
    USER("User");

    private final String entity;

    Entity(final String entity) {
        this.entity = entity;
    }

    public String getName() {
        return this.name();
    }
    public String getEntity() {
        return this.entity;
    }
}
