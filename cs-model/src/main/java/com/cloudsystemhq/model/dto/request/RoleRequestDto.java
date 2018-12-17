package com.cloudsystemhq.model.dto.request;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
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
public class RoleRequestDto implements Serializable {

  @NotBlank
  private String name;
}
