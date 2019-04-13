package com.unochapeco.dogssereleps.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Table(name = "users")
public class User {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 80)
    private String name;

    @Column(name = "email", unique = true, length = 255)
    private String email;

    @Column(name = "zipcode", length = 80)
    private String zipcode;

    @Column(name = "city", length = 80)
    private String city;

    @Column(name = "cellphone", length = 20)
    private String cellphone;

    @Column(name = "password", length = 20)
    private String password;
}