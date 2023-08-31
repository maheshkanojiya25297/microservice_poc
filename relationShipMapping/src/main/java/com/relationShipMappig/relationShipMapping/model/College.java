package com.relationShipMappig.relationShipMapping.model;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Data
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "college")
public class College {

    @Id
    private long id;

    @Column(name = "college_location")
    private String collegeLocation;

    @Column(name = "college_name")
    private String collegeName;

    @Column(name = "clg_city")
    protected String collegeCity;

    @Column(name = "clg_div_id")
    protected String collegeDevisionId;
}
