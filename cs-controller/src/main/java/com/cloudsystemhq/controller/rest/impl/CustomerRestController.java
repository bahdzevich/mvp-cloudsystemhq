package com.cloudsystemhq.controller.rest.impl;

import com.cloudsystemhq.controller.rest.AbstractCrudRestController;
import com.cloudsystemhq.model.dto.request.CustomerRequestDto;
import com.cloudsystemhq.model.dto.response.CustomerResponseDto;
import com.cloudsystemhq.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/customers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CustomerRestController
    extends
    AbstractCrudRestController<CustomerRequestDto, CustomerResponseDto, Long, ICustomerService> {

  @Autowired
  public CustomerRestController(ICustomerService customerService) {
    super(customerService);
  }
}
