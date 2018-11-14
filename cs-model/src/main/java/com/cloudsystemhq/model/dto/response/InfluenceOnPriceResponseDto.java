package com.cloudsystemhq.model.dto.response;

import com.cloudsystemhq.model.domain.Answer;
import com.cloudsystemhq.model.domain.InvoiceSection;
import com.cloudsystemhq.model.domain.OperationType;
import java.io.Serializable;
import java.math.BigDecimal;
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
public class InfluenceOnPriceResponseDto implements Serializable {
    private Long id;
    private Integer quantity;
    private Double coefficient;
    private BigDecimal pricePerUnit;
    private BigDecimal extraPriceForSection;
    private InvoiceSection invoiceSection;
    private OperationType operationType;
  private Answer answer;
}
