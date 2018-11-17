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
public class SupportTypeRequestDto implements Serializable {

  private String type;
  private Boolean roundTheClock;
  private Boolean onlyWorkingHours;
  private Boolean chat;
  private Boolean email;
  private Boolean phone;
}
