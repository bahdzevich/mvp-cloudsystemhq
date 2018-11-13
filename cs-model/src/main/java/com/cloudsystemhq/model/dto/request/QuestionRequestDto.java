package com.cloudsystemhq.model.dto.request;

import com.cloudsystemhq.model.domain.Response;
import com.cloudsystemhq.model.domain.ResponseType;
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
    private ResponseType responseType;
    private Set<Response> responses = new HashSet<>();
}
