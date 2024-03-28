package br.com.alura.alurex.api.model;

import br.com.alura.alurex.api.dto.CreateFeedbackDTO;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

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

    private LocalDateTime createdAtEnrollment = LocalDateTime.now();

    private Integer score;

    private String reason;

    private LocalDateTime createdAtFeedback;


    public Enrollment(User user, Course course){
        this.user = user;
        this.course = course;
        this.id = new EnrollmentId(user.getId(), course.getId());
    }

    public void setFeedback(CreateFeedbackDTO dto){
        this.score = dto.score();
        this.reason = dto.reason();
        this.createdAtFeedback = LocalDateTime.now();
    }
}
