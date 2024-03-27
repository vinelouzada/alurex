package br.com.alura.alurex.api.dto;

public record NpsMetricsDTO(
        String nameCouse,
        Long promoters,
        Long detractors,
        Long totalFeedbacks
        )
{

}
