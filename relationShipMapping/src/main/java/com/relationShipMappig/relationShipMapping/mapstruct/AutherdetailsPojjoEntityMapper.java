package com.relationShipMappig.relationShipMapping.mapstruct;

import com.relationShipMappig.relationShipMapping.DTO.request.ServiceRequestBean;
import com.relationShipMappig.relationShipMapping.model.AutherDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AutherdetailsPojjoEntityMapper {

    AutherdetailsPojjoEntityMapper INSTANCE = Mappers.getMapper(AutherdetailsPojjoEntityMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "serviceRequestBean.id"),
            @Mapping(target = "gender", source = "serviceRequestBean.gender"),
            @Mapping(target = "email", source = "serviceRequestBean.email"),
            @Mapping(target = "contact", source = "serviceRequestBean.contact"),
            @Mapping(target = "createdDate", source = "serviceRequestBean.createdDate"),
            @Mapping(target = "createdBy", source = "serviceRequestBean.createdBy"),
            @Mapping(target = "lastModifiedDate", source = "serviceRequestBean.lastModifiedDate"),
            @Mapping(target = "lastModifiedBy", source = "serviceRequestBean.lastModifiedBy"),
            @Mapping(target = "responseBodyTxt", source = "serviceRequestBean.responseBodyTxt")
    })
    AutherDetails roleUserEntityPojo(ServiceRequestBean serviceRequestBean);
}
