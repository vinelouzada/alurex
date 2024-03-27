package br.com.alura.alurex.api.service;

import br.com.alura.alurex.api.dto.NpsMetricsDTO;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class NpsService {

    public BigDecimal calculate(NpsMetricsDTO npsMetricsDTO){

        BigDecimal promoters = BigDecimal.valueOf(npsMetricsDTO.promoters())
                        .divide(BigDecimal.valueOf(npsMetricsDTO.totalFeedbacks()), 2, RoundingMode.UP)
                .multiply(BigDecimal.valueOf(100));
        BigDecimal detractors = BigDecimal.valueOf(npsMetricsDTO.detractors())
                .divide(BigDecimal.valueOf(npsMetricsDTO.totalFeedbacks()), 2, RoundingMode.UP)
                .multiply(BigDecimal.valueOf(100));

        return promoters.subtract(detractors);
    }
}