package br.com.alura.alurex.api.repository;

import br.com.alura.alurex.api.enums.CourseStatus;
import br.com.alura.alurex.api.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findAllByStatus(Pageable pageable, CourseStatus status);
}
