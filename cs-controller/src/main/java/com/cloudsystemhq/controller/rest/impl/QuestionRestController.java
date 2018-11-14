package com.cloudsystemhq.controller.rest.impl;

import com.cloudsystemhq.controller.rest.AbstractCrudRestController;
import com.cloudsystemhq.model.dto.request.QuestionRequestDto;
import com.cloudsystemhq.model.dto.response.QuestionResponseDto;
import com.cloudsystemhq.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/question", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QuestionRestController
        extends AbstractCrudRestController<QuestionRequestDto, QuestionResponseDto, Long, IQuestionService> {

    @Autowired
    public QuestionRestController(IQuestionService questionService){
        super(questionService);
    }

    @Override
    @PostMapping
    public ResponseEntity<QuestionResponseDto> create(@RequestBody QuestionRequestDto questionRequestDto) {
       return super.create(questionRequestDto);
    }

    @Override
    @GetMapping(value = "/{id:[0-9]+}")
    public ResponseEntity<QuestionResponseDto> findOne(@PathVariable Long id) {
        return super.findOne(id);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<QuestionResponseDto>> findAll() {
        return super.findAll();
    }

    @Override
    @PutMapping(value = "/{id:[0-9]+}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<QuestionResponseDto> update(@PathVariable Long id, @RequestBody QuestionRequestDto questionRequestDto) {
        return super.update(id, questionRequestDto);
    }

    @Override
    @DeleteMapping(value = "/{id:[0-9]+}")
    public ResponseEntity<QuestionResponseDto> delete(@PathVariable Long id) {
        return super.delete(id);
    }
}
