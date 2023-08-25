package com.relationShipMappig.relationShipMapping.Service;

import com.relationShipMappig.relationShipMapping.DTO.postDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostInfoService {
    ResponseEntity<List<postDTO>> getPostInfo();
}
