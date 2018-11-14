package com.cloudsystemhq.service.util.mapping;

import com.cloudsystemhq.model.domain.Answer;
import com.cloudsystemhq.model.dto.request.AnswerRequestDto;
import com.cloudsystemhq.model.dto.response.AnswerResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper
    extends EntityMapper<Answer, AnswerRequestDto, AnswerResponseDto> {
}
