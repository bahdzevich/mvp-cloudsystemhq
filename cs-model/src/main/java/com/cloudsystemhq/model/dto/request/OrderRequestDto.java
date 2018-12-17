package com.cloudsystemhq.model.dto.request;

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
import javax.validation.constraints.NotNull;
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
public class OrderRequestDto implements Serializable {

  private boolean finished;
  @NotNull
  private OrderType orderType;
  private Customer customer;
  private Set<Invoice> invoices = new HashSet<>();
  private Set<Answer> answers = new HashSet<>();
  private InfrastructureInfo infrastructureInfo;
  private SupportInfo supportInfo;
  private Urgency urgency;
}
