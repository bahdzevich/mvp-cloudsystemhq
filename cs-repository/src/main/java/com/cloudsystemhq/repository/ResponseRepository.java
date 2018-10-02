package com.cloudsystemhq.repository;

import com.cloudsystemhq.model.domain.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long>{
    Set<Response> findResponsesByQuestionId(Long questionId);
}
