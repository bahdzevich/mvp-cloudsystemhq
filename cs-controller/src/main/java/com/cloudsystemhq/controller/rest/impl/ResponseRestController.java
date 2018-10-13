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

        return responseService.create(questionId, response)
                .map(createdResponse ->  new ResponseEntity<>(createdResponse, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Response>> findAll(@PathVariable(value = "questionId") Long questionId) {
        return responseService.getResponsesByQuestionId(questionId)
                .map(responses -> new ResponseEntity<>(responses,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{responseId:[0-9]+}")
    public ResponseEntity<Response> findOne(@PathVariable(value = "questionId") Long questionId,
                                                      @PathVariable(value = "responseId") Long responseId) {
        return responseService.findOne(questionId, responseId)
                .map(response -> new ResponseEntity<>(response,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{responseId:[0-9]+}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Response> update(@PathVariable(value = "questionId") Long questionId,
                                           @PathVariable(value = "responseId") Long responseId,
                                           @RequestBody Response response){
        return responseService.update(questionId, responseId, response)
                .map(updatedResponse -> new ResponseEntity<>(updatedResponse,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{responseId:[0-9]+}")
    public ResponseEntity<Response> delete(@PathVariable(value = "questionId") Long questionId,
                                           @PathVariable(value = "responseId") Long responseId) {
       return responseService.delete(questionId, responseId)
               .map(deletedRequest -> new ResponseEntity<>(deletedRequest,HttpStatus.OK))
               .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
