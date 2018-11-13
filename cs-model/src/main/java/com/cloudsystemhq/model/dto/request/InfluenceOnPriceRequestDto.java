package com.cloudsystemhq.model.dto.request;

import com.cloudsystemhq.model.domain.InvoiceSection;
import com.cloudsystemhq.model.domain.OperationType;
import com.cloudsystemhq.model.domain.Response;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class InfluenceOnPriceRequestDto implements Serializable {
    private Integer quantity;
    private Double coefficient;
    private BigDecimal pricePerUnit;
    private BigDecimal extraPriceForSection;
    private InvoiceSection invoiceSection;
    private OperationType operationType;
    private Response response;
}
