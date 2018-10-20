package com.cloudsystemhq.model.domain;

import com.cloudsystemhq.model.domain.invoice.Invoice;
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
@EqualsAndHashCode(exclude = "roles")
@ToString(exclude = {"roles","invoices"})
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String email;
  private String phone;
  private String password;
  private Boolean confirmed;

  @ManyToMany(
      fetch = FetchType.LAZY,
      cascade = CascadeType.REMOVE)
  @JoinTable(
      name = "CUSTOMER_ROLE",
      joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private Set<Role> roles = new HashSet<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", orphanRemoval = true)
  private Set<Invoice> invoices = new HashSet<>();
}
