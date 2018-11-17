package com.cloudsystemhq.model.domain.order;

import com.cloudsystemhq.model.domain.Answer;
import com.cloudsystemhq.model.domain.Customer;
import com.cloudsystemhq.model.domain.invoice.Invoice;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OneToMany;
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
@EqualsAndHashCode(exclude = {"answers", "customer"})
@ToString(exclude = {"answers", "customer"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean isFinished;

    //TODO: think about update order logic
    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "order_answer",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
    private Set<Answer> answers = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<Invoice> invoices = new ArrayList<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private InfrastructureInfo infrastructureInfo;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private SupportInfo supportInfo;

    @ManyToOne
    @JoinColumn(name = "urgency_id")
    private Urgency urgency;
}
