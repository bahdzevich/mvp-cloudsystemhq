package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.Question;
import com.cloudsystemhq.model.dto.request.QuestionRequestDto;
import com.cloudsystemhq.model.dto.response.QuestionResponseDto;
import com.cloudsystemhq.repository.QuestionRepository;
import com.cloudsystemhq.service.IQuestionService;
import com.cloudsystemhq.service.util.mapping.QuestionMapper;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class QuestionServiceImpl
    extends AbstractBaseServiceImpl<Question, QuestionRequestDto, QuestionResponseDto, Long>
    implements IQuestionService {


  public QuestionServiceImpl(
      QuestionRepository repository,
      QuestionMapper mapper) {
    super(repository, mapper);
  }

  @Override
  @Transactional
  public QuestionResponseDto create(final QuestionRequestDto questionRequestDto) {
    Assert.notNull(questionRequestDto, "Question is null.");
    Question question = mapper.convertToEntity(questionRequestDto);
    if (question.getAnswers() != null) {
      question.getAnswers().forEach(response -> {
        response.setQuestion(question);
        question.getAnswers().add(response);
        if (response.getInfluenceOnPrice() != null) {
          response.getInfluenceOnPrice()
              .forEach(influenceOnPrice -> influenceOnPrice.setAnswer(response));
        }
      });
    }
    Question savedQuestion = repository.save(question);
    return mapper.convertToDto(savedQuestion);
  }

  @Override
  @Transactional
  public Optional<QuestionResponseDto> update(final Long id, final QuestionRequestDto question) {
    return super.update(id, question);
  }

  @Override
  Function<Question, Question> updateEntity(final Question updatedQuestion) {
    return (persistedQuestion) -> {
      persistedQuestion.setTitle(updatedQuestion.getTitle());
      persistedQuestion.setAnswerType(updatedQuestion.getAnswerType());
      persistedQuestion.getAnswers().clear();
      persistedQuestion.getAnswers()
          .addAll(
              updatedQuestion
                  .getAnswers()                             // Without setting a Question
                  .stream()                                                  // 'question' column in Answer object
                  .peek(answer -> {
                    answer.setQuestion(persistedQuestion);
                    if (answer.getInfluenceOnPrice() != null) {
                      answer.getInfluenceOnPrice()
                          .forEach(influenceOnPrice -> influenceOnPrice
                              .setAnswer(answer));
                    }
                  })                                                          // violates not-null constraint
                  .collect(Collectors.toSet())
              // In bidirectional association we are responsible for handling consistency
          );
      return repository.save(persistedQuestion);
    };
  }
}