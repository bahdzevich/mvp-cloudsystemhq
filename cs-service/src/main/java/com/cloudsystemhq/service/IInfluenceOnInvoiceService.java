package com.cloudsystemhq.service;

import com.cloudsystemhq.model.domain.InfluenceOnPrice;

import java.util.Optional;


public interface IInfluenceOnInvoiceService {
    Optional<InfluenceOnPrice> create(Long responseId, InfluenceOnPrice influenceOnInvoice);
    Optional<InfluenceOnPrice> update(Long responseId, InfluenceOnPrice influenceOnInvoice);
    Optional<InfluenceOnPrice> findInfluenceByResponseId(Long responseId);
    Optional<InfluenceOnPrice> delete(Long responseId);
}
