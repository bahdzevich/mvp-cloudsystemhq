package com.cloudsystemhq.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ToString(exclude = "question")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    @JsonIgnore
    private Question question;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="next_question_id")
    private Question nextQuestion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "answer", orphanRemoval = true)
    private Set<InfluenceOnPrice> influenceOnPrice = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private AnswerHandlingType answerHandlingType;
}