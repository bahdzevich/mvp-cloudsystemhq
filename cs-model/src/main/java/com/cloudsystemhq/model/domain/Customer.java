package com.cloudsystemhq.model.domain;

import com.cloudsystemhq.model.domain.order.Order;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = "roles")
@ToString(exclude = {"roles"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String email;
  private String phone;
  private String password;
  private Boolean confirmed;
  private Double discount;

  @ManyToMany(
      fetch = FetchType.LAZY)
  @JoinTable(
      name = "CUSTOMER_ROLE",
      joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private Set<Role> roles = new HashSet<>();

  @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "customer")
  private List<Order> orders = new ArrayList<>();
}
