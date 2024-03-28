package br.com.alura.alurex.api.dto;
import java.time.LocalDateTime;

public record CreatedDataEnrollmentDTO (Long idCourse, Long idUser, LocalDateTime createdAt){
}
