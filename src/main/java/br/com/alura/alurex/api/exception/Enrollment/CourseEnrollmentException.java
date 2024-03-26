package br.com.alura.alurex.api.exception.Enrollment;

public class CourseEnrollmentException extends RuntimeException{
    public CourseEnrollmentException(){
        super("Course Enrollment Unavailable");
    }
}
