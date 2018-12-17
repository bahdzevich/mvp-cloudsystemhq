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
public class SupportTypeRequestDto implements Serializable {

  @NotBlank
  private String type;
  private boolean roundTheClock;
  private boolean onlyWorkingHours;
  private boolean chat;
  private boolean email;
  private boolean phone;
}
