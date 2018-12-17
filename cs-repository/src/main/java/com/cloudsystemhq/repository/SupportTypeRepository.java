package com.cloudsystemhq.repository;

import com.cloudsystemhq.model.domain.order.SupportType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportTypeRepository extends JpaRepository<SupportType, Long> {

  Optional<SupportType> findByType(String type);
}
