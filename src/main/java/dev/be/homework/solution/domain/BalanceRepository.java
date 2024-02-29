package dev.be.homework.solution.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BalanceRepository extends JpaRepository<BalanceEntity, Long> {
    Optional<BalanceEntity> findByUserId(String userId);
}
