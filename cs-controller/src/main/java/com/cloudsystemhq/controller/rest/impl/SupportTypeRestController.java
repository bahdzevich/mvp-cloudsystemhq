package com.cloudsystemhq.controller.rest.impl;

import com.cloudsystemhq.controller.rest.AbstractCrudRestController;
import com.cloudsystemhq.model.dto.request.SupportTypeRequestDto;
import com.cloudsystemhq.model.dto.response.SupportTypeResponseDto;
import com.cloudsystemhq.service.ISupportTypeService;
import java.util.List;
import javax.validation.Valid;
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
@RequestMapping(value = "/api/supporttype", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SupportTypeRestController extends
    AbstractCrudRestController<SupportTypeRequestDto, SupportTypeResponseDto, Long, ISupportTypeService> {

  public SupportTypeRestController(ISupportTypeService service) {
    super(service);
  }

  @Override
  @PostMapping
  public ResponseEntity<SupportTypeResponseDto> create(
      @Valid @RequestBody SupportTypeRequestDto responseDto) {
    return super.create(responseDto);
  }

  @Override
  @GetMapping(value = "/{id:[0-9]+}")
  public ResponseEntity<SupportTypeResponseDto> findOne(@PathVariable Long id) {
    return super.findOne(id);
  }

  @Override
  @GetMapping
  public ResponseEntity<List<SupportTypeResponseDto>> findAll() {
    return super.findAll();
  }

  @Override
  @PutMapping(value = "/{id:[0-9]+}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<SupportTypeResponseDto> update(@PathVariable Long id,
      @Valid @RequestBody SupportTypeRequestDto requestDto) {
    return super.update(id, requestDto);
  }

  @Override
  @DeleteMapping(value = "/{id:[0-9]+}")
  public ResponseEntity<SupportTypeResponseDto> delete(@PathVariable Long id) {
    return super.delete(id);
  }
}
