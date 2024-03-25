package br.com.alura.alurex.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "Enrollment")
@Table(name = "enrollments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Enrollment{

    @EmbeddedId
    private EnrollmentId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false)
    private Course course;

    private LocalDate createdAt;

    public Enrollment(User user, Course course){
        this.user = user;
        this.course = course;
        this.id = new EnrollmentId(user.getId(), course.getId());
        this.createdAt = LocalDate.now();
    }
}
