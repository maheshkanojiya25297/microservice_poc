package com.implemented.user.service.entities;


import lombok.*;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "micro_users")
public class User {
    @Id
    @Column(name = "ID")
    private String userId;

    @Column(name = "NAME", length = 15)
    private String name;

    @Column(name = "ABOUT")
    private String about;

    @Column(name = "EMAIL")
    private String email;

    @Transient
    private List<Rating> ratings = new ArrayList<>();
}
