package com.cloudsystemhq.security.service;

import com.cloudsystemhq.model.domain.Customer;

public interface CustomerRegistrationService {
  Customer createCustomer(Customer customer);
}
