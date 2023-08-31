package com.relationShipMappig.relationShipMapping.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollegAutherDTO {

    private String collegeName;
    private String collegeLocation;
    private String gender;
    private String contact;
    private String email;
    private String createdDate;
}
