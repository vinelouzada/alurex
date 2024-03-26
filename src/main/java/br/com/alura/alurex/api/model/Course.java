package br.com.alura.alurex.api.model;

import br.com.alura.alurex.api.dto.CreateCourseDTO;
import br.com.alura.alurex.api.dto.UserDetailsByUsernameDTO;
import br.com.alura.alurex.api.enums.CourseStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private String instrutorName;
    private String description;
    @Enumerated(EnumType.STRING)
    private CourseStatus status;
    private LocalDate createdAt;
    private LocalDate inactiveAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User instructor;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;

    public Course (CreateCourseDTO dto, User instrutor){
        this.name = dto.name();
        this.code = dto.code();
        this.instrutorName = instrutor.getName();
        this.description = dto.description();
        this.status = dto.status();
        this.createdAt = LocalDate.now();
        this.instructor = instrutor;
    }

    public void markAsInactive(){
        this.status = CourseStatus.INACTIVE;
        this.inactiveAt = LocalDate.now();
    }
}
