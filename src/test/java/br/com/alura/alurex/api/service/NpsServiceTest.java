package br.com.alura.alurex.api.service;

import br.com.alura.alurex.api.dto.NpsMetricsDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

class NpsServiceTest {

    @Test
    public void testCalculateNpsTo50Promoters25Detractors100Total(){
        NpsMetricsDTO npsMetricsDTO = new NpsMetricsDTO("Spring Boot", 50L, 25L, 100L);

        NpsService npsService = new NpsService();
        BigDecimal result = npsService.calculate(npsMetricsDTO);

        Assertions.assertEquals(0, BigDecimal.valueOf(25).compareTo(result));
    }

    @Test
    public void testCalculateNpsTo20Promoters50Detractors100Total(){
        NpsMetricsDTO npsMetricsDTO = new NpsMetricsDTO("PHP DDD", 20L, 50L, 100L);

        NpsService npsService = new NpsService();
        BigDecimal result = npsService.calculate(npsMetricsDTO);

        Assertions.assertEquals(0, BigDecimal.valueOf(-30).compareTo(result));
    }

    @Test
    public void testCalculateNpsTo1Promoters42Detractors67Total(){
        NpsMetricsDTO npsMetricsDTO = new NpsMetricsDTO("Java OO", 1L, 42L, 67L);

        NpsService npsService = new NpsService();
        BigDecimal result = npsService.calculate(npsMetricsDTO);

        Assertions.assertEquals(0, BigDecimal.valueOf(-61).compareTo(result));
    }

}