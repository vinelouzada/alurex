package br.com.alura.alurex.api.exception.User;

public class DuplicateUsernameException extends RuntimeException{
    public DuplicateUsernameException(){
        super("User with the same username already exists");
    }
}
