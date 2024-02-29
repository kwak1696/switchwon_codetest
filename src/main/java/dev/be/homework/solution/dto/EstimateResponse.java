package dev.be.homework.solution.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "견적 응답")
public class EstimateResponse {
    @Schema(description = "견적총액", example = "155.00")
    private final BigDecimal estimatedTotal;
    @Schema(description = "수수료", example = "5.00")
    private final BigDecimal fees;
    @Schema(description = "통화", example = "USD")
    private final String currency;
}
