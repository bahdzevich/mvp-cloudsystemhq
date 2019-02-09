package com.cloudsystemhq.model.dto.response;

import java.io.Serializable;
import java.util.List;

import com.cloudsystemhq.model.domain.Question;
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
public class QuestionnairePageDto implements Serializable {
    private List<Question> questions;
    private boolean lastPage;
}
