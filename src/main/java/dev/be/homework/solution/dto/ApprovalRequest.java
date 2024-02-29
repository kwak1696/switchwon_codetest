package dev.be.homework.solution.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Data
@Getter
@Schema(description = "결제 승인 요청")
public class ApprovalRequest {
    @Schema(description = "사용자 ID", example = "12345")
    private final String userId;
    @Schema(description = "결제 금액", example = "200.00")
    private final BigDecimal amount;
    @Schema(description = "통화", example = "USD")
    private final String currency;

    @Schema(description = "상점 ID", example = "merchantId123")
    private final String merchantId;
    @Schema(description = "결제 방법", example = "creditCard")
    private final String paymentMethod;
    @Schema(description = "결제 상세 정보")
    private final PaymentDetails paymentDetails;

    @Data
    @Getter
    @Schema(description = "결제 상세 정보")
    public static class PaymentDetails {
        @Schema(description = "카드번호", example = "1234-5678-9123-4567")
        private final String cardNumber;
        @Schema(description = "카드 만료일", example = "12/24")
        private final String expiryDate;
        @Schema(description = "CVV", example = "123")
        private final String cvv;
    }
}
