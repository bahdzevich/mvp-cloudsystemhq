package com.cloudsystemhq.service;

import com.cloudsystemhq.model.domain.Response;

import java.util.Set;

public interface IInvoiceCalculationService {
    Double calculate(Set<Response> customerResponses);
}
