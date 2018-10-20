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
@DiscriminatorValue("2")
@ToString
public class SubscriptionParameter extends InvoiceParameter {

    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;
}
