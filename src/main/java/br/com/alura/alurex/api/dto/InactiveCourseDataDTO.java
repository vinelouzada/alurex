package br.com.alura.alurex.api.dto;

import br.com.alura.alurex.api.enums.CourseStatus;
import br.com.alura.alurex.api.model.Course;

import java.time.LocalDate;

public record InactiveCourseDataDTO(String name, CourseStatus status, LocalDate inactiveAt) {
    public InactiveCourseDataDTO(Course course){
        this(course.getName(), course.getStatus(), course.getInactiveAt());
    }
}
