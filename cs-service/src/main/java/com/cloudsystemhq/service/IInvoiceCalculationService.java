package com.cloudsystemhq.service;

import com.cloudsystemhq.model.domain.CustomerForm;

public interface IInvoiceCalculationService {
    Double calculate(CustomerForm customerForm);
}
