package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.Answer;
import com.cloudsystemhq.model.dto.request.AnswerRequestDto;
import com.cloudsystemhq.model.dto.response.AnswerResponseDto;
import com.cloudsystemhq.repository.AnswerRepository;
import com.cloudsystemhq.repository.QuestionRepository;
import com.cloudsystemhq.service.IAnswerService;
import com.cloudsystemhq.service.util.mapping.AnswerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl
    extends AbstractBaseServiceImpl<Answer, AnswerRequestDto, AnswerResponseDto, Long>
        implements IAnswerService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AnswerServiceImpl.class.getName());
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public AnswerServiceImpl(
            AnswerRepository repository,
            AnswerMapper mapper,
            QuestionRepository questionRepository){
        super(repository, mapper);
        this.answerRepository = repository;
        this.questionRepository = questionRepository;
    }

    @Override
    public List<AnswerResponseDto> getAnswersByQuestionId(final Long questionId) {
        if (!questionRepository.existsById(questionId)){
            LOGGER.warn("There is no question with id=" + questionId);
        }
        return answerRepository.findAnswersByQuestionId(questionId)
                .stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<AnswerResponseDto> create(final Long questionId, final AnswerRequestDto answerRequestDto) {
        if (!questionRepository.existsById(questionId)){
            LOGGER.warn("There is no question with id=" + questionId);
        }
      Answer answer = mapper.convertToEntity(answerRequestDto);
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

    @Transactional
    public Optional<AnswerResponseDto> update(final Long questionId, final Long answerId,
                                              final AnswerRequestDto answerRequestDto) {
        if (!questionRepository.existsById(questionId)){
            LOGGER.warn("There is no question with id=" + questionId);
        }
        return super.update(answerId, answerRequestDto);
    }

    @Override
    Function<Answer, Answer> updateEntity(Answer answer) {
        return persistedAnswer -> {
            persistedAnswer.setText(answer.getText());
            persistedAnswer.getInfluenceOnPrice().clear();
            persistedAnswer.getInfluenceOnPrice()
                    .addAll(
                            answer.getInfluenceOnPrice()
                                    .stream()
                                    .peek(influence -> influence.setAnswer(persistedAnswer))
                                    .collect(Collectors.toSet())
            );
            persistedAnswer.setNextQuestion(answer.getNextQuestion());
            persistedAnswer.setAnswerHandlingType(answer.getAnswerHandlingType());
            return repository.save(persistedAnswer);
        };
    }
}
