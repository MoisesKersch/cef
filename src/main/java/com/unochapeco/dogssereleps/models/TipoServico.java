package com.unochapeco.dogssereleps.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "tipo_servico")
public class TipoServico {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", length = 255)
    private String nome;

    @Column(name = "valor", length = 255)
    private BigDecimal valor;

    @Column(name = "descricao", length = 255)
    private String descricao;
}
