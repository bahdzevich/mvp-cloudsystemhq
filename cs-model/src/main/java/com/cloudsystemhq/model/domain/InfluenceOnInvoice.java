package com.cloudsystemhq.model.domain;

import com.cloudsystemhq.model.domain.invoice.InvoiceParameter;
import com.cloudsystemhq.model.domain.invoice.PriceDependency;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "response")
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "response_id",
        scope = InfluenceOnInvoice.class
)
public class InfluenceOnInvoice {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JsonIgnore
    private Response response;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="influence_id", referencedColumnName="response_id", nullable = false)
    private List<InvoiceParameter> invoiceParameters = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="influence_id", referencedColumnName="response_id", nullable = false)
    private List<PriceDependency> priceDependencies = new ArrayList<>();
}