package br.com.alura.alurex.api.dto;

import br.com.alura.alurex.api.model.Enrollment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record FeedbackDataDTO (Long idUser,
                               Long idCourse,
                               Integer score,
                               String reason,
                               LocalDateTime createdAtFeedback){

    public FeedbackDataDTO(Enrollment enrollment){
        this(enrollment.getUser().getId(),
                enrollment.getCourse().getId(),
                enrollment.getScore(),
                enrollment.getReason(),
                enrollment.getCreatedAtFeedback());
    }
}
