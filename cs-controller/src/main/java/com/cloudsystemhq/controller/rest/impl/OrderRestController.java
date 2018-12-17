package com.cloudsystemhq.controller.rest.impl;

import com.cloudsystemhq.controller.rest.AbstractCrudRestController;
import com.cloudsystemhq.model.dto.request.OrderRequestDto;
import com.cloudsystemhq.model.dto.response.OrderResponseDto;
import com.cloudsystemhq.service.IOrderService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping(value = "/api/customer/{customerId:[0-9]+}/order", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OrderRestController extends
    AbstractCrudRestController<OrderRequestDto, OrderResponseDto, Long, IOrderService> {

  @Autowired
  public OrderRestController(IOrderService orderService) {
    super(orderService);
  }

  @PostMapping
  public ResponseEntity<OrderResponseDto> create(@PathVariable Long customerId,
      @Valid @RequestBody OrderRequestDto orderRequestDto) {
    return service.create(customerId, orderRequestDto)
        .map(order -> new ResponseEntity<>(order, HttpStatus.CREATED))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @Override
  @GetMapping(value = "/{orderId:[0-9]+}")
  public ResponseEntity<OrderResponseDto> findOne(@PathVariable Long orderId) {
    return super.findOne(orderId);
  }

  @GetMapping
  public ResponseEntity<List<OrderResponseDto>> findOrdersByCustomerId(
      @PathVariable(value = "customerId") Long customerId) {
    return ResponseEntity.ok(service.findOrdersByCustomerId(customerId));
  }

  @Override
  @DeleteMapping(value = "/{orderId:[0-9]+}")
  public ResponseEntity<OrderResponseDto> delete(@PathVariable Long orderId) {
    return super.delete(orderId);
  }

  @PutMapping(value = "/{orderId:[0-9]+}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<OrderResponseDto> update(
      @PathVariable(value = "customerId") Long customerId,
      @PathVariable(value = "orderId") Long orderId,
      @RequestBody OrderRequestDto orderRequestDto) {
    return service.update(customerId, orderId, orderRequestDto)
        .map(updatedResponse -> new ResponseEntity<>(updatedResponse, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
