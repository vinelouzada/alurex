package br.com.alura.alurex.api.exception;

import br.com.alura.alurex.api.exception.User.DuplicateEmailException;
import br.com.alura.alurex.api.exception.User.DuplicateUsernameException;
import br.com.alura.alurex.api.exception.User.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleUserNotFound(UserNotFoundException ex){
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );

        return ResponseEntity.status(exceptionDetails.httpStatus()).body(exceptionDetails);
    }

    @ExceptionHandler({DuplicateEmailException.class, DuplicateUsernameException.class})
    public ResponseEntity<ExceptionDetails> handleDuplicateUserException(RuntimeException ex) {

        ExceptionDetails exceptionDetails = new ExceptionDetails(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );

        return ResponseEntity.status(exceptionDetails.httpStatus()).body(exceptionDetails);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<FormExceptionDetails>> handleArgumentNotValid(MethodArgumentNotValidException ex){
        List<FieldError> errors = ex.getFieldErrors();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.stream().map(FormExceptionDetails::new).toList());
    }
}
