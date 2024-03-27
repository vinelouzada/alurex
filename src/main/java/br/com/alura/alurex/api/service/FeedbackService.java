package br.com.alura.alurex.api.service;

import br.com.alura.alurex.api.dto.CreateFeedbackDTO;
import br.com.alura.alurex.api.dto.FeedbackDataDTO;
import br.com.alura.alurex.api.dto.NpsMetricsDTO;
import br.com.alura.alurex.api.dto.NpsReportDTO;
import br.com.alura.alurex.api.exception.Feedback.FeedbackAlreadyExistsException;
import br.com.alura.alurex.api.model.Enrollment;
import br.com.alura.alurex.api.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private NpsService npsService;

    public List<NpsReportDTO> reportNPS(){
        List<NpsMetricsDTO> dto = enrollmentRepository.findNpsMetrics();

        List<NpsReportDTO> npsReportList = new ArrayList<>();
        for (NpsMetricsDTO metricsCourse: dto){
            BigDecimal nps = npsService.calculate(metricsCourse);

            npsReportList.add(new NpsReportDTO(metricsCourse, nps));
        }

        return  npsReportList;
    }

    public FeedbackDataDTO create(CreateFeedbackDTO dto){

        Enrollment enrollment = enrollmentService.findById(dto.idUser(), dto.idCourse());

        if (enrollment.getScore() != null){
            throw  new FeedbackAlreadyExistsException();
        }

        enrollment.setFeedback(dto);
        enrollmentRepository.save(enrollment);

        if (enrollment.getScore() < 6){
            sendNotificationToInstructor(enrollment);
        }

        return new FeedbackDataDTO(enrollment);
    }

    private void sendNotificationToInstructor(Enrollment enrollment){

        String emailInstructor = enrollment.getCourse().getInstructorEmail();
        String nameCourse = enrollment.getCourse().getName();
        String body = """
                Hello,
                
                Your course "%s" has received feedback.
                Score: %d
                Reason: %s
                Date: %s
                """.formatted(nameCourse, enrollment.getScore(),
                enrollment.getReason(), enrollment.getCreatedAtFeedback());

        emailSenderService.send(emailInstructor, "Feeback Course", body);
    }
}
