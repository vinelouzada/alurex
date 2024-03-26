package br.com.alura.alurex.api.service;

import br.com.alura.alurex.api.dto.LoginDTO;
import br.com.alura.alurex.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public String login(LoginDTO dto){
        var authToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        Authentication authentication = authenticationManager.authenticate(authToken);

        return tokenService.create((User) authentication.getPrincipal());
    }
}
