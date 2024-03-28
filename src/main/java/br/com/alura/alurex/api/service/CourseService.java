package br.com.alura.alurex.api.service;

import br.com.alura.alurex.api.dto.*;
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

    public CreatedDataCourseDTO create(CreateCourseDTO dto){
        User instructor = userService.findById(dto.instructorId());

        if (!Role.INSTRUCTOR.name().equals(instructor.getRole())){
            throw new UserMustBeInstructorException();
        }

        Course course = dto.toModel(instructor);

        repository.save(course);

        return new CreatedDataCourseDTO(
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getStatus(),
                course.getCode(),
                instructor.getName()
        );
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

        if (CourseStatus.INACTIVE.equals(course.getStatus())){
            throw new CourseAlreadyInactiveException();
        }

        course.markAsInactive();

        return new InactiveCourseDataDTO(course);
    }
}
