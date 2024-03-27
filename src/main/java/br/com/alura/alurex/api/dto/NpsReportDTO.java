package br.com.alura.alurex.api.dto;

import java.math.BigDecimal;

public record NpsReportDTO (String nameCouse,
                            Long promoters,
                            Long detractors,
                            Long totalFeedbacks,
                            BigDecimal nps) {
    public NpsReportDTO(NpsMetricsDTO dto, BigDecimal nps){
        this(dto.nameCouse(), dto.promoters(), dto.detractors(), dto.totalFeedbacks(), nps);
    }
}
