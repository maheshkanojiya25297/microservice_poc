package com.example.keycloak.springbootkeyclock.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    private String name;

    private String department;
}
