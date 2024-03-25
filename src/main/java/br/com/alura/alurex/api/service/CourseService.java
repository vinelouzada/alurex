package br.com.alura.alurex.api.service;

import br.com.alura.alurex.api.dto.CreateCourseDTO;
import br.com.alura.alurex.api.exception.Course.CourseNotFoundException;
import br.com.alura.alurex.api.exception.User.UserNotFoundException;
import br.com.alura.alurex.api.model.Course;
import br.com.alura.alurex.api.model.User;
import br.com.alura.alurex.api.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private UserService userService;

    public Long create(CreateCourseDTO dto){
        User instructor = userService.findById(dto.instructorId());

        Course course = new Course(dto, instructor);

        repository.save(course);

        return course.getId();
    }

    public Course findById(Long id){
        try {
            return repository.getReferenceById(id);
        }catch (EntityNotFoundException ex){
            throw new CourseNotFoundException();
        }
    }
}
