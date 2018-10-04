package com.cloudsystemhq.controller.rest.impl;

import com.cloudsystemhq.model.domain.Response;
import com.cloudsystemhq.service.IResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/question/{questionId:[0-9]+}/response", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ResponseRestController{

    private final IResponseService responseService;

    @Autowired
    public ResponseRestController(IResponseService responseService){
        this.responseService = responseService;
    }

    @PostMapping
    public ResponseEntity<Response> create(@PathVariable(value = "questionId") Long questionId,
                                           @RequestBody Response response) {
        Response newResponse = responseService.create(questionId, response);
        return new ResponseEntity<>(newResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Response>> findAll(@PathVariable(value = "questionId") Long questionId) {
        List<Response> responses = responseService.getResponsesByQuestionId(questionId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping(value = "/{responseId:[0-9]+}")
    public ResponseEntity<Response> findOne(@PathVariable(value = "questionId") Long questionId,
                                                      @PathVariable(value = "responseId") Long responseId) {
        Response response = responseService.findOne(responseId);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{responseId:[0-9]+}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Response> update(@PathVariable(value = "questionId") Long questionId,
                                           @PathVariable(value = "responseId") Long responseId,
                                           @RequestBody Response response){
        Response updatedResponse = responseService.update(questionId, responseId, response);
        return ResponseEntity.ok(updatedResponse);
    }

    @DeleteMapping(value = "/{responseId:[0-9]+}")
    public ResponseEntity<Response> delete(@PathVariable(value = "questionId") Long questionId,
                                           @PathVariable(value = "responseId") Long responseId) {
       return ResponseEntity.ok(responseService.delete(questionId, responseId));
    }
}
