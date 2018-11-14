package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.Answer;
import com.cloudsystemhq.model.dto.request.ResponseRequestDto;
import com.cloudsystemhq.model.dto.response.ResponseResponseDto;
import com.cloudsystemhq.repository.QuestionRepository;
import com.cloudsystemhq.repository.ResponseRepository;
import com.cloudsystemhq.service.IResponseService;
import com.cloudsystemhq.service.util.mapping.ResponseMapper;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResponseServiceImpl
    extends AbstractBaseServiceImpl<Answer, ResponseRequestDto, ResponseResponseDto, Long>
        implements IResponseService{

    private final static Logger LOGGER = LoggerFactory.getLogger(ResponseServiceImpl.class.getName());
    private final ResponseRepository responseRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public ResponseServiceImpl(
            ResponseRepository repository,
            ResponseMapper mapper,
            QuestionRepository questionRepository){
        super(repository, mapper);
        this.responseRepository = repository;
        this.questionRepository = questionRepository;
    }

    @Override
    public List<ResponseResponseDto> getResponsesByQuestionId(final Long questionId) {
        if (!questionRepository.existsById(questionId)){
            LOGGER.warn("There is no question with id=" + questionId);
        }
        return responseRepository.findResponsesByQuestionId(questionId)
                .stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<ResponseResponseDto> create(final Long questionId, final ResponseRequestDto responseRequestDto) {
        if (!questionRepository.existsById(questionId)){
            LOGGER.warn("There is no question with id=" + questionId);
        }
      Answer answer = mapper.convertToEntity(responseRequestDto);
        return questionRepository.findById(questionId).map(question -> {
          if (answer.getInfluenceOnPrice() != null) {
            answer.getInfluenceOnPrice()
                .forEach(influenceOnPrice -> influenceOnPrice.setAnswer(answer));
            }
          question.getAnswers().add(answer);
          answer.setQuestion(question);
            questionRepository.save(question);
          return mapper
              .convertToDto(answer); // id == null, because returned object not persisted yet
        });
    }

    public Optional<ResponseResponseDto> findOne(final Long responseId) {
        return super.findOne(responseId);
    }

    @Transactional
    public Optional<ResponseResponseDto> update(final Long questionId, final Long responseId,
                                                final ResponseRequestDto responseRequestDto) {
        if (!questionRepository.existsById(questionId)){
            LOGGER.warn("There is no question with id=" + questionId);
        }
        return super.update(responseId, responseRequestDto);
    }

    @Transactional
    public Optional<ResponseResponseDto> delete(final Long responseId) {
        return super.delete(responseId);
    }

    @Override
    Function<Answer, Answer> updateEntity(Answer answer) {
        return persistedResponse -> {
          persistedResponse.setText(answer.getText());
          persistedResponse.setInfluenceOnPrice(answer.getInfluenceOnPrice());
          persistedResponse.setNextQuestion(answer.getNextQuestion());
          persistedResponse.setAnswerHandlingType(answer.getAnswerHandlingType());
            return responseRepository.save(persistedResponse);
        };
    }
}
