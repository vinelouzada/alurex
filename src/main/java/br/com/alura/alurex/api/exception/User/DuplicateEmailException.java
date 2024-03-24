package br.com.alura.alurex.api.exception.User;

public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException(){
        super("User with the same email already exists");
    }
}
