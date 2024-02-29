package dev.be.homework.solution;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.be.homework.solution.domain.BalanceRepository;
import dev.be.homework.solution.domain.FeesRepository;
import dev.be.homework.solution.domain.PaymentEntity;
import dev.be.homework.solution.domain.PaymentRepository;
import dev.be.homework.solution.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Log4j2
public class PaymentService {
    private final BalanceRepository balanceRepository;
    private final FeesRepository feesRepository;
    private final PaymentRepository paymentRepository;
    private final ObjectMapper objectMapper;

    public BalanceResponse balance(String userId) {
        return balanceRepository.findByUserId(userId)
                .map(entity -> {
                            BigDecimal balance = getDecimal(entity.getBalance());
                            String currency = entity.getCurrency();
                            return new BalanceResponse(userId, balance, currency);
                        }
                ).orElseThrow(() -> new IllegalArgumentException("잔액 조회 실패"));
    }

    public EstimateResponse estimate(EstimateRequest request) {
        String userId = request.getUserId();
        String merchantId = request.getDestination();
        String currency = request.getCurrency();
        return feesRepository.findByUserIdAndMerchantIdAndCurrency(userId, merchantId, currency)
                .map(entity -> {
                    BigDecimal fee = getDecimal(entity.getFee());
                    BigDecimal amount = getDecimal(request.getAmount());
                    BigDecimal estimatedTotal = amount.add(fee);
                    return new EstimateResponse(estimatedTotal, fee, currency);
                })
                .orElseThrow(() -> new IllegalArgumentException("수수료 정보가 없습니다"));
    }

    public ApprovalResponse approval(ApprovalRequest request) throws JsonProcessingException {
        String userId = request.getUserId();
        String merchantId = request.getMerchantId();
        String currency = request.getCurrency();
        BigDecimal amount = getDecimal(request.getAmount());
        String paymentMethod = request.getPaymentMethod();
        ApprovalRequest.PaymentDetails paymentDetails = request.getPaymentDetails();

        String details = objectMapper.writeValueAsString(request.getPaymentDetails());


        PaymentEntity entity = paymentRepository.save(new PaymentEntity(userId, merchantId, currency, amount, paymentMethod, details));

        entity.setTimestamp(LocalDateTime.now());

        if (!"123".equals(paymentDetails.getCvv())) {
            entity.setStatus("error");
        } else {
            entity.setStatus("approved");
        }
        return new ApprovalResponse(paymentRepository.save(entity));
    }

    private BigDecimal getDecimal(Number n) {
        return new BigDecimal(n.toString()).setScale(2, RoundingMode.HALF_UP);
    }
}
