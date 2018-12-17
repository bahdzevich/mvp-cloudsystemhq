package com.cloudsystemhq.repository;

import com.cloudsystemhq.model.domain.order.Subscription;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

  Optional<Subscription> findByNumberOfMonth(Integer numberOfMonth);
}
