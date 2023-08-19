package com.relationShipMappig.relationShipMapping.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post")
public class Post implements Serializable {
    private static final Long serialVersionUID = 1L;

  /*  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long post_id;

    @Column(name="post_title")
    private String post_title;

    @Column(name = "post_description")
    private String post_description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pc_id" , referencedColumnName = "id")
    List<Comment> comments = new ArrayList<>();*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private long postSqId;

    @Column(name = "post_pins_id", unique = true)
    private String postPinsId;

    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "post_description")
    private String postDescription;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_pins_id", referencedColumnName = "post_pins_id")
    List<Comment> comments = new ArrayList<>();
}
