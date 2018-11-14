package com.cloudsystemhq.service;

import com.cloudsystemhq.model.dto.request.AnswerRequestDto;
import com.cloudsystemhq.model.dto.response.AnswerResponseDto;

import java.util.List;
import java.util.Optional;

public interface IAnswerService extends
        IBaseService<AnswerRequestDto, AnswerResponseDto, Long> {
    List<AnswerResponseDto> getAnswersByQuestionId(final Long questionId);
    Optional<AnswerResponseDto> create(final Long questionId, final AnswerRequestDto answerRequestDto);
    Optional<AnswerResponseDto> update(final Long questionId, final Long answerId,
                                       final AnswerRequestDto answerRequestDto);
}
