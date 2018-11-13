package com.cloudsystemhq.controller.rest.impl;

import com.cloudsystemhq.controller.rest.AbstractCrudRestController;
import com.cloudsystemhq.model.dto.request.ResponseRequestDto;
import com.cloudsystemhq.model.dto.response.ResponseResponseDto;
import com.cloudsystemhq.service.IResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/question/{questionId:[0-9]+}/response", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ResponseRestController
        extends AbstractCrudRestController<ResponseRequestDto, ResponseResponseDto, Long, IResponseService>{

    @Autowired
    public ResponseRestController(IResponseService responseService){
        super(responseService);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseResponseDto> create(@PathVariable Long questionId,
                                                      @RequestBody ResponseRequestDto responseRequestDto) {
        return service.create(questionId, responseRequestDto)
                .map(influence -> new ResponseEntity<>(influence, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @GetMapping(value = "/{responseId:[0-9]+}")
    public ResponseEntity<ResponseResponseDto> findOne(@PathVariable Long responseId) {
        return super.findOne(responseId);
    }

    @GetMapping
    public ResponseEntity<List<ResponseResponseDto>> findResponsesByQuestion(@PathVariable(value = "questionId") Long questionId) {
        return ResponseEntity.ok(service.getResponsesByQuestionId(questionId));
    }

    @Override
    @DeleteMapping(value = "/{responseId:[0-9]+}")
    public ResponseEntity<ResponseResponseDto> delete(@PathVariable Long responseId) {
        return super.delete(responseId);
    }

    @PutMapping(value = "/{responseId:[0-9]+}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseResponseDto> update(@PathVariable(value = "questionId") Long questionId,
                                           @PathVariable(value = "responseId") Long responseId,
                                           @RequestBody ResponseRequestDto response){
        return service.update(questionId, responseId, response)
                .map(updatedResponse -> new ResponseEntity<>(updatedResponse,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
