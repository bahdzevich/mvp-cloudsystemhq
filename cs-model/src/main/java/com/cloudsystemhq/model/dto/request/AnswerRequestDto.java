package com.cloudsystemhq.model.dto.request;

import com.cloudsystemhq.model.domain.AnswerHandlingType;
import com.cloudsystemhq.model.domain.InfluenceOnPrice;
import com.cloudsystemhq.model.domain.Question;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
public class AnswerRequestDto implements Serializable {

  @NotBlank(message = "Text cannot be blank")
  @Size(max = 200, message = "Answer must have less then 200 characters")
  private String text;
  @NotNull(message = "Question cannot be null")
  private Question question;
  private Question nextQuestion;
  private Set<InfluenceOnPrice> influenceOnPrice = new HashSet<>();
  @NotNull(message = "AnswerHandlingType cannot be null")
  private AnswerHandlingType answerHandlingType;
}
