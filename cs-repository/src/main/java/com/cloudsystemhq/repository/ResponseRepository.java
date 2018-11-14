package com.cloudsystemhq.repository;

import com.cloudsystemhq.model.domain.Answer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<Answer, Long> {

  List<Answer> findResponsesByQuestionId(Long questionId);
}
