package com.cloudsystemhq.model.domain.invoice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(exclude = "invoice")
public class Infrastructure {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    private ServerList serverList;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JsonIgnore
    private Invoice invoice;
}