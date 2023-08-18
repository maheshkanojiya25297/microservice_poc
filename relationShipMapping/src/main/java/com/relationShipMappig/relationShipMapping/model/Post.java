package com.relationShipMappig.relationShipMapping.model;

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
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="post_title")
    private String title;

    @Column(name = "post_description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pc_id" , referencedColumnName = "id")
    List<Comment> comments = new ArrayList<>();
}
