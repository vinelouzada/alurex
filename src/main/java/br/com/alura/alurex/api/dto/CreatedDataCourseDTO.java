package br.com.alura.alurex.api.dto;

import br.com.alura.alurex.api.enums.CourseStatus;

public record CreatedDataCourseDTO(Long id,
                                   String name,
                                   String description,
                                   CourseStatus status,
                                   String code,
                                   String instructorName) {
}
