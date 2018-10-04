package com.cloudsystemhq.service;

import com.cloudsystemhq.model.domain.Response;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IResponseService {
    Response create(Long questionId, Response response);
    Response update(Long questionId, Long responseId, Response response);
    List<Response> getResponsesByQuestionId(Long questionId);
    Response findOne(Long responseId);
    Page<Response> findPage(Long questionId, Integer page, Integer size);
    Response delete(Long questionId, Long responseId);
}
