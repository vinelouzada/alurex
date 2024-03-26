package br.com.alura.alurex.api.exception.User;

public class DuplicateEmailOrUsernameException extends RuntimeException{
    public DuplicateEmailOrUsernameException(){
        super("User with the same email or username already exists");
    }
}
