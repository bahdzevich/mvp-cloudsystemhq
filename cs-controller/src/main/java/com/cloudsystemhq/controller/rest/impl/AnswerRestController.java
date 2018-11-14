package com.cloudsystemhq.controller.rest.impl;

import com.cloudsystemhq.controller.rest.AbstractCrudRestController;
import com.cloudsystemhq.model.dto.request.AnswerRequestDto;
import com.cloudsystemhq.model.dto.response.AnswerResponseDto;
import com.cloudsystemhq.service.IAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/question/{questionId:[0-9]+}/answer", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AnswerRestController
        extends AbstractCrudRestController<AnswerRequestDto, AnswerResponseDto, Long, IAnswerService>{

    @Autowired
    public AnswerRestController(IAnswerService responseService){
        super(responseService);
    }

    @PostMapping
    public ResponseEntity<AnswerResponseDto> create(@PathVariable Long questionId,
                                                    @RequestBody AnswerRequestDto responseRequestDto) {
        return service.create(questionId, responseRequestDto)
                .map(influence -> new ResponseEntity<>(influence, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @GetMapping(value = "/{answerId:[0-9]+}")
    public ResponseEntity<AnswerResponseDto> findOne(@PathVariable Long answerId) {
        return super.findOne(answerId);
    }

    @GetMapping
    public ResponseEntity<List<AnswerResponseDto>> findResponsesByQuestion(@PathVariable(value = "questionId") Long questionId) {
        return ResponseEntity.ok(service.getAnswersByQuestionId(questionId));
    }

    @Override
    @DeleteMapping(value = "/{answerId:[0-9]+}")
    public ResponseEntity<AnswerResponseDto> delete(@PathVariable Long answerId) {
        return super.delete(answerId);
    }

    @PutMapping(value = "/{answerId:[0-9]+}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AnswerResponseDto> update(@PathVariable(value = "questionId") Long questionId,
                                                    @PathVariable(value = "answerId") Long answerId,
                                                    @RequestBody AnswerRequestDto response){
        return service.update(questionId, answerId, response)
                .map(updatedResponse -> new ResponseEntity<>(updatedResponse,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
