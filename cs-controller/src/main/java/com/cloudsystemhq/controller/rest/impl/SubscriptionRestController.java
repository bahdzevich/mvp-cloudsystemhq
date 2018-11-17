package com.cloudsystemhq.controller.rest.impl;

import com.cloudsystemhq.controller.rest.AbstractCrudRestController;
import com.cloudsystemhq.model.dto.request.SubscriptionRequestDto;
import com.cloudsystemhq.model.dto.response.SubscriptionResponseDto;
import com.cloudsystemhq.service.ISubscriptionService;
import java.util.List;
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
@RequestMapping(value = "/api/subscription", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SubscriptionRestController extends
    AbstractCrudRestController<SubscriptionRequestDto, SubscriptionResponseDto, Long, ISubscriptionService> {

  public SubscriptionRestController(ISubscriptionService service) {
    super(service);
  }

  @Override
  @PostMapping
  public ResponseEntity<SubscriptionResponseDto> create(
      @RequestBody SubscriptionRequestDto responseDto) {
    return super.create(responseDto);
  }

  @Override
  @GetMapping(value = "/{id:[0-9]+}")
  public ResponseEntity<SubscriptionResponseDto> findOne(@PathVariable Long id) {
    return super.findOne(id);
  }

  @Override
  @GetMapping
  public ResponseEntity<List<SubscriptionResponseDto>> findAll() {
    return super.findAll();
  }

  @Override
  @PutMapping(value = "/{id:[0-9]+}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<SubscriptionResponseDto> update(@PathVariable Long id,
      @RequestBody SubscriptionRequestDto requestDto) {
    return super.update(id, requestDto);
  }

  @Override
  @DeleteMapping(value = "/{id:[0-9]+}")
  public ResponseEntity<SubscriptionResponseDto> delete(@PathVariable Long id) {
    return super.delete(id);
  }
}
