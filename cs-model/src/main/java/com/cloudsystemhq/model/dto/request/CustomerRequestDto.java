package com.cloudsystemhq.model.dto.request;

import com.cloudsystemhq.model.domain.Role;
import com.cloudsystemhq.model.domain.order.Order;
import com.cloudsystemhq.model.dto.validation.PasswordConstraint;
import com.cloudsystemhq.model.dto.validation.PhoneConstraint;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
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
public class CustomerRequestDto implements Serializable {

  @NotBlank(message = "Name cannot be blank")
  @Size(max = 150)
  private String name;
  @Pattern(regexp = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
      message = "Please provide a valid email address")
  private String email;
  @PhoneConstraint
  private String phone;
  @PasswordConstraint
  private String password;
  private boolean confirmed;
  @PositiveOrZero
  private Double discount;
  @NotEmpty
  private Set<Role> roles = new HashSet<>();
  private Set<Order> orders = new HashSet<>();
}
