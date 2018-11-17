package com.cloudsystemhq.controller.rest.impl;

import com.cloudsystemhq.model.domain.invoice.Invoice;
import com.cloudsystemhq.service.IInvoiceService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/customer/{customerId:[0-9]+}/order/{orderId:[0-9]+}/invoice", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class InvoiceRestController {

  private final IInvoiceService invoiceService;

  public InvoiceRestController(IInvoiceService invoiceService) {
    this.invoiceService = invoiceService;
  }

  @GetMapping(value = "/{invoiceId:[0-9]+}")
  public ResponseEntity<Invoice> findOne(@PathVariable Long invoiceId) {
    return invoiceService
        .findOne(invoiceId)
        .map(invoice -> new ResponseEntity<>(invoice, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping
  public ResponseEntity<List<Invoice>> findInvoicesByOrderId(
      @PathVariable(value = "orderId") Long orderId) {
    return ResponseEntity.ok(invoiceService.findInvoicesByOrderId(orderId));
  }


}
