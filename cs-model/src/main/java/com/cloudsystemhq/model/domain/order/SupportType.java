package com.cloudsystemhq.model.domain.order;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SupportType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true ,nullable=false)
    private String type;
    private Boolean dayAndNight; //Looks shitty
    private Boolean onlyWorkingHours;
    private Boolean chat;
    private Boolean email;
    private Boolean phone;
}
