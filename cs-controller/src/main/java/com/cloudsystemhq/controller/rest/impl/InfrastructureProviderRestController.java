package com.cloudsystemhq.controller.rest.impl;

import com.cloudsystemhq.controller.rest.AbstractCrudRestController;
import com.cloudsystemhq.model.dto.request.InfrastructureProviderRequestDto;
import com.cloudsystemhq.model.dto.response.InfrastructureProviderResponseDto;
import com.cloudsystemhq.service.IInfrastructureProviderService;
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
@RequestMapping(value = "/api/infrastructureprovider", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class InfrastructureProviderRestController extends
    AbstractCrudRestController<InfrastructureProviderRequestDto, InfrastructureProviderResponseDto, Long, IInfrastructureProviderService> {

  public InfrastructureProviderRestController(IInfrastructureProviderService service) {
    super(service);
  }

  @Override
  @PostMapping
  public ResponseEntity<InfrastructureProviderResponseDto> create(
      @RequestBody InfrastructureProviderRequestDto responseDto) {
    return super.create(responseDto);
  }

  @Override
  @GetMapping(value = "/{id:[0-9]+}")
  public ResponseEntity<InfrastructureProviderResponseDto> findOne(@PathVariable Long id) {
    return super.findOne(id);
  }

  @Override
  @GetMapping
  public ResponseEntity<List<InfrastructureProviderResponseDto>> findAll() {
    return super.findAll();
  }

  @Override
  @PutMapping(value = "/{id:[0-9]+}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<InfrastructureProviderResponseDto> update(@PathVariable Long id,
      @RequestBody InfrastructureProviderRequestDto requestDto) {
    return super.update(id, requestDto);
  }

  @Override
  @DeleteMapping(value = "/{id:[0-9]+}")
  public ResponseEntity<InfrastructureProviderResponseDto> delete(@PathVariable Long id) {
    return super.delete(id);
  }
}
