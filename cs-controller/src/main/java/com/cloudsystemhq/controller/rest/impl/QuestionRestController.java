package com.cloudsystemhq.controller.rest.impl;

import com.cloudsystemhq.controller.rest.AbstractCrudRestController;
import com.cloudsystemhq.model.domain.Question;
import com.cloudsystemhq.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/question", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QuestionRestController extends AbstractCrudRestController<Question, Question, Long, IQuestionService> {

    @Autowired
    public QuestionRestController(IQuestionService questionService){
        super(questionService);
    }
}
