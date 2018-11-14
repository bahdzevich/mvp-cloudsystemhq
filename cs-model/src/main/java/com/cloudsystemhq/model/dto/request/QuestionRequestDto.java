package com.cloudsystemhq.model.dto.request;

import com.cloudsystemhq.model.domain.Answer;
import com.cloudsystemhq.model.domain.AnswerType;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class QuestionRequestDto implements Serializable{
    private String title;
    private AnswerType answerType;
    private Set<Answer> answers = new HashSet<>();
}
