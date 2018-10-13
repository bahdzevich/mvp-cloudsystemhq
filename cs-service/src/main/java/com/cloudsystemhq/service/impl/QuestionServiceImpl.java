package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.Question;
import com.cloudsystemhq.repository.QuestionRepository;
import com.cloudsystemhq.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements IQuestionService{

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    @Override
    public Question create(final Question question) {
        Assert.notNull(question, "Question is null.");
        if (question.getResponses() != null){
            question.getResponses().forEach(response -> {
                response.setQuestion(question);
                question.getResponses().add(response);
                if (response.getInfluenceOnInvoice() != null){
                    response.getInfluenceOnInvoice().setResponse(response);
                }
            });
        }
        return questionRepository.save(question);
    }

    @Override
    public Optional<Question> findOne(final Long id) {
        Assert.notNull(id, "Question id is null.");
        return questionRepository.findById(id);
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Page<Question> findPage(final Integer page, final Integer size) {
        Assert.notNull(page, "Page number is null.");
        Assert.notNull(size, "Size number is null.");
        return questionRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    @Transactional
    public Optional<Question> update(final Long id, final Question question) {
        Assert.notNull(id, "Question id is null.");
        Assert.notNull(question, "Question is null.");
        return questionRepository.findById(id).map(updateQuestion(question));
    }

    @Override
    @Transactional
    public Optional<Question> delete(Long id) {
        Assert.notNull(id, "Question id is null.");
        Optional<Question> questionOptional = questionRepository.findById(id);
        questionOptional.ifPresent(questionRepository::delete);
        return questionOptional;
    }

    private Function<Question, Question> updateQuestion(final Question updatedQuestion) {
        return (persistedQuestion) -> {
            persistedQuestion.setTitle(updatedQuestion.getTitle());
            persistedQuestion.setType(updatedQuestion.getType());
            persistedQuestion.getResponses().clear();
            persistedQuestion.getResponses()
                    .addAll(
                            updatedQuestion.getResponses()                             // Without setting a Question
                            .stream()                                                  // 'question' column in Response object
                            .peek(response -> {
                                response.setQuestion(persistedQuestion);
                                if(response.getInfluenceOnInvoice() != null) {
                                    response.getInfluenceOnInvoice().setResponse(response);
                                }
                            }) // violates not-null constraint
                            .collect(Collectors.toSet())                               // In bidirectional association we are responsible for handling consistency
                    );
            return questionRepository.save(persistedQuestion);
        };
    }
}