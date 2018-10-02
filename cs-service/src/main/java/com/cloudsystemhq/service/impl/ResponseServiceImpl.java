package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.exception.ResourceNotFoundException;
import com.cloudsystemhq.model.domain.Response;
import com.cloudsystemhq.repository.QuestionRepository;
import com.cloudsystemhq.repository.ResponseRepository;
import com.cloudsystemhq.service.IResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Set;

@Service
public class ResponseServiceImpl implements IResponseService{

    private final ResponseRepository responseRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public ResponseServiceImpl(ResponseRepository responseRepository, QuestionRepository questionRepository){
        this.responseRepository = responseRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Set<Response> getResponsesByQuestionId(Long questionId) {
        Assert.notNull(questionId, "Question id is null.");
        return responseRepository.findResponsesByQuestionId(questionId);
    }

    @Override
    public Response create(final Long questionId, final Response response) {
        Assert.notNull(response, "Question id is null.");
        Assert.notNull(response, "Response is null.");
        return questionRepository.findById(questionId).map(question -> {
            if (response.getInfluenceOnInvoice() != null){
                response.getInfluenceOnInvoice().setResponse(response);
            }
            question.getResponses().add(response);
            response.setQuestion(question);
            questionRepository.save(question);
            return response; // id == null, because returned object not persisted yet
        }).orElseThrow(() -> new ResourceNotFoundException("QuestionId " + questionId + " not found"));
    }

    public Response findOne(final Long responseId) {
        Assert.notNull(responseId, "Response id is null.");
        return responseRepository.findById(responseId)
                .orElseThrow(() -> new ResourceNotFoundException("ResponseId " + responseId + " not found"));
    }

    @Override
    public Response update(final Long questionId, final Long responseId, final Response response) {
        if(!questionRepository.existsById(questionId)) {
            throw new ResourceNotFoundException("QuestionId " + questionId + " not found");
        }

        return responseRepository.findById(responseId).map(persistedResponse -> {
            persistedResponse.setText(response.getText());
            persistedResponse.setValue(response.getValue());
            persistedResponse.setInfluenceOnInvoice(response.getInfluenceOnInvoice());
            return responseRepository.save(persistedResponse);
        }).orElseThrow(() -> new ResourceNotFoundException("ResponseId " + responseId + " not found"));
    }


    @Override
    public Page<Response> findPage(final Long questionId, final Integer page, final Integer size) {
        Assert.notNull(page, "Page number is null.");
        Assert.notNull(size, "Page size is null.");
        if(!questionRepository.existsById(questionId)) {
            throw new ResourceNotFoundException("QuestionId " + questionId + " not found");
        }
        return responseRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    @Transactional
    public Response delete(final Long questionId, final Long responseId) {
        Assert.notNull(questionId, "Question id is null.");
        Assert.notNull(responseId, "Response id is null.");
        if(!questionRepository.existsById(questionId)) {
            throw new ResourceNotFoundException("QuestionId " + questionId + " not found");
        }

        return responseRepository.findById(responseId).map(response -> {
            responseRepository.delete(response);
            return response;
        }).orElseThrow(() -> new ResourceNotFoundException("ResponseId " + responseId + " not found"));
    }
}
