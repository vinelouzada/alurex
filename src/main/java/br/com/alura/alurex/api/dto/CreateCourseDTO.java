package br.com.alura.alurex.api.dto;

import br.com.alura.alurex.api.enums.CourseStatus;
import jakarta.validation.constraints.*;

public record CreateCourseDTO(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotNull
        CourseStatus status,
        @NotBlank
        @Size(max = 10)
        String code,
        @NotNull(message = "Instructor cannot be blank")
        Long instructorId
) {
}
