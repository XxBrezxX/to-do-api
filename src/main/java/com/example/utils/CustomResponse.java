package com.example.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomResponse {
    public static ResponseEntity<Object> generateResponse(HttpStatus status, Object body) {
        return ResponseEntity.status(status).body(body);
    }
}
