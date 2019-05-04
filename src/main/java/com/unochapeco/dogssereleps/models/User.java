package com.unochapeco.dogssereleps.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", length = 80)
    private String nome;

    @NotNull
    @Column(name = "email", unique = true, length = 255)
    private String email;

    @NotNull
    @Column(name = "cep", length = 80)
    private String cep;

    @NotNull
    @Column(name = "cidade", length = 80)
    private String cidade;

    @NotNull
    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "rua", length = 20)
    private String rua;

    @NotNull
    @Column(name = "senha", length = 20)
    private String senha;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;
}
