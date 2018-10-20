package com.cloudsystemhq.model.domain.invoice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Getter
@Setter
@ToString(exclude = "invoice")
public class Support {
   @Id
   private Long id;

   @Enumerated(EnumType.STRING)
   private SupportType supportType;

   @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
   @JoinColumn(name = "subscription_id")
   private Subscription subscription;

   @OneToOne(fetch = FetchType.LAZY, optional = false)
   @MapsId
   @JsonIgnore
   private Invoice invoice;
}
