package com.cloudsystemhq.repository;

import com.cloudsystemhq.model.domain.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long>{
    Optional<List<Response>> findResponsesByQuestionId(Long questionId);
}
