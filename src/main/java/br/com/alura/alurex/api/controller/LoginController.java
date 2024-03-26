package br.com.alura.alurex.api.controller;

import br.com.alura.alurex.api.dto.LoginDTO;
import br.com.alura.alurex.api.dto.TokenJwtDTO;
import br.com.alura.alurex.api.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping
    public ResponseEntity<TokenJwtDTO> login(@RequestBody @Valid LoginDTO dto){

        String tokenJWT = service.login(dto);

        return ResponseEntity.ok(new TokenJwtDTO(tokenJWT));
    }

}
