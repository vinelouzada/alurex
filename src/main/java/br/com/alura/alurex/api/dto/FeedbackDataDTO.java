package br.com.alura.alurex.api.dto;

import br.com.alura.alurex.api.model.Enrollment;

import java.time.LocalDate;

public record FeedbackDataDTO (Long idUser,
                               Long idCourse,
                               Integer score,
                               String reason,
                               LocalDate createdAtFeedback){

    public FeedbackDataDTO(Enrollment enrollment){
        this(enrollment.getUser().getId(),
                enrollment.getUser().getId(),
                enrollment.getScore(),
                enrollment.getReason(),
                enrollment.getCreatedAtFeedback());
    }
}
