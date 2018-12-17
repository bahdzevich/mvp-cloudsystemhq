package com.cloudsystemhq.model.dto.request;

import java.io.Serializable;
import javax.validation.constraints.Positive;
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
public class SubscriptionRequestDto implements Serializable {

  @Positive
  private int numberOfMonth;
}
