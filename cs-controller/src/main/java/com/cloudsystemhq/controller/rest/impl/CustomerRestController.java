package com.cloudsystemhq.controller.rest.impl;

import com.cloudsystemhq.controller.rest.AbstractCrudRestController;
import com.cloudsystemhq.model.domain.Customer;
import com.cloudsystemhq.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/customers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CustomerRestController
        extends AbstractCrudRestController<Customer, Customer, Long, ICustomerService> {

    @Autowired
    public CustomerRestController(ICustomerService customerService) {
        super(customerService);
    }
}
