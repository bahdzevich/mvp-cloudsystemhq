package com.cloudsystemhq.model.domain.order;

import com.cloudsystemhq.model.domain.Answer;
import com.cloudsystemhq.model.domain.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "order_")
@Getter
@Setter
@EqualsAndHashCode(exclude = "answers")
@ToString(exclude = {"answers"})
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
    private Set<Answer> answers = new HashSet<>();

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
