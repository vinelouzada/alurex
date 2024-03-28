package br.com.alura.alurex.api.controller;

import br.com.alura.alurex.api.dto.*;
import br.com.alura.alurex.api.enums.CourseStatus;
import br.com.alura.alurex.api.service.CourseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping
    public Page<CourseDataDTO> all(@Valid
            @PageableDefault(size = 1) Pageable pageable,
            @RequestParam(required = false) CourseStatus status
            ){

        if (status != null){
            return service.allByStatus(pageable,status);
        }

        return service.all(pageable);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CreatedDataCourseDTO> create(@RequestBody @Valid CreateCourseDTO dto){
        CreatedDataCourseDTO courseDTO = this.service.create(dto);
        return ResponseEntity.ok(courseDTO);
    }

    @PatchMapping("/inactive")
    @Transactional
    public ResponseEntity<InactiveCourseDataDTO> inactive(@RequestBody @Valid InactiveCourseDTO dto){
        InactiveCourseDataDTO course = this.service.inactive(dto);

        return ResponseEntity.ok().body(course);
    }
}
