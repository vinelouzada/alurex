package br.com.alura.alurex.api.dto;

import br.com.alura.alurex.api.enums.CourseStatus;
import br.com.alura.alurex.api.model.Course;

import java.time.LocalDate;

public record CourseDataDTO(String name,
                            String instructorEmail,
                            CourseStatus status,
                            LocalDate createdAt,
                            LocalDate inactiveAt) {
    public CourseDataDTO(Course course){
        this(course.getName(), course.getInstructorEmail(),
                course.getStatus(), course.getCreatedAt(),
                course.getInactiveAt());
    }
}
