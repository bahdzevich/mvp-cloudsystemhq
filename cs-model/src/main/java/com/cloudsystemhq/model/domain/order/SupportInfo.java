package com.cloudsystemhq.model.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class SupportInfo {
   @Id
   private Long id;

   @ManyToOne
   @JoinColumn(name = "support_type_id")
   private SupportType supportType;

   @ManyToOne
   @JoinColumn(name = "subscription_id")
   private Subscription subscription;

   @OneToOne(fetch = FetchType.LAZY)
   @MapsId
   @JsonIgnore
   private Order order;

//   private boolean isActiveSubscription;
//   private LocalDate startSubscriptionDate;
}
