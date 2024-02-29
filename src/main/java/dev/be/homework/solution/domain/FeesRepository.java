package dev.be.homework.solution.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeesRepository extends JpaRepository<FeesEntity, Long> {
    Optional<FeesEntity> findByUserIdAndMerchantIdAndCurrency(String userId, String merchantId, String currency);
}
