package com.cloudsystemhq.repository;

import com.cloudsystemhq.model.domain.InfluenceOnInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InfluenceOnInvoiceRepository extends JpaRepository<InfluenceOnInvoice, Long> {
    Optional<InfluenceOnInvoice> findByResponseId(Long responseId);
}
