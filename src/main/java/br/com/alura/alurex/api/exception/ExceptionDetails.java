package br.com.alura.alurex.api.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ExceptionDetails(String message, HttpStatus httpStatus, LocalDateTime time) {
}
