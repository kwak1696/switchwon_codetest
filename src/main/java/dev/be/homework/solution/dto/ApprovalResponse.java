package dev.be.homework.solution.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dev.be.homework.solution.domain.PaymentEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "결제 승인 응답")
public class ApprovalResponse {
    @Schema(description = "결제 ID", example = "paymentId12345")
    private final String paymentId;
    @Schema(description = "결제 상태", example = "approved")
    private final String status;
    @Schema(description = "결제 금액", example = "200.00")
    private final BigDecimal amount;
    @Schema(description = "통화", example = "USD")
    private final String currency;
    @Schema(description = "결제시각", example = "2023-01-23T10:00:00Z")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private final LocalDateTime timestamp;

    public ApprovalResponse(PaymentEntity entity) {
        this.paymentId = "paymentId" + entity.getId();
        this.status = entity.getStatus();
        this.amount = entity.getAmount();
        this.currency = entity.getCurrency();
        this.timestamp = entity.getTimestamp();
    }
}
