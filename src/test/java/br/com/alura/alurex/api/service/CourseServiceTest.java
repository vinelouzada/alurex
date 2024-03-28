package br.com.alura.alurex.api.service;

import br.com.alura.alurex.api.dto.*;
import br.com.alura.alurex.api.enums.CourseStatus;
import br.com.alura.alurex.api.enums.Role;
import br.com.alura.alurex.api.exception.Course.CourseAlreadyInactiveException;
import br.com.alura.alurex.api.exception.Course.UserMustBeInstructorException;
import br.com.alura.alurex.api.model.Course;
import br.com.alura.alurex.api.model.User;
import br.com.alura.alurex.api.repository.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {
    @InjectMocks
    private CourseService courseService;
    @Mock
    private UserService userService;
    private CreateCourseDTO dto;
    @Mock
    private User user;
    @Captor
    private ArgumentCaptor<Course> courseCaptor;
    @Spy
    private Course course;
    @Mock
    private CourseRepository repository;

    @Mock
    private InactiveCourseDTO inactiveCourseDTO;
    @Mock
    private InactiveCourseDataDTO inactiveCourseDataDTO;

    @Mock
    private CreateCourseDTO createCourseDTO;



    @Test
    public void mustBeCreateACourse(){
        this.dto = new CreateCourseDTO("Java DDD",
                "xpto", CourseStatus.ACTIVE,
                "java-ddd", 1L);

        given(userService.findById(dto.instructorId())).willReturn(user);
        given(user.getRole()).willReturn(Role.INSTRUCTOR.name());

        courseService.create(dto);

        then(repository).should().save(courseCaptor.capture());
        Course course  = courseCaptor.getValue();

        Assertions.assertEquals(user, course.getInstructor());
        Assertions.assertEquals("Java DDD", course.getName());
        Assertions.assertEquals("xpto", course.getDescription());
        Assertions.assertEquals(CourseStatus.ACTIVE, course.getStatus());
        Assertions.assertEquals("java-ddd", course.getCode());
        Assertions.assertEquals(user, course.getInstructor());
    }

    @Test
    public void mustThrowUserMustBeInstructorExceptionToUserStudent(){
        given(userService.findById(createCourseDTO.instructorId())).willReturn(user);
        given(user.getRole()).willReturn(Role.STUDENT.name());

        Assertions.assertThrows(UserMustBeInstructorException.class, () -> courseService.create(createCourseDTO));
    }

    @Test
    public void mustInactiveACourse(){

        given(courseService.findById(inactiveCourseDTO.id())).willReturn(course);

        courseService.inactive(inactiveCourseDTO);

        then(course).should().markAsInactive();

        Assertions.assertEquals(CourseStatus.INACTIVE, course.getStatus());
    }

    @Test
    public void mustThrowCourseAlreadyInactiveException(){
        given(courseService.findById(inactiveCourseDTO.id())).willReturn(course);
        given(course.getStatus()).willReturn(CourseStatus.INACTIVE);


        Assertions.assertThrows(CourseAlreadyInactiveException.class, () -> courseService.inactive(inactiveCourseDTO));
    }

}