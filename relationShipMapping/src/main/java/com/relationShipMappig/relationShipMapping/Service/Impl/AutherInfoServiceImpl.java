package com.relationShipMappig.relationShipMapping.Service.Impl;

import com.relationShipMappig.relationShipMapping.DTO.AuthDetailsDTO;
import com.relationShipMappig.relationShipMapping.DTO.response.ServiceResponseBean;
import com.relationShipMappig.relationShipMapping.Service.AutherInfoService;
import com.relationShipMappig.relationShipMapping.model.AutherDetails;
import com.relationShipMappig.relationShipMapping.repository.AutherDeatailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class AutherInfoServiceImpl implements AutherInfoService {

    @Autowired
    private AutherDeatailsRepository autherDeatailsRepository;

    @Override
    public ResponseEntity<ServiceResponseBean> getAutherInfo() {
        try {
            List<Tuple> autherDetails1 = this.autherDeatailsRepository.getAutherInfo("male", 2);
            log.info("autherDetails: {}" + autherDetails1);
            List<Map<String, String>> listHashMap = new ArrayList<>();
            for (Tuple autherDetails : autherDetails1) {
                Map<String, String> map = new HashMap<>();
                map.put("email", autherDetails.get(1, String.class));
                map.put("contact", Objects.nonNull(autherDetails.get(0, String.class)) ? autherDetails.get(0, String.class) : null);
                listHashMap.add(map);
            }
            return ResponseEntity.ok(ServiceResponseBean.builder().status(Boolean.TRUE).data(listHashMap).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(ServiceResponseBean.builder().status(Boolean.TRUE).message("No data found for company.").build());
    }

    @Override
    public Object getSingleAutherInfo() {
        try {
            String input = "male";
            Object result = this.autherDeatailsRepository.getSingleAutherInfo(input);
            log.info("getSingleAutherInfo {} result: " + result);
            return ResponseEntity.ok(ServiceResponseBean.builder().data(result).message("Data found for auther.").status(Boolean.TRUE).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(ServiceResponseBean.builder().status(Boolean.TRUE).message("No data found for auther.").build());
    }

    @Override
    public Object getSingleAutherInfoSpecific() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String input = "male";
            Tuple autherDetails = this.autherDeatailsRepository.getSingleAutherInfoSpecific(input);
            log.info("getSingleAutherInfoSpecific {} result: " + autherDetails);
            HashMap<String, String> hm = new HashMap<>();
            String CONTACT = Objects.nonNull(autherDetails.get(0, String.class)) ? autherDetails.get(0, String.class) : null;
            String EMAIL = Objects.nonNull(autherDetails.get(1, String.class)) ? autherDetails.get(1, String.class) : null;
            String CONTACTBY = Objects.nonNull(autherDetails.get(2, String.class)) ? autherDetails.get(2, String.class) : null;
            String CONTACTDATE = Objects.nonNull(autherDetails.get(3, Date.class)) ? dateFormat.format(autherDetails.get(3, Date.class)) : null;
            hm.put("email", EMAIL);
            hm.put("contact", CONTACT);
            hm.put("contactBy", CONTACTBY);
            hm.put("contactDate", CONTACTDATE);
            return ResponseEntity.ok(ServiceResponseBean.builder().data(hm).message("Data found for auther.").status(Boolean.TRUE).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(ServiceResponseBean.builder().status(Boolean.TRUE).message("No data found for auther.").build());
    }

    @Override
    public ResponseEntity<ServiceResponseBean> getSingleAutherInfoAll() {
        try {
            List<AutherDetails> autherDetails1 = this.autherDeatailsRepository.findByGenderNotNullOrderByIdDesc();
            //this.findByGenderNotNull();
            //this.autherDeatailsRepository.getSingleAutherInfoAll();
            log.info("getSingleAutherInfoAll {} autherDetails: " + autherDetails1);
            List<AuthDetailsDTO> result = new ArrayList<>();
            for (AutherDetails autherDetails : autherDetails1) {
                AuthDetailsDTO authDetailsDTO = new AuthDetailsDTO();
                authDetailsDTO.setId(autherDetails.getId());
                authDetailsDTO.setCreatedBy(autherDetails.getCreatedBy());
                authDetailsDTO.setContact(autherDetails.getContact());
                authDetailsDTO.setEmail(autherDetails.getEmail());
                result.add(authDetailsDTO);
            }
            return ResponseEntity.ok(ServiceResponseBean.builder().data(result).message("Data found for auther.").status(Boolean.TRUE).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(ServiceResponseBean.builder().status(Boolean.TRUE).message("No data found for auther.").build());
    }
}
