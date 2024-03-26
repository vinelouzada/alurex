package br.com.alura.alurex.api.controller;

import br.com.alura.alurex.api.dto.CreateCourseDTO;
import br.com.alura.alurex.api.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService service;

    @PostMapping
    public ResponseEntity<CreateCourseDTO> create(@RequestBody @Valid CreateCourseDTO dto, UriComponentsBuilder uriBuilder){

        Long idCourse = this.service.create(dto);

        URI uri = uriBuilder.path("/course/{id}").buildAndExpand(idCourse).toUri();

        return ResponseEntity.created(uri).body(dto);
    }
}
