package com.cloudsystemhq.model.domain.invoice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("1")
@ToString
public class ServerListParameter extends InvoiceParameter {

    @Enumerated(EnumType.STRING)
    private ServerProvider serverProvider;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="server_list_parameter_id", referencedColumnName="parameter_id", nullable = false)
    private List<ServerInstanceParameter> serverInstanceParameters = new ArrayList<>();
}
