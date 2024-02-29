package dev.be.homework.solution.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@Table(name = "fees")
@NoArgsConstructor
@Getter
public class FeesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String userId;
    @Column
    private String merchantId;
    @Column
    private String currency;
    @Column
    private BigDecimal fee;
}
