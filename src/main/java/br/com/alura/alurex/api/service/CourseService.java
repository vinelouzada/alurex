package br.com.alura.alurex.api.service;

import br.com.alura.alurex.api.dto.CourseDataDTO;
import br.com.alura.alurex.api.dto.CreateCourseDTO;
import br.com.alura.alurex.api.dto.InactiveCourseDTO;
import br.com.alura.alurex.api.dto.InactiveCourseDataDTO;
import br.com.alura.alurex.api.enums.CourseStatus;
import br.com.alura.alurex.api.enums.Role;
import br.com.alura.alurex.api.exception.Course.CourseAlreadyInactiveException;
import br.com.alura.alurex.api.exception.Course.CourseNotFoundException;
import br.com.alura.alurex.api.exception.Course.UserMustBeInstructorException;
import br.com.alura.alurex.api.exception.User.UserNotFoundException;
import br.com.alura.alurex.api.model.Course;
import br.com.alura.alurex.api.model.User;
import br.com.alura.alurex.api.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private UserService userService;

    public Page<CourseDataDTO> all(Pageable pageable){
        return repository.findAll(pageable).map(CourseDataDTO::new);
    }
    public Page<CourseDataDTO> allByStatus(Pageable pageable, CourseStatus status){
        return repository.findAllByStatus(pageable,status).map(CourseDataDTO::new);
    }

    public Long create(CreateCourseDTO dto){
        User instructor = userService.findById(dto.instructorId());

        if (!instructor.getRole().equals(Role.INSTRUCTOR.name())){
            throw new UserMustBeInstructorException();
        }

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


    public InactiveCourseDataDTO inactive(InactiveCourseDTO dto){
        Course course = findById(dto.id());

        if (course.getStatus() == CourseStatus.INACTIVE){
            throw new CourseAlreadyInactiveException();
        }

        course.markAsInactive();
        Course courseUpdated = repository.save(course);
        return new InactiveCourseDataDTO(courseUpdated);
    }
}
