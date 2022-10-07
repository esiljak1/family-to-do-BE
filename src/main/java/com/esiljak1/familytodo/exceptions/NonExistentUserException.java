package com.esiljak1.familytodo.exceptions;

public class NonExistentUserException extends IllegalArgumentException {
    public NonExistentUserException(String message) {
        super(message);
    }
}
