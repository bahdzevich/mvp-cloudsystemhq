package com.cloudsystemhq.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Question{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;

    @Enumerated(EnumType.STRING)
    private AnswerType answerType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question", orphanRemoval = true)
    private Set<Answer> answers = new HashSet<>();
}
