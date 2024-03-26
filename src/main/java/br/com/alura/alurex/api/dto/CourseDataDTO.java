package br.com.alura.alurex.api.dto;

import br.com.alura.alurex.api.enums.CourseStatus;
import br.com.alura.alurex.api.model.Course;

import java.time.LocalDate;

public record CourseDataDTO(String name,
                            String instructor,
                            CourseStatus status,
                            LocalDate createdAt,
                            LocalDate inactiveAt) {
    public CourseDataDTO(Course course){
        this(course.getName(), course.getInstrutorName(),
                course.getStatus(), course.getCreatedAt(),
                course.getInactiveAt());
    }
}
