package com.relationShipMappig.relationShipMapping.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "auther")
public class Auther {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@JsonIgnore
    private long id;

    @Column(name = "auther_name")
    private String autherName;

    @Column(name = "auther_location")
    private String autherLocation;

    @OneToMany
    @JoinColumn(name = "details_id", referencedColumnName = "id")
    List<AutherDetails> detailsList = new ArrayList<>();
}
