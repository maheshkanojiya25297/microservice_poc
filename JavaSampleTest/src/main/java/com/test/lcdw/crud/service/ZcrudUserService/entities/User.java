package com.test.lcdw.crud.service.ZcrudUserService.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Test_User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private Integer Id;

    @Column(name = "Name")
    private String name;

    @Column(name = "City")
    private String city;

    @Column(name = "Gender")
    private String gender;

}
