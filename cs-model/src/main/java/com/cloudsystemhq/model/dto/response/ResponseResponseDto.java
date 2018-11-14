package com.cloudsystemhq.model.dto.response;

import com.cloudsystemhq.model.domain.AnswerHandlingType;
import com.cloudsystemhq.model.domain.InfluenceOnPrice;
import com.cloudsystemhq.model.domain.Question;
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
public class ResponseResponseDto implements Serializable {
    private Long id;
    private String text;
    private Question question;
    private Question nextQuestion;
    private Set<InfluenceOnPrice> influenceOnPrice = new HashSet<>();
  private AnswerHandlingType answerHandlingType;
}
