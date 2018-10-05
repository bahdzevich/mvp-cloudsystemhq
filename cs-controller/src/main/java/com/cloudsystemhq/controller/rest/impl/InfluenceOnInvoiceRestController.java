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
        return influenceOnInvoiceService.create(responseId, influenceOnInvoice)
                .map(influence -> new ResponseEntity<>(influence, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping
    public ResponseEntity<InfluenceOnInvoice> findInfluenceByResponseId(@PathVariable(value = "questionId") Long questionId,
                                                           @PathVariable(value = "responseId") Long responseId) {
        return influenceOnInvoiceService.findInfluenceByResponseId(responseId)
                .map(influence -> new ResponseEntity<>(influence, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<InfluenceOnInvoice> update(@PathVariable(value = "questionId") Long questionId,
                                           @PathVariable(value = "responseId") Long responseId,
                                           @RequestBody InfluenceOnInvoice influenceOnInvoice){
        return influenceOnInvoiceService.update(responseId, influenceOnInvoice)
                .map(influence -> new ResponseEntity<>(influence, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping
    public ResponseEntity<InfluenceOnInvoice> delete(@PathVariable(value = "questionId") Long questionId,
                                           @PathVariable(value = "responseId") Long responseId) {
        return influenceOnInvoiceService.delete(responseId)
                .map(influence -> new ResponseEntity<>(influence, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}