package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.Question;
import com.cloudsystemhq.model.dto.request.QuestionRequestDto;
import com.cloudsystemhq.model.dto.response.QuestionResponseDto;
import com.cloudsystemhq.repository.QuestionRepository;
import com.cloudsystemhq.service.IQuestionService;
import com.cloudsystemhq.service.util.mapping.QuestionMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl
        extends AbstractBaseServiceImpl<Question, QuestionRequestDto, QuestionResponseDto, Long>
        implements IQuestionService{


    public QuestionServiceImpl(
            QuestionRepository repository,
            QuestionMapper mapper) {
        super(repository,mapper);
    }

    @Override
    @Transactional
    public QuestionResponseDto create(final QuestionRequestDto questionRequestDto) {
        Assert.notNull(questionRequestDto, "Question is null.");
        Question question = mapper.convertToEntity(questionRequestDto);
        if (question.getResponses() != null){
            question.getResponses().forEach(response -> {
                response.setQuestion(question);
                question.getResponses().add(response);
                if (response.getInfluenceOnPrice() != null){
                    response.getInfluenceOnPrice()
                            .forEach(influenceOnPrice -> influenceOnPrice.setResponse(response));
                }
            });
        }
        Question savedQuestion = repository.save(question);
        return mapper.convertToDto(savedQuestion);
    }

    @Override
    public Optional<QuestionResponseDto> findOne(final Long id) {
        return super.findOne(id);
    }

    @Override
    public List<QuestionResponseDto> findAll() {
        return super.findAll();
    }

    @Override
    public Page<QuestionResponseDto> findPage(final Integer page, final Integer size) {
        return super.findPage(page,size);
    }

    @Override
    @Transactional
    public Optional<QuestionResponseDto> update(final Long id, final QuestionRequestDto question) {
        return super.update(id, question);
    }

    @Override
    @Transactional
    public Optional<QuestionResponseDto> delete(final Long id) {
        return super.delete(id);
    }

    @Override
    Function<Question, Question> updateEntity(final Question updatedQuestion) {
        return (persistedQuestion) -> {
            persistedQuestion.setTitle(updatedQuestion.getTitle());
            persistedQuestion.setResponseType(updatedQuestion.getResponseType());
            persistedQuestion.getResponses().clear();
            persistedQuestion.getResponses()
                    .addAll(
                            updatedQuestion.getResponses()                             // Without setting a Question
                            .stream()                                                  // 'question' column in Response object
                            .peek(response -> {
                                response.setQuestion(persistedQuestion);
                                if(response.getInfluenceOnPrice() != null) {
                                    response.getInfluenceOnPrice()
                                            .forEach(influenceOnPrice -> influenceOnPrice.setResponse(response));
                                }
                            })                                                          // violates not-null constraint
                            .collect(Collectors.toSet())                               // In bidirectional association we are responsible for handling consistency
                    );
            return persistedQuestion;
        };
    }
}