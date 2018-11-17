package com.cloudsystemhq.model.dto.response;

import com.cloudsystemhq.model.domain.Answer;
import com.cloudsystemhq.model.domain.Customer;
import com.cloudsystemhq.model.domain.invoice.Invoice;
import com.cloudsystemhq.model.domain.order.InfrastructureInfo;
import com.cloudsystemhq.model.domain.order.OrderType;
import com.cloudsystemhq.model.domain.order.SupportInfo;
import com.cloudsystemhq.model.domain.order.Urgency;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
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
public class OrderResponseDto implements Serializable {

  private Long id;
  private Boolean isFinished;
  private OrderType orderType;
  private Customer customer;
  private Set<Answer> answers = new HashSet<>();
  private Set<Invoice> invoices = new HashSet<>();
  private InfrastructureInfo infrastructureInfo;
  private SupportInfo supportInfo;
  private Urgency urgency;
}
