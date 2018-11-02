package com.cloudsystemhq.model.dto.request;

import java.io.Serializable;
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
  private String name;
  private String email;
  private String phone;
  private String password;
  private Boolean confirmed;
}
