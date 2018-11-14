package com.cloudsystemhq.controller.rest.impl;

import com.cloudsystemhq.controller.rest.AbstractCrudRestController;
import com.cloudsystemhq.model.dto.request.CustomerRequestDto;
import com.cloudsystemhq.model.dto.response.CustomerResponseDto;
import com.cloudsystemhq.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/customer", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CustomerRestController
    extends
    AbstractCrudRestController<CustomerRequestDto, CustomerResponseDto, Long, ICustomerService> {

  @Autowired
  public CustomerRestController(ICustomerService customerService) {
    super(customerService);
  }

  @Override
  @PostMapping
  public ResponseEntity<CustomerResponseDto> create(@RequestBody CustomerRequestDto customerRequestDto) {
    return super.create(customerRequestDto);
  }

  @Override
  @GetMapping(value = "/{id:[0-9]+}")
  public ResponseEntity<CustomerResponseDto> findOne(@PathVariable Long id) {
    return super.findOne(id);
  }

  @Override
  @GetMapping
  public ResponseEntity<List<CustomerResponseDto>> findAll() {
    return super.findAll();
  }

  @Override
  @PutMapping(value = "/{id:[0-9]+}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<CustomerResponseDto> update(@PathVariable Long id, @RequestBody CustomerRequestDto customerRequestDto) {
    return super.update(id, customerRequestDto);
  }

  @Override
  @DeleteMapping(value = "/{id:[0-9]+}")
  public ResponseEntity<CustomerResponseDto> delete(@PathVariable Long id) {
    return super.delete(id);
  }

}
