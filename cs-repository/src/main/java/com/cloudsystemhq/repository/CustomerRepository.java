package com.cloudsystemhq.repository;

import com.cloudsystemhq.model.domain.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByEmail(String email);

  Optional<Customer> findCustomerByPhone(String phone);
}
