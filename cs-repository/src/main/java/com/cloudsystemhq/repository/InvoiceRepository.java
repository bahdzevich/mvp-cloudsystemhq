package com.cloudsystemhq.repository;

import com.cloudsystemhq.model.domain.invoice.Invoice;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

  List<Invoice> findInvoicesByOrderId(Long customerId);
}
