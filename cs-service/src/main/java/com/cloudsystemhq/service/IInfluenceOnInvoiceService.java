package com.cloudsystemhq.service;

import com.cloudsystemhq.model.domain.InfluenceOnInvoice;

import java.util.List;

public interface IInfluenceOnInvoiceService {
    InfluenceOnInvoice create(Long responseId, InfluenceOnInvoice influenceOnInvoice);
    InfluenceOnInvoice update(Long responseId, InfluenceOnInvoice influenceOnInvoice);
    List<InfluenceOnInvoice> findAll();
    InfluenceOnInvoice findInfluenceByResponseId(Long responseId);
    InfluenceOnInvoice delete(Long responseId);
}
