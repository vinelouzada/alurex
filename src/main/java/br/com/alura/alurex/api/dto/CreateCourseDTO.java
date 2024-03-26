package br.com.alura.alurex.api.dto;

import br.com.alura.alurex.api.enums.CourseStatus;
import jakarta.validation.constraints.*;

public record CreateCourseDTO(
        @NotBlank(message = "Name cannot be blank")
        String name,
        @NotBlank(message = "Description cannot be blank")
        String description,
        @NotNull(message = "Status cannot be null")
        CourseStatus status,
        @NotBlank(message = "Code cannot be blank")
        @Size(max = 10, message = "Code must have a maximum of {max} characters")
        String code,
        @NotNull(message = "Instructor cannot be blank")
        Long instructorId
) {
}
