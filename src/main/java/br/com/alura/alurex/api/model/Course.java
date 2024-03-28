package br.com.alura.alurex.api.model;

import br.com.alura.alurex.api.dto.CreateCourseDTO;
import br.com.alura.alurex.api.enums.CourseStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "courses")
@Entity(name = "Course")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private String instructorEmail;
    private String description;
    @Enumerated(EnumType.STRING)
    private CourseStatus status;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime inactiveAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User instructor;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;

    public Course (CreateCourseDTO dto, User instrutor){
        this.name = dto.name();
        this.code = dto.code();
        this.instructorEmail = getInstructorEmail();
        this.description = dto.description();
        this.status = dto.status();
        this.instructor = instrutor;
    }

    public Course(String name, String code, String description, CourseStatus status, User instructor) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.status = status;
        this.instructor = instructor;
        this.instructorEmail = getInstructorEmail();
    }

    public void markAsInactive(){
        this.status = CourseStatus.INACTIVE;
        this.inactiveAt = LocalDateTime.now();
    }
}
