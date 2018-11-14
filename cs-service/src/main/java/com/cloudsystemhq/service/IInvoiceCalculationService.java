package com.cloudsystemhq.service;

import com.cloudsystemhq.model.domain.Answer;
import java.util.Set;

public interface IInvoiceCalculationService {

    Double calculate(Set<Answer> customerRespons);
}
