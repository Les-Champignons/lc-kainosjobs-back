package org.kainos.ea.exceptions;

public class InvalidException extends Exception {
    public InvalidException() {
        super("Invalid request");
    }
}
