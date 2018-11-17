package com.cloudsystemhq.repository;

import com.cloudsystemhq.model.domain.order.InfrastructureProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfrastructureProviderRepository extends
    JpaRepository<InfrastructureProvider, Long> {

}
