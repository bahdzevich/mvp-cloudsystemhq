package com.cloudsystemhq.service.util.mapping;

import com.cloudsystemhq.model.domain.Question;
import com.cloudsystemhq.model.dto.request.QuestionRequestDto;
import com.cloudsystemhq.model.dto.response.QuestionResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper
        extends EntityMapper<Question, QuestionRequestDto, QuestionResponseDto>{
}
