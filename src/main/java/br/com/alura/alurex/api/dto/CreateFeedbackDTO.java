package br.com.alura.alurex.api.dto;

import jakarta.validation.constraints.*;

public record CreateFeedbackDTO(
        @NotNull(message = "Course ID cannot be null")
        Long idCourse,

        @NotNull(message = "User ID cannot be null")
        Long idUser,

        @NotNull(message = "Score cannot be null")
        @Min(value = 0, message = "Score must be at least 0")
        @Max(value = 10, message = "Score must be at most 10")
        Integer score,

        @Size(max = 255, message = "Reason length must be less than or equal to 255 characters")
        @NotBlank
        String reason
) {}
