package br.com.alura.alurex.api.controller;

import br.com.alura.alurex.api.dto.CreateUserDTO;
import br.com.alura.alurex.api.dto.UserDataDTO;
import br.com.alura.alurex.api.dto.UserDetailsByUsernameDTO;
import br.com.alura.alurex.api.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    @Transactional
    public ResponseEntity<UserDataDTO> create(@RequestBody @Valid CreateUserDTO dto, UriComponentsBuilder uriBuilder){
        UserDataDTO userDataDTO = this.service.create(dto);
        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(userDataDTO.id()).toUri();

        return ResponseEntity.created(uri).body(userDataDTO);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDetailsByUsernameDTO> findByUsername(@PathVariable String username){
        UserDetailsByUsernameDTO dto = this.service.findByUsername(username);
        return ResponseEntity.ok().body(dto);
    }

}
