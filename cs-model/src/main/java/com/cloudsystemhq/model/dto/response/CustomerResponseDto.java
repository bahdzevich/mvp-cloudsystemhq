package com.cloudsystemhq.model.dto.response;

import com.cloudsystemhq.model.domain.Role;
import com.cloudsystemhq.model.domain.invoice.Invoice;
import com.cloudsystemhq.model.domain.order.Order;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CustomerResponseDto implements Serializable {
  private Long id;
  private String name;
  private String email;
  private String phone;
  private String password;
  private Boolean confirmed;
  private Double discount;
  private Set<Role> roles = new HashSet<>();
  private Set<Invoice> invoices = new HashSet<>();
  private Set<Order> orders = new HashSet<>();
}
