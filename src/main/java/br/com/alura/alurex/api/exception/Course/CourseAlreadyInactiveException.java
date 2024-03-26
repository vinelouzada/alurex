package br.com.alura.alurex.api.exception.Course;

public class CourseAlreadyInactiveException extends RuntimeException{
    public CourseAlreadyInactiveException(){
        super("Course already inactive");
    }
}
