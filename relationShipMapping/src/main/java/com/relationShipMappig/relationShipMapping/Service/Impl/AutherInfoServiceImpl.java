package com.relationShipMappig.relationShipMapping.Service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.relationShipMappig.relationShipMapping.DTO.AuthDetailsDTO;
import com.relationShipMappig.relationShipMapping.DTO.CollegAutherDTO;
import com.relationShipMappig.relationShipMapping.DTO.DetailsDTO;
import com.relationShipMappig.relationShipMapping.DTO.request.ServiceRequestBean;
import com.relationShipMappig.relationShipMapping.DTO.response.ServiceResponseBean;
import com.relationShipMappig.relationShipMapping.Service.AutherInfoService;
import com.relationShipMappig.relationShipMapping.mapstruct.AutherdetailsPojjoEntityMapper;
import com.relationShipMappig.relationShipMapping.model.AutherDetails;
import com.relationShipMappig.relationShipMapping.repository.AutherDeatailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Slf4j
public class AutherInfoServiceImpl implements AutherInfoService {

    @Autowired
    private AutherDeatailsRepository autherDeatailsRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AutherdetailsPojjoEntityMapper autherdetailsPojjoEntityMapper;

    @Override
    public ResponseEntity<ServiceResponseBean> getAutherInfo() {
        try {
            List<Tuple> autherDetails1 = this.autherDeatailsRepository.getAutherInfo("male", 2);
            log.info("autherDetails: {}" + autherDetails1);
            List<Map<String, String>> listHashMap = new ArrayList<>();
            for (Tuple autherDetails : autherDetails1) {
                Map<String, String> map = new HashMap<>();
                map.put("email", Objects.nonNull(autherDetails.get(1, String.class)) ? autherDetails.get(1, String.class) : null);
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
            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            List<AutherDetails> autherDetails1 = this.autherDeatailsRepository.findByGenderNotNullOrderByIdDesc();
            //this.autherDeatailsRepository.findByGenderNotNull();
            // this.autherDeatailsRepository.getSingleAutherInfoAll();
            //this.autherDeatailsRepository.findByGenderNotNullOrderByIdDesc();
            log.info("getSingleAutherInfoAll {} autherDetails: " + autherDetails1);
            List<AuthDetailsDTO> result = new ArrayList<>();
            for (AutherDetails autherDetails : autherDetails1) {
                String date = autherDetails.getCreatedDate().toString();
                Date currentdate;
                currentdate = sdf.parse(date);
                log.info("currentdate: " + currentdate);
                AuthDetailsDTO authDetailsDTO = new AuthDetailsDTO();
                authDetailsDTO.setId(Objects.nonNull(autherDetails.getId()) ? autherDetails.getId() : null);
                authDetailsDTO.setCreatedBy(Objects.nonNull(autherDetails.getCreatedBy()) ? autherDetails.getCreatedBy() : null);
                authDetailsDTO.setContact(Objects.nonNull(autherDetails.getContact()) ? autherDetails.getContact() : null);
                authDetailsDTO.setEmail(Objects.nonNull(autherDetails.getEmail()) ? autherDetails.getEmail() : null);
                authDetailsDTO.setGender(Objects.nonNull(autherDetails.getGender()) ? autherDetails.getGender() : null);
                authDetailsDTO.setCreatedDate(Objects.nonNull(autherDetails.getCreatedDate()) ? currentdate : null);
                authDetailsDTO.setLastModifiedBy(Objects.nonNull(autherDetails.getLastModifiedBy()) ? autherDetails.getLastModifiedBy() : null);
                authDetailsDTO.setLastModifiedDate(Objects.nonNull(autherDetails.getLastModifiedDate()) ? autherDetails.getLastModifiedDate() : null);
                result.add(authDetailsDTO);
            }
            getDataByNativeQyery();
            return ResponseEntity.ok(ServiceResponseBean.builder().data(result).message("Data found for auther.").status(Boolean.TRUE).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(ServiceResponseBean.builder().status(Boolean.TRUE).message("No data found for auther.").build());
    }

    @Override
    public Object getTupleData() {
        try {
            Query query = this.entityManager.createNativeQuery("SELECT * FROM AUTHERDETAIL", Tuple.class);
            List<Tuple> tupleList = query.getResultList();
            //List<Tuple> result =tupleList.stream().collect(Collectors.toList());
            List<AuthDetailsDTO> authDetailsDTOS = new ArrayList<>();
            for (Tuple result : tupleList) {
                log.info("AutherInfoServiceImpl {} getTupleData(): \r\n" + result.get(0, BigInteger.class) + "\r\n" + result.get(1, String.class) + "\r\n" + result.get(2, String.class) +
                        "\r\n" + result.get(3, Date.class) + "\r\n" + result.get(4, String.class) + "\r\n" + result.get(5, String.class) +
                        "\r\n" + result.get(6, String.class) + "\r\n" + result.get(7, Date.class) + "\r\n" + result.get(8, BigInteger.class));

            }

            String openStr = "'";
            String closeStr = "'";
            String format = openStr + "%W %M %e %Y" + closeStr;
            log.info("AutherInfoServiceImpl {} getTupleData(): \r\n" + format);
            Query query1 = this.entityManager.createNativeQuery("SELECT id,gender,email,contact,created_by,created_date  as created_date,last_modified_by,last_modified_date,response_body_txt FROM AUTHERDETAIL", AutherDetails.class);
            log.info("AutherInfoServiceImpl {} getTupleData(): \r\n" + query1);
            List<AutherDetails> lsMap = query1.getResultList();
            List<AuthDetailsDTO> result = new ArrayList<>();
            for (AutherDetails map : lsMap) {
                log.info("AutherInfoServiceImpl {} getTupleData(): \r\n" + map.getCreatedDate());
                AuthDetailsDTO authDetailsDTOS1 = new AuthDetailsDTO();
                authDetailsDTOS1.setId(map.getId());
                authDetailsDTOS1.setContact(map.getContact());
                authDetailsDTOS1.setGender(map.getGender());
                authDetailsDTOS1.setCreatedDate(map.getCreatedDate());
                authDetailsDTOS1.setEmail(map.getEmail());
                authDetailsDTOS1.setLastModifiedDate(map.getLastModifiedDate());
                authDetailsDTOS1.setLastModifiedBy(map.getLastModifiedBy());
                authDetailsDTOS1.setCreatedBy(map.getCreatedBy());
                result.add(authDetailsDTOS1);
            }

            query = this.entityManager.createNativeQuery("SELECT id,gender,email,contact,created_by,created_date,last_modified_by,last_modified_date,response_body_txt FROM AUTHERDETAIL", Tuple.class);
            log.info("query: " + query);
            List<Tuple> detailsList = query.getResultList();
            List<DetailsDTO> detailsDTOS = new ArrayList<>();

            for (Tuple tupleResult : detailsList) {

                log.info("AutherInfoServiceImpl {} getTupleData(): \r\n" + tupleResult.get(0, BigInteger.class)
                        + "\r\n" + tupleResult.get(1, String.class) + "\r\n" + tupleResult.get(2, String.class)
                        + "\r\n" + tupleResult.get(3, String.class) + "\r\n" + tupleResult.get(4, String.class)
                        + "\r\n" + tupleResult.get(5, Timestamp.class) + "\r\n" + tupleResult.get(6, String.class)
                        + "\r\n" + tupleResult.get(7, Timestamp.class)
                        + "\r\n" + tupleResult.get(8, String.class));

                Timestamp createdDate = tupleResult.get(5, Timestamp.class);
                String createdDateStr = createdDate.toString();
                Timestamp modifiedDate = tupleResult.get(7, Timestamp.class);
                String modifiedDateStr = modifiedDate.toString();
                log.info("createdDateStr---------------------->:" + createdDateStr);
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                String responseBodyTxtResponse = tupleResult.get(8, String.class);
                log.info("responseBodyTxtResponse---------------------->:" + responseBodyTxtResponse);
                AutherDetails reportMasterDatabaseList = this.objectMapper.readValue(responseBodyTxtResponse, new TypeReference<AutherDetails>() {
                });
                log.info("reportMasterDatabaseList---------------------->: " + reportMasterDatabaseList);

                detailsDTOS.add(DetailsDTO.builder()
                        .id(String.valueOf(tupleResult.get(0, BigInteger.class)))
                        .email(tupleResult.get(2, String.class))
                        .gender(tupleResult.get(1, String.class))
                        .contact(tupleResult.get(3, String.class))
                        .createdBy(tupleResult.get(4, String.class))
                        .createdDate(createdDateStr)
                        .lastModifiedBy(tupleResult.get(6, String.class))
                        .lastModifiedDate(modifiedDateStr)
                        .responseBodyTxt(reportMasterDatabaseList)
                        .build());
            }

            Optional<AutherDetails> ListOfOutPut = this.autherDeatailsRepository.findById(1L);
            /*Reading Json Response Using ObjectMapper*/
            AutherDetails reportMasterDatabaseList = this.objectMapper.readValue(ListOfOutPut.get().getResponseBodyTxt(), new TypeReference<AutherDetails>() {
            });
            log.info("reportMasterDatabaseList: " + reportMasterDatabaseList);
            log.info("\r\n id: " + reportMasterDatabaseList.getId() + "\r\n gender: " + reportMasterDatabaseList.getGender() + "\r\n email: " + reportMasterDatabaseList.getEmail());
            log.info("contact of Id 1: " + ListOfOutPut.get().getContact());

            return ResponseEntity.ok(ServiceResponseBean.builder().data(detailsDTOS).status(Boolean.TRUE).message("Data recieved.").build());
            //return ResponseEntity.ok(ServiceResponseBean.builder().data(result).status(Boolean.TRUE).message("Data recieved.").build());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.ok(ServiceResponseBean.builder().message("No data exist").status(Boolean.FALSE).errorCode(716).build());
    }

    private void getDataByNativeQyery() {
        Query query;
        query = this.entityManager.createNativeQuery("select * from autherdetail", Tuple.class);
        log.info("Query: " + query);
        List<Tuple> tupleList = query.getResultList();
        log.info("tupleList: " + tupleList);
        for (Tuple tpl : tupleList) {
            System.out.println(tpl.get(0, BigInteger.class));
            log.info(tpl.get(1, String.class));
        }
    }


    public ObjectMapper objectMapper = fetchObjectMapperInstance();

    /*Object Mapper Logic added to save entire response in to database*/
    public ObjectMapper fetchObjectMapperInstance() {
        ObjectMapper objectMapperClass = new ObjectMapper();
        objectMapperClass.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapperClass.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapperClass.findAndRegisterModules();
        objectMapperClass.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
        return objectMapperClass;
    }

    @Override
    public Object saveAutherData(ServiceRequestBean serviceRequestBean) throws JsonProcessingException {
        log.info("saveAutherData{} serviceRequestBean: " + serviceRequestBean);
        /*Writing Json Response Using ObjectMappr*/
        String writeValueAsString = this.objectMapper.writeValueAsString(serviceRequestBean);
        serviceRequestBean.setResponseBodyTxt(writeValueAsString);
        AutherDetails autherDetails = autherdetailsPojjoEntityMapper.roleUserEntityPojo(serviceRequestBean);
        log.info("saveAutherData{} autherDetails: " + autherDetails);
        return this.autherDeatailsRepository.save(autherDetails);
    }

    @Override
    public Object getAutherCollegeDetails() {
        try {
            log.info("getAutherCollegeDetails: {}");
            Tuple autherCollegeList = this.autherDeatailsRepository.getAutherCollegeDetails("male", "VJTI");
            log.info("autherCollegeList: {}"
                    + "\r\n" + autherCollegeList.get(0, String.class)
                    + "\r\n" + autherCollegeList.get(1, String.class)
                    + "\r\n" + autherCollegeList.get(2, String.class)
                    + "\r\n" + autherCollegeList.get(3, String.class)
                    + "\r\n" + autherCollegeList.get(4, String.class)
                    + "\r\n" + autherCollegeList.get(5, Timestamp.class));

            CollegAutherDTO collegAutherDTO = CollegAutherDTO.builder()
                    .collegeName(autherCollegeList.get(0, String.class))
                    .collegeLocation(autherCollegeList.get(1, String.class))
                    .contact(autherCollegeList.get(2, String.class))
                    .email(autherCollegeList.get(3, String.class))
                    .gender(autherCollegeList.get(4, String.class))
                    .createdDate(String.valueOf(autherCollegeList.get(5, Timestamp.class)))
                    .build();
            return ResponseEntity.ok(ServiceResponseBean.builder().data(collegAutherDTO).status(Boolean.TRUE).message("Data recieved.").build());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.ok(ServiceResponseBean.builder().message("No data exist").status(Boolean.FALSE).errorCode(716).build());
    }
}
