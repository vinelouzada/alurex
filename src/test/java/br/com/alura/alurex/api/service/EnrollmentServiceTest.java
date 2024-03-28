package br.com.alura.alurex.api.service;

import br.com.alura.alurex.api.dto.CreateEnrollmentDTO;
import br.com.alura.alurex.api.dto.CreatedDataEnrollmentDTO;
import br.com.alura.alurex.api.dto.InactiveCourseDataDTO;
import br.com.alura.alurex.api.enums.CourseStatus;
import br.com.alura.alurex.api.exception.Enrollment.CourseEnrollmentException;
import br.com.alura.alurex.api.exception.Enrollment.EnrollmentAlreadyExistsException;
import br.com.alura.alurex.api.model.Course;
import br.com.alura.alurex.api.model.Enrollment;
import br.com.alura.alurex.api.model.User;
import br.com.alura.alurex.api.repository.EnrollmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class EnrollmentServiceTest {

    @InjectMocks
    private EnrollmentService enrollmentService;

    @Mock
    private EnrollmentRepository repository;

    @Mock
    private User user;

    @Mock
    private Course course;

    @Mock
    private UserService userService;

    @Mock
    private CourseService courseService;


    @Test
    public void mustBeCreateAEnrollment(){
        CreateEnrollmentDTO enrollmentDTO = new CreateEnrollmentDTO(1L,1L);
        given(userService.findById(enrollmentDTO.idUser())).willReturn(user);
        given(courseService.findById(enrollmentDTO.idCourse())).willReturn(course);
        given(course.getStatus()).willReturn(CourseStatus.ACTIVE);
        given(repository.existsByUserIdAndCourseId(user.getId(),course.getId())).willReturn(false);

        Enrollment newEnrollment = new Enrollment(user, course);
        given(repository.save(newEnrollment)).willReturn(newEnrollment);

        CreatedDataEnrollmentDTO createdDto = enrollmentService.create(enrollmentDTO);

        Assertions.assertEquals(newEnrollment.getId().getUserId(), createdDto.idUser());
        Assertions.assertEquals(newEnrollment.getId().getCourseId(), createdDto.idCourse());
        Assertions.assertEquals(newEnrollment.getCreatedAtEnrollment(), createdDto.createdAt());
    }

    @Test
    public void mustThrowExceptionWhenEnrollmentAlreadyExists(){
        CreateEnrollmentDTO enrollmentDTO = new CreateEnrollmentDTO(1L,1L);
        given(userService.findById(enrollmentDTO.idUser())).willReturn(user);
        given(courseService.findById(enrollmentDTO.idCourse())).willReturn(course);
        given(course.getStatus()).willReturn(CourseStatus.ACTIVE);
        given(repository.existsByUserIdAndCourseId(user.getId(),course.getId())).willReturn(true);

        Assertions.assertThrows(EnrollmentAlreadyExistsException.class, () -> enrollmentService.create(enrollmentDTO));
    }

    @Test
    public void mustThrowExceptionWhenCourseIsInactive(){
        CreateEnrollmentDTO enrollmentDTO = new CreateEnrollmentDTO(1L,1L);
        given(userService.findById(enrollmentDTO.idUser())).willReturn(user);
        given(courseService.findById(enrollmentDTO.idCourse())).willReturn(course);
        given(course.getStatus()).willReturn(CourseStatus.INACTIVE);

        Assertions.assertThrows(CourseEnrollmentException.class, () -> enrollmentService.create(enrollmentDTO));
    }

}