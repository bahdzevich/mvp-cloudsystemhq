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
@DiscriminatorValue("5")
@ToString
public class SupportType extends InvoiceParameter{

    @Enumerated(EnumType.STRING)
    private Value supportTypeValue;

    public enum Value {
        WITHOUT, BASIC, ADVANCED, PREMIUM
    }
}

