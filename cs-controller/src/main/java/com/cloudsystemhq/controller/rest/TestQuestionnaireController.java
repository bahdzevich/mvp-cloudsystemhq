package com.cloudsystemhq.controller.rest;

import com.cloudsystemhq.model.domain.Question;
import com.cloudsystemhq.model.domain.QuestionType;
import com.cloudsystemhq.model.domain.Response;
import com.cloudsystemhq.model.dto.response.QuestionnairePageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/question/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TestQuestionnaireController {

    private static Long nextId = Long.valueOf(1);

    private long getNextId() {
        nextId++;
        return nextId;
    }

    private List<List<Question>> questionnaire;

    public TestQuestionnaireController() {
        this.questionnaire = new ArrayList<>();
        questionnaire.add(buildQuestionnairePage());
        questionnaire.add(buildQuestionnairePage());
        questionnaire.add(buildQuestionnairePage());
    }

    @GetMapping(value = "/page/{num}")
    public ResponseEntity<QuestionnairePageDto> loadQuestionnairePage(@PathVariable("num") Integer num) {
        QuestionnairePageDto dto = new QuestionnairePageDto();
        dto.setQuestions(this.questionnaire.get(num));
        dto.setLastPage(num == 2);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/full")
    public ResponseEntity<List<List<Question>>> loadQuestionnaire() {
        return new ResponseEntity<>(questionnaire, HttpStatus.OK);
    }

    private List<Question> buildQuestionnairePage() {
        List<Question> responseList = new ArrayList<>();
        responseList.add(buildQuestion(QuestionType.CHECKBOX));
        responseList.add(buildQuestion(QuestionType.RADIO_BUTTON));
        responseList.add(buildQuestion(QuestionType.TEXT));
        return responseList;
    }

    private Response buildResponse(String text, String value) {
        Response response = new Response();
        response.setId(getNextId());
        response.setText(text);
        response.setValue(value);
        return response;
    }

    private Question buildQuestion(QuestionType questionType) {
        Question question = new Question();
        question.setId(getNextId());
        Set<Response> responses = new HashSet<>();
        responses.add(buildResponse("Text1", "Val1"));
        responses.add(buildResponse("Text2", "Val2"));
        question.setResponses(responses);
        question.setTitle("Title"+question.getId());
        question.setType(questionType);
        return question;
    }
}
