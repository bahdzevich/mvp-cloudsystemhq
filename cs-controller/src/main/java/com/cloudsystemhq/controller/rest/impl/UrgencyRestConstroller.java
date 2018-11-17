package com.cloudsystemhq.controller.rest.impl;

import com.cloudsystemhq.controller.rest.AbstractCrudRestController;
import com.cloudsystemhq.model.dto.request.UrgencyRequestDto;
import com.cloudsystemhq.model.dto.response.UrgencyResponseDto;
import com.cloudsystemhq.service.IUrgencyService;
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
@RequestMapping(value = "/api/urgency", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UrgencyRestConstroller extends
    AbstractCrudRestController<UrgencyRequestDto, UrgencyResponseDto, Long, IUrgencyService> {

  public UrgencyRestConstroller(IUrgencyService service) {
    super(service);
  }

  @Override
  @PostMapping
  public ResponseEntity<UrgencyResponseDto> create(
      @RequestBody UrgencyRequestDto responseDto) {
    return super.create(responseDto);
  }

  @Override
  @GetMapping(value = "/{id:[0-9]+}")
  public ResponseEntity<UrgencyResponseDto> findOne(@PathVariable Long id) {
    return super.findOne(id);
  }

  @Override
  @GetMapping
  public ResponseEntity<List<UrgencyResponseDto>> findAll() {
    return super.findAll();
  }

  @Override
  @PutMapping(value = "/{id:[0-9]+}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<UrgencyResponseDto> update(@PathVariable Long id,
      @RequestBody UrgencyRequestDto requestDto) {
    return super.update(id, requestDto);
  }

  @Override
  @DeleteMapping(value = "/{id:[0-9]+}")
  public ResponseEntity<UrgencyResponseDto> delete(@PathVariable Long id) {
    return super.delete(id);
  }
}
