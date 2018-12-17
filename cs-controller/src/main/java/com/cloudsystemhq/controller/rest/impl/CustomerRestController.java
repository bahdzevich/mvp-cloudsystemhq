package com.cloudsystemhq.controller.rest.impl;

import com.cloudsystemhq.controller.rest.AbstractCrudRestController;
import com.cloudsystemhq.model.dto.request.CustomerRequestDto;
import com.cloudsystemhq.model.dto.response.CustomerResponseDto;
import com.cloudsystemhq.service.ICustomerService;
import com.cloudsystemhq.service.smsApi.SendOtpResponse;
import com.cloudsystemhq.service.smsApi.VerifyOtpResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  public ResponseEntity<CustomerResponseDto> create(
      @Valid @RequestBody CustomerRequestDto customerRequestDto) {
    return super.create(customerRequestDto);
  }

  @PostMapping(value = "/{id:[0-9]+}/sendotp")
  public ResponseEntity<SendOtpResponse> sendOtp(@PathVariable Long id) {
    return ResponseEntity.ok(service.sendOtp(id));
  }

  @PostMapping(value = "/{id:[0-9]+}/verify/{code:[0-9]{6}}")
  public ResponseEntity<VerifyOtpResponse> verify(@PathVariable Long id,
      @PathVariable Integer code) {
    return ResponseEntity.ok(service.verifyOtp(id, code));
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
  public ResponseEntity<CustomerResponseDto> update(@PathVariable Long id,
      @Valid @RequestBody CustomerRequestDto customerRequestDto) {
    return super.update(id, customerRequestDto);
  }

  @Override
  @DeleteMapping(value = "/{id:[0-9]+}")
  public ResponseEntity<CustomerResponseDto> delete(@PathVariable Long id) {
    return super.delete(id);
  }

}
