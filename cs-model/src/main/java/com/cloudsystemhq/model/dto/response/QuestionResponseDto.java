package com.cloudsystemhq.model.dto.response;

import com.cloudsystemhq.model.domain.Answer;
import com.cloudsystemhq.model.domain.AnswerType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class QuestionResponseDto implements Serializable{
    private Long id;
    private String title;
  private AnswerType answerType;
  private Set<Answer> answers = new HashSet<>();
}
