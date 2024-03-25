package br.com.alura.alurex.api.repository;

import br.com.alura.alurex.api.model.Enrollment;
import br.com.alura.alurex.api.model.EnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {
    Optional<Enrollment> findByUserIdAndCourseId(Long idUser, Long idCourse);
    boolean existsByUserIdAndCourseId(Long idUser, Long idCourse);
}
