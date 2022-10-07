package com.esiljak1.familytodo.interfaces;

import org.springframework.http.ResponseEntity;

public interface CRUDInterface<I, T> {
    ResponseEntity<?> post(T object);
    ResponseEntity<?> get();
    ResponseEntity<?> update(I id, T object);
    ResponseEntity<?> delete(I id);
}
