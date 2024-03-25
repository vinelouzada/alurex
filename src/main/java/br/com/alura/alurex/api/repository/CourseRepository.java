package br.com.alura.alurex.api.repository;

import br.com.alura.alurex.api.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
