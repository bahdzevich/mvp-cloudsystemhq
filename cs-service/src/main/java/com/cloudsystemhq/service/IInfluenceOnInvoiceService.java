package com.cloudsystemhq.service;

import com.cloudsystemhq.model.domain.InfluenceOnInvoice;


public interface IInfluenceOnInvoiceService {
    InfluenceOnInvoice create(Long responseId, InfluenceOnInvoice influenceOnInvoice);
    InfluenceOnInvoice update(Long responseId, InfluenceOnInvoice influenceOnInvoice);
    InfluenceOnInvoice findInfluenceByResponseId(Long responseId);
    InfluenceOnInvoice delete(Long responseId);
}
