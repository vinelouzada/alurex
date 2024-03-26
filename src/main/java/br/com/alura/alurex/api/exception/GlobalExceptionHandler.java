package br.com.alura.alurex.api.exception;

import br.com.alura.alurex.api.exception.Course.CourseAlreadyInactiveException;
import br.com.alura.alurex.api.exception.Course.CourseNotFoundException;
import br.com.alura.alurex.api.exception.Course.UserMustBeInstructorException;
import br.com.alura.alurex.api.exception.Enrollment.CourseEnrollmentException;
import br.com.alura.alurex.api.exception.Enrollment.EnrollmentAlreadyExistsException;
import br.com.alura.alurex.api.exception.Enrollment.EnrollmentNotFoundException;
import br.com.alura.alurex.api.exception.User.DuplicateEmailOrUsernameException;
import br.com.alura.alurex.api.exception.User.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class, CourseNotFoundException.class, EnrollmentNotFoundException.class})
    public ResponseEntity<ExceptionDetails> handleNotFoundException(RuntimeException ex){

        ExceptionDetails exceptionDetails = new ExceptionDetails(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );

        return ResponseEntity.status(exceptionDetails.httpStatus()).body(exceptionDetails);
    }

    @ExceptionHandler({DuplicateEmailOrUsernameException.class, EnrollmentAlreadyExistsException.class,
            CourseAlreadyInactiveException.class, CourseEnrollmentException.class})
    public ResponseEntity<ExceptionDetails> handleDuplicateDataException(RuntimeException ex) {

        ExceptionDetails exceptionDetails = new ExceptionDetails(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );

        return ResponseEntity.status(exceptionDetails.httpStatus()).body(exceptionDetails);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class,})
    public ResponseEntity<List<FormExceptionDetails>> handleArgumentNotValid(MethodArgumentNotValidException ex){
        List<FieldError> errors = ex.getFieldErrors();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.stream().map(FormExceptionDetails::new).toList());
    }

    @ExceptionHandler(UserMustBeInstructorException.class)
    public ResponseEntity<ExceptionDetails> handleRolePermissionViolation(UserMustBeInstructorException ex){
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                ex.getMessage(),
                HttpStatus.FORBIDDEN,
                LocalDateTime.now()
        );

        return ResponseEntity.status(exceptionDetails.httpStatus()).body(exceptionDetails);
    }
}
