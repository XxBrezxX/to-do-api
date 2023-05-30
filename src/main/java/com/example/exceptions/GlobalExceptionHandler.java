package com.example.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        // Obtener los mensajes de error de validación
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<String> errorMessages = fieldErrors.stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        // Crear la respuesta de error
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Error de validación");
        errorResponse.setErrors(errorMessages);

        // Devolver la respuesta de error
        return ResponseEntity.badRequest().body(errorResponse);
    }

    // Otros métodos de manejo de excepciones

    // Clase ErrorResponse para representar la respuesta de error
    public class ErrorResponse {
        public void setMessage(String string) {
        }
        public void setErrors(List<String> errorMessages) {
        }

        // Constructor, getters y setters
    }
}
