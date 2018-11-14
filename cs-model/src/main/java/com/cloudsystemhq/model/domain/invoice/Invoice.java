package com.cloudsystemhq.model.domain.invoice;

import com.cloudsystemhq.model.domain.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="infrastructure_price_id")
    private InfrastructurePriceDescription infrastructurePriceDescription;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="deployment_price_id")
    private DevopsPriceDescription devopsPriceDescription;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="support_price_id")
    private SupportPriceDescription supportPriceDescription;
}