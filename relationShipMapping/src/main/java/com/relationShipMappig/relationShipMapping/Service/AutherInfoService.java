package com.relationShipMappig.relationShipMapping.Service;

import com.relationShipMappig.relationShipMapping.DTO.response.ServiceResponseBean;
import org.springframework.http.ResponseEntity;

public interface AutherInfoService {

    ResponseEntity<ServiceResponseBean> getAutherInfo();

    Object getSingleAutherInfo();

    Object getSingleAutherInfoSpecific();
}
