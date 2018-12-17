package com.cloudsystemhq.model.dto.request;

import com.cloudsystemhq.model.domain.Answer;
import com.cloudsystemhq.model.domain.AnswerType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class QuestionRequestDto implements Serializable {

  @NotBlank
  private String title;
  @NotNull
  private AnswerType answerType;
  private Set<Answer> answers = new HashSet<>();
}
