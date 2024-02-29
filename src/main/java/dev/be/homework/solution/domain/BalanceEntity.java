package dev.be.homework.solution.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table( name = "balance")
@NoArgsConstructor
@Getter
public class BalanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String userId;
    @Column
    private String currency;
    @Column
    private BigDecimal balance;

}
