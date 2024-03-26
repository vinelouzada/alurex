package br.com.alura.alurex.api.exception.Course;

public class UserMustBeInstructorException extends RuntimeException{
    public UserMustBeInstructorException(){
        super("User must be Instructor");
    };
}
