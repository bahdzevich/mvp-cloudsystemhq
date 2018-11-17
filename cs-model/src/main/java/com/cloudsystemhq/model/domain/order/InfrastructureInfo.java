package com.cloudsystemhq.model.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private List<ServerInstance> serverInstances = new ArrayList<>();

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