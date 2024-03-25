package br.com.alura.alurex.api.exception.Course;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(){
        super("Course not found");
    }
}
