package com.implementation.multiDb.connectivity.multiple.Db.Connectivity.mysql.model.userInfo;

import jakarta.persistence.*;
import lombok.*;
//import org.hibernate.annotations.Proxy;

import java.sql.Blob;
import java.util.Date;

@Entity
@Table(schema = "myhiber" , name = "user_information")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Proxy
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_Id")
    private int id;

    @Column(name = "added_date")
    private Date date;

    @Column(name = "CITY")
    private String city;

    @Column(name = "image")
    private Blob image;

    @Column(name = "is_open")
    private byte status;

    @Column(name = "STREET")
    private String street;
}
