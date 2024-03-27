package br.com.alura.alurex.api.repository;

import br.com.alura.alurex.api.dto.NpsMetricsDTO;
import br.com.alura.alurex.api.model.Enrollment;
import br.com.alura.alurex.api.model.EnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {
    Optional<Enrollment> findByUserIdAndCourseId(Long idUser, Long idCourse);
    boolean existsByUserIdAndCourseId(Long idUser, Long idCourse);

    @Query("""
       SELECT new br.com.alura.alurex.api.dto.NpsMetricsDTO(c.name,
       SUM(CASE WHEN e.score >= 9 THEN 1 ELSE 0 END),
       SUM(CASE WHEN e.score <= 6 THEN 1 ELSE 0 END),
       COUNT(e))
       FROM Course c
       INNER JOIN c.enrollments e
       WHERE e.score IS NOT NULL
       GROUP BY c.name
        """)
    List<NpsMetricsDTO> findNpsMetrics();
}
