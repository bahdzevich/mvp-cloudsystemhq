package com.cloudsystemhq.controller.rest;

import com.cloudsystemhq.model.domain.Question;
import com.cloudsystemhq.model.domain.QuestionType;
import com.cloudsystemhq.model.domain.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/question/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TestQuestionnaireController {

    @GetMapping
    public ResponseEntity<List<Question>> loadQuestionnaire() {
        List<Question> responseList = new ArrayList<>();
        Question question = new Question();
        question.setId(1L);
        Set<Response> responses = new HashSet<>();
        Response response = new Response();
        response.setId(1L);
        response.setText("resp text");
        response.setValue("value");
        responses.add(response);
        question.setResponses(responses);
        question.setTitle("Title");
        question.setType(QuestionType.CHECKBOX);
        responseList.add(question);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}
