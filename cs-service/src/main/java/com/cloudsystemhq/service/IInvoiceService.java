package com.cloudsystemhq.service;

import com.cloudsystemhq.model.domain.invoice.Invoice;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface IInvoiceService {

  Optional<Invoice> findOne(final Long invoiceId);

  Page<Invoice> findPage(final Integer page, final Integer size);

  Optional<Invoice> delete(final Long invoiceId);

  List<Invoice> findInvoicesByOrderId(final Long order);

  Optional<Invoice> update(final Long customerId, final Long invoiceId,
      final Invoice invoice);
}
