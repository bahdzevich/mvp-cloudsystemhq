package com.cloudsystemhq.controller.rest.impl;

import com.cloudsystemhq.controller.rest.AbstractCrudRestController;
import com.cloudsystemhq.model.dto.request.InfluenceOnPriceRequestDto;
import com.cloudsystemhq.model.dto.response.InfluenceOnPriceResponseDto;
import com.cloudsystemhq.service.IInfluenceOnPriceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/question/{questionId:[0-9]+}/answer/{responseId:[0-9]+}/influence",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class InfluenceOnPriceRestController
        extends AbstractCrudRestController<InfluenceOnPriceRequestDto, InfluenceOnPriceResponseDto, Long,
                IInfluenceOnPriceService> {

    @Autowired
    public InfluenceOnPriceRestController(IInfluenceOnPriceService influenceOnInvoiceService){
        super(influenceOnInvoiceService);
    }

    @PostMapping
    public ResponseEntity<InfluenceOnPriceResponseDto> create(@PathVariable Long responseId,
                                                              @RequestBody InfluenceOnPriceRequestDto influenceOnInvoice) {
        return service.create(responseId, influenceOnInvoice)
                .map(influence -> new ResponseEntity<>(influence, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<InfluenceOnPriceResponseDto>> findInfluencesByResponseId(@PathVariable(value = "questionId") Long questionId,
                                                                                       @PathVariable(value = "responseId") Long responseId) {
        return ResponseEntity.ok(service.findInfluencesByResponseId(responseId));
    }
}