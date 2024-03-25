package br.com.alura.alurex.api.exception.Enrollment;

public class EnrollmentAlreadyExistsException extends RuntimeException{
    public EnrollmentAlreadyExistsException(){
        super("Enrollment already exists for this student and course.");
    }
}
