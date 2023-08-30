package com.relationShipMappig.relationShipMapping.DTO.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequestBean {
    private long id;
    private String gender;
    private String email;
    private String contact;
    private Date createdDate;
    private String createdBy;
    private Date lastModifiedDate;
    private String lastModifiedBy;
}
