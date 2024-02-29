package dev.be.homework.solution.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Data
@Getter
@Schema(description = "결제 예상 결과 조회 요청")
public class EstimateRequest {
    @Schema(description = "결제 금액", example = "150.00")
    private final BigDecimal amount;
    @Schema(description = "통화", example = "USD")
    private final String currency;
    @Schema(description = "상점ID", example = "merchantId123")
    private final String destination;
    @Schema(description = "사용자 ID", example = "12345")
    private final String userId;
}
