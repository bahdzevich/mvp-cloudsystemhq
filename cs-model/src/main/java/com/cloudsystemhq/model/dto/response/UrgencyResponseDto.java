package com.cloudsystemhq.model.dto.response;

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
public class UrgencyResponseDto implements Serializable {

  private Long id;
  private String type;
  private Integer hours;
}
