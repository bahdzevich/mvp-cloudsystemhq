package com.cloudsystemhq.model.dto.request;

import com.cloudsystemhq.model.domain.Answer;
import com.cloudsystemhq.model.domain.InvoiceSection;
import com.cloudsystemhq.model.domain.OperationType;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.PositiveOrZero;
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
public class InfluenceOnPriceRequestDto implements Serializable {

  @PositiveOrZero
  private Integer quantity;
  @PositiveOrZero
  private Double coefficient;
  @PositiveOrZero
  private BigDecimal pricePerUnit;
  @PositiveOrZero
  private BigDecimal extraPriceForSection;
    private InvoiceSection invoiceSection;
    private OperationType operationType;
    private Answer answer;
}
