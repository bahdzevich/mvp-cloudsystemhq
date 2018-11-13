package com.cloudsystemhq.service;

import com.cloudsystemhq.model.dto.request.ResponseRequestDto;
import com.cloudsystemhq.model.dto.response.ResponseResponseDto;

import java.util.List;
import java.util.Optional;

public interface IResponseService extends
        IBaseService<ResponseRequestDto, ResponseResponseDto, Long> {
    List<ResponseResponseDto> getResponsesByQuestionId(final Long questionId);
    Optional<ResponseResponseDto> create(final Long questionId, final ResponseRequestDto responseRequestDto);
    Optional<ResponseResponseDto> update(final Long questionId, final Long responseId,
                                         final ResponseRequestDto responseRequestDto);
}
