package br.com.alura.alurex.api.service;

import br.com.alura.alurex.api.dto.CreateFeedbackDTO;
import br.com.alura.alurex.api.dto.FeedbackDataDTO;
import br.com.alura.alurex.api.exception.Feedback.FeedbackAlreadyExistsException;
import br.com.alura.alurex.api.model.Course;
import br.com.alura.alurex.api.model.Enrollment;
import br.com.alura.alurex.api.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceTest {
    @InjectMocks
    private FeedbackService feedbackService;
    @Mock
    private EnrollmentService enrollmentService;
    @Mock
    private User user;
    @Mock
    private Course course;
    @Spy
    private Enrollment enrollment = new Enrollment();
    @Mock
    private EmailSenderService emailSenderService;


    @Test
    public void mustBeCreateAFeedback(){
        CreateFeedbackDTO createFeedbackDTO = new CreateFeedbackDTO(
                1L,1L,10,"xpto");
        given(enrollmentService.findById(createFeedbackDTO.idUser(),
                createFeedbackDTO.idCourse())).willReturn(enrollment);
        given(enrollment.getUser()).willReturn(user);
        given(enrollment.getCourse()).willReturn(course);
        given(enrollment.getUser().getId()).willReturn(createFeedbackDTO.idUser());
        given(enrollment.getCourse().getId()).willReturn(createFeedbackDTO.idCourse());
        given(enrollment.getReason()).willReturn(createFeedbackDTO.reason());

        FeedbackDataDTO dataDTO = feedbackService.create(createFeedbackDTO);
        then(enrollment).should().setFeedback(createFeedbackDTO);

        Assertions.assertEquals("xpto", dataDTO.reason());
        verifyNoInteractions(emailSenderService);
    }

    @Test
    public void mustBeCreateAFeedbackAndSendEmail(){
        CreateFeedbackDTO createFeedbackDTO = new CreateFeedbackDTO(
                1L,1L,3,"xpto");
        given(enrollmentService.findById(createFeedbackDTO.idUser(),
                createFeedbackDTO.idCourse())).willReturn(enrollment);
        given(enrollment.getUser()).willReturn(user);
        given(enrollment.getCourse()).willReturn(course);
        given(enrollment.getUser().getId()).willReturn(createFeedbackDTO.idUser());
        given(enrollment.getCourse().getId()).willReturn(createFeedbackDTO.idCourse());
        given(enrollment.getReason()).willReturn(createFeedbackDTO.reason());

        FeedbackDataDTO dataDTO = feedbackService.create(createFeedbackDTO);
        then(enrollment).should().setFeedback(createFeedbackDTO);

        Assertions.assertEquals("xpto", dataDTO.reason());
        verify(emailSenderService).send(any(),any(),any());
    }

    @Test
    public void mustThrowExceptionToFeedbackAlreadyExistsException(){
        CreateFeedbackDTO createFeedbackDTO = new CreateFeedbackDTO(
                1L,1L,10,"xpto");
        given(enrollmentService.findById(createFeedbackDTO.idUser(),
                createFeedbackDTO.idCourse())).willReturn(enrollment);
        given(enrollment.getScore()).willReturn(10);

        Assertions.assertThrows(FeedbackAlreadyExistsException.class, () -> feedbackService.create(createFeedbackDTO));
    }



}