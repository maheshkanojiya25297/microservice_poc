package com.jwt.token.generation.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String firstname;

    private String lastname;

    private String street;

    private String city;
}
