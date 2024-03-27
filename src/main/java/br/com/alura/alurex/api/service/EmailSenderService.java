package br.com.alura.alurex.api.service;

import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    public void send(String recipientEmail, String subject, String body) {
        System.out.println(
                "Simulating sending email to [%s]:\n".formatted(recipientEmail)
        );
        System.out.println("""
            Subject: %s
            Body: %s
            """.formatted(subject, body));
        }
}
