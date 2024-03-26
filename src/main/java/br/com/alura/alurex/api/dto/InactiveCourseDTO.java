package br.com.alura.alurex.api.dto;

import jakarta.validation.constraints.NotNull;

public record InactiveCourseDTO (
        @NotNull(message = "Course ID cannot be null")
        Long id){
}
