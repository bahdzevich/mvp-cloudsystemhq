package com.cloudsystemhq.model.dto.response;

import com.cloudsystemhq.model.domain.Role;
import com.cloudsystemhq.model.domain.order.Order;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
  private Set<Order> orders = new HashSet<>();
}
