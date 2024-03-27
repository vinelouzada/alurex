package br.com.alura.alurex.api.service;

import br.com.alura.alurex.api.dto.CreateFeedbackDTO;
import br.com.alura.alurex.api.dto.FeedbackDataDTO;
import br.com.alura.alurex.api.exception.Feedback.FeedbackAlreadyExistsException;
import br.com.alura.alurex.api.model.Enrollment;
import br.com.alura.alurex.api.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FeedbackService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private EnrollmentService enrollmentService;

    public FeedbackDataDTO create(CreateFeedbackDTO dto){

        Enrollment enrollment = enrollmentService.findById(dto.idUser(), dto.idCourse());

        if (enrollment.getScore() != null){
            throw  new FeedbackAlreadyExistsException();
        }

        enrollment.setFeedback(dto);

        enrollmentRepository.save(enrollment);
        return new FeedbackDataDTO(enrollment);
    }
}
