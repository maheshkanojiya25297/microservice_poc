package com.relationShipMappig.relationShipMapping.DTO;

import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailsDTO {
    private String id;
    private String gender;
    private String email;
    private String contact;
    private String createdDate;
    private String createdBy;
    private String lastModifiedDate;
    private String lastModifiedBy;
}
