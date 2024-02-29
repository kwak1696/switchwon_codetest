package dev.be.homework.solution;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.be.homework.solution.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping(value = "/balance/{userId}")
    @Operation(summary = "1. 잔액 조회")
    public BalanceResponse balance(@PathVariable(name = "userId") @Schema(description = "사용자 ID", example = "12345") String userId) {
        return paymentService.balance(userId);
    }

    @PostMapping(value = "/estimate")
    @Operation(summary = "2. 결제 예상 결과 조회")
    public EstimateResponse estimate(@RequestBody EstimateRequest request) {
        return paymentService.estimate(request);
    }

    @PostMapping(value = "/approval")
    @Operation(summary = "3. 결제 승인 요청")
    public ApprovalResponse approval(@RequestBody ApprovalRequest request) throws JsonProcessingException {
        return paymentService.approval(request);
    }
}
