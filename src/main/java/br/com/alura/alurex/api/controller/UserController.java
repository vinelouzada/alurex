package br.com.alura.alurex.api.controller;

import br.com.alura.alurex.api.dto.CreateUserDTO;
import br.com.alura.alurex.api.dto.UserDetailsByUsernameDTO;
import br.com.alura.alurex.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/user")
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CreateUserDTO dto, UriComponentsBuilder uriBuilder){
        Long idUser = this.service.create(dto);
        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(idUser).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity findByUsername(@PathVariable String username){
        UserDetailsByUsernameDTO dto = this.service.findByUsername(username);
        return ResponseEntity.ok().body(dto);
    }


}
