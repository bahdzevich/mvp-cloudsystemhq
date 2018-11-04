package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.Response;
import com.cloudsystemhq.repository.QuestionRepository;
import com.cloudsystemhq.repository.ResponseRepository;
import com.cloudsystemhq.service.IResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ResponseServiceImpl implements IResponseService{

    private final static Logger LOGGER = LoggerFactory.getLogger(ResponseServiceImpl.class.getName());
    private final ResponseRepository responseRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public ResponseServiceImpl(ResponseRepository responseRepository, QuestionRepository questionRepository){
        this.responseRepository = responseRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Optional<List<Response>> getResponsesByQuestionId(final Long questionId) {
        if (!questionRepository.existsById(questionId)){
            LOGGER.warn("There is no question with id=" + questionId);
        }
        return responseRepository.findResponsesByQuestionId(questionId);
    }

    @Override
    public Optional<Response> create(final Long questionId, final Response response) {
        if (!questionRepository.existsById(questionId)){
            LOGGER.warn("There is no question with id=" + questionId);
        }
        return questionRepository.findById(questionId).map(question -> {
            if (response.getInfluenceOnPrice() != null){
                response.getInfluenceOnPrice()
                        .forEach(influenceOnPrice -> influenceOnPrice.setResponse(response));
            }
            question.getResponses().add(response);
            response.setQuestion(question);
            questionRepository.save(question);
            return response; // id == null, because returned object not persisted yet
        });
    }

    public Optional<Response> findOne(final Long questionId, final Long responseId) {
        if (!questionRepository.existsById(questionId)){
            LOGGER.warn("There is no question with id=" + questionId);
        }
        return responseRepository.findById(responseId);
    }

    @Override
    public Optional<Response> update(final Long questionId, final Long responseId, final Response response) {
        if (!questionRepository.existsById(questionId)){
            LOGGER.warn("There is no question with id=" + questionId);
        }
        return responseRepository.findById(responseId).map(persistedResponse -> {
            persistedResponse.setText(response.getText());
            persistedResponse.setInfluenceOnPrice(response.getInfluenceOnPrice());
            return responseRepository.save(persistedResponse);
        });
    }

    @Override
    @Transactional
    public Optional<Response> delete(final Long questionId, final Long responseId) {
        if (!questionRepository.existsById(questionId)){
            LOGGER.warn("There is no question with id=" + questionId);
        }
        return responseRepository.findById(responseId).map(response -> {
            responseRepository.delete(response);
            return response;
        });
    }
}
