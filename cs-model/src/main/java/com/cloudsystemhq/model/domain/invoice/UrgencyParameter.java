package com.cloudsystemhq.model.domain.invoice;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
@DiscriminatorValue("4")
@ToString
public class UrgencyParameter extends InvoiceParameter {

    @Enumerated(EnumType.STRING)
    private Urgency urgency;
}
