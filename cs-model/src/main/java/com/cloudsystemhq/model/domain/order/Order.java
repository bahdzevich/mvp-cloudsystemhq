package com.cloudsystemhq.model.domain.order;

import com.cloudsystemhq.model.domain.Customer;
import com.cloudsystemhq.model.domain.Response;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order_")
@Getter
@Setter
@EqualsAndHashCode(exclude = "responses")
@ToString(exclude = {"responses"})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    boolean isFinished;

    //TODO: think about update order logic
    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "order_response",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
    private Set<Response> responses = new HashSet<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private InfrastructureInfo infrastructureInfo;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private SupportInfo supportInfo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "urgency_id")
    private Urgency urgency;
}
