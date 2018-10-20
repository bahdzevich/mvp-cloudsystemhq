package com.cloudsystemhq.model.domain.invoice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString(exclude = "invoice")
public class InfrastructureDeployment {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private Urgency urgency; // need opportunity to customize

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JsonIgnore
    private Invoice invoice;

    private LocalDateTime deploymentStartTime;
}