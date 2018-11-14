package com.cloudsystemhq.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString(exclude = "answer")
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class InfluenceOnPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer quantity;
    private Double coefficient;
    private BigDecimal pricePerUnit;

    private BigDecimal extraPriceForSection;

    @Enumerated(EnumType.STRING)
    private InvoiceSection invoiceSection;

    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id", nullable = false)
    @JsonIgnore
    private Answer answer;
}