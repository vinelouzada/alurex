package br.com.alura.alurex.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = {"userId", "courseId"})
@Embeddable
public class EnrollmentId implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "course_id")
    private Long courseId;
}
