package com.unochapeco.dogssereleps.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "servico")
public class Servico implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "aonde", length = 255)
    private String aonde;

    @Column(name = "descricao", length = 255)
    private String descricao;

    @Column(name = "status", length = 255)
    private String status = "NOVO";
    //NOVO, EM ANDAMENTO, FINALIZADO, CANCELADO

    @NotNull
    @Column(name = "data")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date data;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private TipoServico tipoServico;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User user;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Animal animal;

    @Transient
    private double distancia;
}
