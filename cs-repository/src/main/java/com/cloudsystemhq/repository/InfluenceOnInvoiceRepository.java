package com.cloudsystemhq.repository;

import com.cloudsystemhq.model.domain.InfluenceOnPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InfluenceOnInvoiceRepository extends JpaRepository<InfluenceOnPrice, Long> {
    Optional<InfluenceOnPrice> findByResponseId(Long responseId);
}
