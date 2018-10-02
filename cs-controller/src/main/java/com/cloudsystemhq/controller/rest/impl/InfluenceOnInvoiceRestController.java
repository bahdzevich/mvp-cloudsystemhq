package com.cloudsystemhq.controller.rest.impl;

import com.cloudsystemhq.model.domain.InfluenceOnInvoice;
import com.cloudsystemhq.service.IInfluenceOnInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/question/{questionId:[0-9]+}/response/{responseId:[0-9]+}/influence",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class InfluenceOnInvoiceRestController {

    private final IInfluenceOnInvoiceService influenceOnInvoiceService;

    @Autowired
    public InfluenceOnInvoiceRestController(IInfluenceOnInvoiceService influenceOnInvoiceService){
        this.influenceOnInvoiceService = influenceOnInvoiceService;
    }

    @PostMapping
    public ResponseEntity<InfluenceOnInvoice> create(@PathVariable(value = "questionId") Long questionId,
                                           @PathVariable(value = "responseId") Long responseId,
                                           @RequestBody InfluenceOnInvoice influenceOnInvoice) {
        InfluenceOnInvoice newInfluence = influenceOnInvoiceService.create(responseId, influenceOnInvoice);
        return new ResponseEntity<>(newInfluence, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<InfluenceOnInvoice> findInfluenceByResponseId(@PathVariable(value = "questionId") Long questionId,
                                                           @PathVariable(value = "responseId") Long responseId) {
        InfluenceOnInvoice influenceOnInvoice = influenceOnInvoiceService.findInfluenceByResponseId(responseId);
        return ResponseEntity.ok(influenceOnInvoice);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<InfluenceOnInvoice> update(@PathVariable(value = "questionId") Long questionId,
                                           @PathVariable(value = "responseId") Long responseId,
                                           @RequestBody InfluenceOnInvoice influenceOnInvoice){
        InfluenceOnInvoice updatedInfluenceOnInvoice = influenceOnInvoiceService.update(responseId, influenceOnInvoice);
        return ResponseEntity.ok(updatedInfluenceOnInvoice);
    }

    @DeleteMapping
    public ResponseEntity<InfluenceOnInvoice> delete(@PathVariable(value = "questionId") Long questionId,
                                           @PathVariable(value = "responseId") Long responseId) {
        return ResponseEntity.ok(influenceOnInvoiceService.delete(responseId));
    }
}
