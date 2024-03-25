package br.com.alura.alurex.api.dto;

import jakarta.validation.constraints.NotNull;

public record CreateEnrollmentDTO (
        @NotNull
        Long idUser,
        @NotNull
        Long idCourse){

}
