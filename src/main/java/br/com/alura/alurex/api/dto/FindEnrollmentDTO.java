package br.com.alura.alurex.api.dto;

import br.com.alura.alurex.api.model.Enrollment;

import java.time.LocalDate;

public record FindEnrollmentDTO(Long idUser, Long idCouse, LocalDate date) {
    public FindEnrollmentDTO(Enrollment enrollment){
        this(enrollment.getId().getUserId(), enrollment.getId().getCourseId(), enrollment.getCreatedAtEnrollment());
    }
}
