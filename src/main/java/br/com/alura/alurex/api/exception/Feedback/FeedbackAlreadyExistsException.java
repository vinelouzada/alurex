package br.com.alura.alurex.api.exception.Feedback;

public class FeedbackAlreadyExistsException extends RuntimeException {
    public FeedbackAlreadyExistsException() {
        super("Feedback already exists for this enrollment.");
    }
}

