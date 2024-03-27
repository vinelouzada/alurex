package br.com.alura.alurex.api.controller;

import br.com.alura.alurex.api.dto.CreateFeedbackDTO;
import br.com.alura.alurex.api.dto.FeedbackDataDTO;
import br.com.alura.alurex.api.service.FeedbackService;
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
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService service;

    @PostMapping
    public ResponseEntity<FeedbackDataDTO> create(@RequestBody @Valid CreateFeedbackDTO dto, UriComponentsBuilder uriBuilder){
        FeedbackDataDTO feedbackDataDTO = this.service.create(dto);

        URI uri = uriBuilder.path("/feedback/{userId}/{courseId}")
                .buildAndExpand(feedbackDataDTO.idUser(), feedbackDataDTO.idCourse())
                .toUri();

        return ResponseEntity.created(uri).body(feedbackDataDTO);
    }
}
