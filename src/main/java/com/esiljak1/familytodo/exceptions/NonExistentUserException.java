package com.esiljak1.familytodo.exceptions;

public class NonExistentUserException extends Exception {
    public NonExistentUserException(String message) {
        super(message);
    }
}
