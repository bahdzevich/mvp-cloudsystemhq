package com.cloudsystemhq.repository;

import com.cloudsystemhq.model.domain.order.Urgency;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrgencyRepository extends JpaRepository<Urgency, Long> {

  Optional<Urgency> findUrgencyByType(String type);
}
