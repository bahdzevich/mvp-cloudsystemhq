package com.cloudsystemhq.service;

import com.cloudsystemhq.model.domain.Response;

import java.util.List;
import java.util.Optional;

public interface IResponseService {
    Optional<Response> create(Long questionId, Response response);
    Optional<Response> update(Long questionId, Long responseId, Response response);
    Optional<List<Response>> getResponsesByQuestionId(Long questionId);
    Optional<Response> findOne(Long questionId, Long responseId);
    Optional<Response> delete(Long questionId, Long responseId);
}
