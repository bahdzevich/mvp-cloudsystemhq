package com.cloudsystemhq.model.domain.invoice;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PriceDependency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private InvoiceSection invoiceSection;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    private Double value;
}
