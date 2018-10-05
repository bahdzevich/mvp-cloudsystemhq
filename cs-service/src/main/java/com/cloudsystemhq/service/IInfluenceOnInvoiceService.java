package com.cloudsystemhq.service;

import com.cloudsystemhq.model.domain.InfluenceOnInvoice;

import java.util.Optional;


public interface IInfluenceOnInvoiceService {
    Optional<InfluenceOnInvoice> create(Long responseId, InfluenceOnInvoice influenceOnInvoice);
    Optional<InfluenceOnInvoice> update(Long responseId, InfluenceOnInvoice influenceOnInvoice);
    Optional<InfluenceOnInvoice> findInfluenceByResponseId(Long responseId);
    Optional<InfluenceOnInvoice> delete(Long responseId);
}
