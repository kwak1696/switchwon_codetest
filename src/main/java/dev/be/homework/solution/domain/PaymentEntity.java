package dev.be.homework.solution.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@NoArgsConstructor
@Getter
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String userId;
    @Column
    private String merchantId;
    @Column
    private String currency;
    @Column
    private BigDecimal amount;
    @Column
    private String paymentMethod;
    @Column
    private String paymentDetails;

    @Column
    @Setter
    private String status;
    @Column
    @Setter
    private LocalDateTime timestamp;

    public PaymentEntity(String userId, String merchantId, String currency, BigDecimal amount, String paymentMethod, String paymentDetails) {
        this.userId = userId;
        this.merchantId = merchantId;
        this.currency = currency;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDetails = paymentDetails;
    }
}
