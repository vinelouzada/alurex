package br.com.alura.alurex.api.exception;

import org.springframework.validation.FieldError;

public record FormExceptionDetails (String field, String message) {
    public  FormExceptionDetails(FieldError error){
        this(error.getField(), error.getDefaultMessage());
    }
}
