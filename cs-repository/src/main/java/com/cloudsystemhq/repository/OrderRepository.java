package com.cloudsystemhq.repository;

import com.cloudsystemhq.model.domain.order.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  List<Order> findOrdersByCustomerId(Long customerId);
}