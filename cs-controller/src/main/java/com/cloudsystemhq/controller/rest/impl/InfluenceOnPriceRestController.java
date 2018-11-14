package com.cloudsystemhq.controller.rest.impl;

import com.cloudsystemhq.controller.rest.AbstractCrudRestController;
import com.cloudsystemhq.model.dto.request.InfluenceOnPriceRequestDto;
import com.cloudsystemhq.model.dto.response.InfluenceOnPriceResponseDto;
import com.cloudsystemhq.service.IInfluenceOnPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/question/{questionId:[0-9]+}/answer/{answerId:[0-9]+}/influence",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class InfluenceOnPriceRestController
        extends AbstractCrudRestController<InfluenceOnPriceRequestDto, InfluenceOnPriceResponseDto, Long,
                IInfluenceOnPriceService> {

    @Autowired
    public InfluenceOnPriceRestController(IInfluenceOnPriceService influenceOnInvoiceService){
        super(influenceOnInvoiceService);
    }

    @PostMapping
    public ResponseEntity<InfluenceOnPriceResponseDto> create(@PathVariable Long answerId,
                                                              @RequestBody InfluenceOnPriceRequestDto influenceOnInvoice) {
        return service.create(answerId, influenceOnInvoice)
                .map(influence -> new ResponseEntity<>(influence, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<InfluenceOnPriceResponseDto>> findInfluencesByAnswerId(
            @PathVariable(value = "answerId") Long answerId) {
        return ResponseEntity.ok(service.findInfluencesByAnswerId(answerId));
    }

    @Override
    @GetMapping(value = "/{influenceId:[0-9]+}")
    public ResponseEntity<InfluenceOnPriceResponseDto> findOne(@PathVariable Long influenceId) {
        return super.findOne(influenceId);
    }

    @PutMapping(value = "/{influenceId:[0-9]+}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<InfluenceOnPriceResponseDto> update(
            @PathVariable Long answerId,
            @PathVariable Long influenceId,
            @RequestBody InfluenceOnPriceRequestDto influenceOnPriceRequestDto) {
        return service.update(answerId, influenceId, influenceOnPriceRequestDto)
                .map(updatedInfluence -> new ResponseEntity<>(updatedInfluence,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @DeleteMapping(value = "/{influenceId:[0-9]+}")
    public ResponseEntity<InfluenceOnPriceResponseDto> delete(@PathVariable Long influenceId) {
        return super.delete(influenceId);
    }

}