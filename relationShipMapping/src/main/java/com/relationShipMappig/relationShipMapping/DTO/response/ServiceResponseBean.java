package com.relationShipMappig.relationShipMapping.DTO.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ServiceResponseBean {

	private Object data;
	
	private Integer errorCode;
	
	@Default
	private Boolean status = Boolean.FALSE;
	
	private String message;
	
	private Object extraData;
}
