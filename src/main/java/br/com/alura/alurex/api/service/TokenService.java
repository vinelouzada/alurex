package br.com.alura.alurex.api.service;


import br.com.alura.alurex.api.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    private static final String ISSUER = "alurex";

    public String create(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create().withIssuer(ISSUER)
                    .withSubject(user.getEmail())
                    .withExpiresAt(tokenExpiresAt())
                    .sign(algorithm);
        } catch (JWTCreationException ex){
            throw new RuntimeException("JWT Token cannot be created", ex);
        }
    }

    public String getSubject(String token){

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex){
            throw new RuntimeException("Invalid or expired JWT token: ");
        }
    }

    private Instant tokenExpiresAt(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
