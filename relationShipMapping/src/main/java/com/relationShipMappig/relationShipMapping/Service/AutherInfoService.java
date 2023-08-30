package com.relationShipMappig.relationShipMapping.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.relationShipMappig.relationShipMapping.DTO.request.ServiceRequestBean;
import com.relationShipMappig.relationShipMapping.DTO.response.ServiceResponseBean;
import org.springframework.http.ResponseEntity;

public interface AutherInfoService {

    ResponseEntity<ServiceResponseBean> getAutherInfo();

    Object getSingleAutherInfo();

    Object getSingleAutherInfoSpecific();

    Object getSingleAutherInfoAll();

    Object getTupleData();

    Object saveAutherData(ServiceRequestBean serviceRequestBean) throws JsonProcessingException;
}
