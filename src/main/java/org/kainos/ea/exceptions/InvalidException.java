package org.kainos.ea.exceptions;

public class InvalidException extends Exception {
    public InvalidException(final Entity entity, final String reason) {
        super(entity.getEntity() + " is not valid: " + reason);
    }
}
