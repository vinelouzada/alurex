package br.com.alura.alurex.api.exception.Enrollment;

public class EnrollmentNotFoundException extends RuntimeException{
    public EnrollmentNotFoundException(){
        super("Enrollment not found");
    }
}
