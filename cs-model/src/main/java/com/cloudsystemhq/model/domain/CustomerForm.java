package com.cloudsystemhq.model.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CustomerForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "customer_form_response",
            joinColumns = @JoinColumn(name = "customer_form_id", referencedColumnName = "id"))
    private Set<Response> responses = new HashSet<>();
}
