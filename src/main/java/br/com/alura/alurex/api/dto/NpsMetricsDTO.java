package br.com.alura.alurex.api.dto;

public record NpsMetricsDTO(
        String nameCourse,
        Long promoters,
        Long detractors,
        Long totalFeedbacks
        )
{

}
