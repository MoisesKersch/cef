package com.unochapeco.dogssereleps.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "animal")
public class Animal {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", length = 80)
    private String nome;

    @NotNull
    @Column(name = "tipo", length = 255)
    private String tipo;

    @NotNull
    @Column(name = "raca", length = 80)
    private String raca;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User user;
}
