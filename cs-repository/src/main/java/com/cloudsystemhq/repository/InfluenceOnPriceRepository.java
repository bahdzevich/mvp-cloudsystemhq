package com.cloudsystemhq.repository;

import com.cloudsystemhq.model.domain.InfluenceOnPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfluenceOnPriceRepository extends JpaRepository<InfluenceOnPrice, Long> {
    List<InfluenceOnPrice> findByAnswerId(Long responseId);
}
