package br.com.alura.alurex.api.service;

import br.com.alura.alurex.api.dto.CreateEnrollmentDTO;
import br.com.alura.alurex.api.enums.CourseStatus;
import br.com.alura.alurex.api.exception.Enrollment.CourseEnrollmentException;
import br.com.alura.alurex.api.exception.Enrollment.EnrollmentAlreadyExistsException;
import br.com.alura.alurex.api.exception.Enrollment.EnrollmentNotFoundException;
import br.com.alura.alurex.api.model.Course;
import br.com.alura.alurex.api.model.Enrollment;
import br.com.alura.alurex.api.model.User;
import br.com.alura.alurex.api.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;


    public Enrollment create(CreateEnrollmentDTO dto){
        User user = userService.findById(dto.idUser());
        Course course = courseService.findById(dto.idCourse());

        if (course.getStatus().equals(CourseStatus.INACTIVE)){
            throw new CourseEnrollmentException();
        }

        Enrollment enrollment = new Enrollment(user, course);

        boolean enrollmentExists = repository.existsByUserIdAndCourseId(user.getId(), course.getId());

        if (enrollmentExists){
            throw new EnrollmentAlreadyExistsException();
        }

        return repository.save(enrollment);
    }

    public Enrollment findById(Long idUser, Long idCourse){

       Optional<Enrollment> enrollment = repository.findByUserIdAndCourseId(idUser, idCourse);

       if (enrollment.isEmpty()){
           throw new EnrollmentNotFoundException();
       }

       return enrollment.get();
    }
}
