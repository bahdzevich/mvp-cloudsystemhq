package com.cloudsystemhq.model.domain.invoice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "invoice")
public class Infrastructure {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private ServerProvider serverProvider;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="infrastructure_id")
    private List<ServerInstance> serverInstances = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JsonIgnore
    private Invoice invoice;
}