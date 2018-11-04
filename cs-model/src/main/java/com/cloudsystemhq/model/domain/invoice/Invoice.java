package com.cloudsystemhq.model.domain.invoice;

import com.cloudsystemhq.model.domain.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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
    private InfrastructurePrice infrastructurePrice;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="deployment_price_id")
    private DevopsPrice devopsPrice;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="support_price_id")
    private SupportPrice supportPrice;
}