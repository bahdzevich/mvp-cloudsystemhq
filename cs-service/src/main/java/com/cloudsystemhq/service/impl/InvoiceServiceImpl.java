package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.invoice.Invoice;
import com.cloudsystemhq.repository.InvoiceRepository;
import com.cloudsystemhq.repository.OrderRepository;
import com.cloudsystemhq.service.IInvoiceService;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements IInvoiceService {

  private final static Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class.getName());
  private final OrderRepository orderRepository;
  private final InvoiceRepository invoiceRepository;

  public InvoiceServiceImpl(
      InvoiceRepository invoiceRepository,
      OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
    this.invoiceRepository = invoiceRepository;
  }

  public Optional<Invoice> create(final Long orderId, final Invoice invoice) {
    return orderRepository.findById(orderId).map(order -> {
      order.getInvoices().add(invoice);
      invoice.setOrder(order);
      orderRepository.save(order);
      return invoice;
    });
  }

  @Override
  public Optional<Invoice> findOne(final Long invoiceId) {
    return invoiceRepository.findById(invoiceId);
  }

  @Override
  public List<Invoice> findInvoicesByOrderId(final Long orderId) {
    if (!orderRepository.existsById(orderId)) {
      LOGGER.warn("There is no invoice with id=" + orderId);
    }
    return invoiceRepository.findInvoicesByOrderId(orderId);
  }

  @Override
  public Page<Invoice> findPage(final Integer page, final Integer size) {
    return invoiceRepository.findAll(PageRequest.of(page, size));
  }

  @Override
  public Optional<Invoice> update(final Long orderId, final Long invoiceId,
      final Invoice invoice) {
    if (!orderRepository.existsById(orderId)) {
      LOGGER.warn("There is no order with id=" + orderId);
    }
    return invoiceRepository
        .findById(invoiceId)
        .map(updateEntity(invoice));
  }

  @Override
  public Optional<Invoice> delete(final Long invoiceId) {
    Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);
    invoiceOptional.ifPresent(invoiceRepository::delete);
    return invoiceOptional;
  }

  private UnaryOperator<Invoice> updateEntity(Invoice invoice) {
    return persistedInvoice -> {
      persistedInvoice.setDevopsPriceDescription(invoice.getDevopsPriceDescription());
      persistedInvoice
          .setInfrastructurePriceDescription(invoice.getInfrastructurePriceDescription());
      persistedInvoice.setSupportPriceDescription(invoice.getSupportPriceDescription());
      return invoiceRepository.save(persistedInvoice);
    };
  }
}
