package com.relationShipMappig.relationShipMapping.Service.Impl;

import com.relationShipMappig.relationShipMapping.DTO.response.ServiceResponseBean;
import com.relationShipMappig.relationShipMapping.Service.AutherInfoService;
import com.relationShipMappig.relationShipMapping.model.AutherDetails;
import com.relationShipMappig.relationShipMapping.repository.AutherDeatailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AutherInfoServiceImpl implements AutherInfoService {

    @Autowired
    private AutherDeatailsRepository autherDeatailsRepository;

    @Override
    public ResponseEntity<ServiceResponseBean> getAutherInfo() {
        try {
            log.info("getAutherInfo {} ");
           // AutherDetails hm = this.autherDeatailsRepository.getAutherInfo("male");
            Tuple autherDetails = this.autherDeatailsRepository.getAutherInfo("male");
            log.info("autherDetails: {}" + autherDetails);
            String EMAIL = autherDetails.get(1, String.class);
            String CONTACT = autherDetails.get(0, String.class);
            Map<String, String> hm = new HashMap<>();
            hm.put("email", EMAIL);
            hm.put("contact", CONTACT);
            return ResponseEntity.ok(ServiceResponseBean.builder().status(Boolean.TRUE).data(hm).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(ServiceResponseBean.builder().status(Boolean.TRUE).message("No data found for company.").build());
    }
}
