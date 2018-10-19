package com.cloudsystemhq.model.domain.invoice;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "invoice_parameters")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="parameter_type",
        discriminatorType = DiscriminatorType.INTEGER)
@Getter
@Setter
public abstract class InvoiceParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parameter_id")
    private Long parameterId;

    @Enumerated(EnumType.STRING)
    private InvoiceSection invoiceSection;

    private Integer importance;
}
