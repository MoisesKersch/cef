package com.unochapeco.dogssereleps.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Endereco {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estado_id")
    private String estadoId;

    @Column(name = "cidade_id")
    private String cidadeId;

    @Column(name = "estado_codigo")
    private String estadoCodigo;

    @Column(name = "estado_nome")
    private String estadoNome;

    @Column(name = "cidade_nome")
    private String cidadeNome;

}
