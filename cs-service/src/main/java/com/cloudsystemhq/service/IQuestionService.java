package com.cloudsystemhq.service;

import com.cloudsystemhq.model.dto.request.QuestionRequestDto;
import com.cloudsystemhq.model.dto.response.QuestionResponseDto;

public interface IQuestionService extends
        IBaseService<QuestionRequestDto, QuestionResponseDto, Long>{
}
