package br.com.alura.alurex.api.controller;

import br.com.alura.alurex.api.dto.CreateEnrollmentDTO;
import br.com.alura.alurex.api.dto.CreatedDataEnrollmentDTO;
import br.com.alura.alurex.api.dto.FindEnrollmentDTO;
import br.com.alura.alurex.api.model.Enrollment;
import br.com.alura.alurex.api.service.EnrollmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentService service;

    @PostMapping
    @Transactional
    public ResponseEntity<CreatedDataEnrollmentDTO> create(@RequestBody @Valid CreateEnrollmentDTO dto, UriComponentsBuilder uriBuilder){
        CreatedDataEnrollmentDTO enrollmentDTO = this.service.create(dto);

        URI uri = uriBuilder.path("/enrollment/{userId}/{courseId}")
                .buildAndExpand(enrollmentDTO.idUser(), enrollmentDTO.idCourse())
                .toUri();

        return ResponseEntity.created(uri).body(enrollmentDTO);
    }

    @GetMapping("/{userId}/{courseId}")
    public ResponseEntity<FindEnrollmentDTO> findById(@PathVariable Long userId, @PathVariable Long courseId){
        Enrollment enrollment = this.service.findById(userId, courseId);

        return ResponseEntity.ok().body(new FindEnrollmentDTO(enrollment));
    }
}
