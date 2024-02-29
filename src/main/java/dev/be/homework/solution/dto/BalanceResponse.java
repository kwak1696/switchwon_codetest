package dev.be.homework.solution.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "잔액 조회 응답")
public class BalanceResponse {
    @Schema(description = "사용자 ID", example = "12345")
    private final String userId;
    @Schema(description = "잔액", example = "1000.00")
    private final BigDecimal balance;
    @Schema(description = "통화", example = "USD")
    private final String currency;
}
