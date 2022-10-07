package com.esiljak1.familytodo.exceptions;

public class NonExistentFamilyException extends IllegalArgumentException {
    public NonExistentFamilyException(String s) {
        super(s);
    }
}
