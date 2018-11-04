package com.cloudsystemhq.model.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = "order")
    public class InfrastructureInfo {
    @Id
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "infrastructure_provider_id")
    private InfrastructureProvider infrastructureProvider;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="infrastructure_id")
    private Set<ServerInstance> serverInstances = new HashSet<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    //   private boolean isActiveSubscription;
    //   private LocalDate startSubscriptionDate;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonIgnore
    private Order order;
}