package com.relationShipMappig.relationShipMapping.Service.Impl;

import com.relationShipMappig.relationShipMapping.DTO.postDTO;
import com.relationShipMappig.relationShipMapping.DTO.response.ServiceResponseBean;
import com.relationShipMappig.relationShipMapping.Service.PostInfoService;
import com.relationShipMappig.relationShipMapping.model.Post;
import com.relationShipMappig.relationShipMapping.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PostInfoServiceImpl implements PostInfoService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public ResponseEntity<List<postDTO>> getPostInfo() {
        log.info("getPostInfo {} called -------------------------------------------------------------------------->:");
        List<Post> postInfo = this.postRepository.findAll();
        log.info("------------------------------------------------------------------------------------------------>:");
        log.info("postInfo: " + postInfo);
        List<postDTO> result = postInfo.stream().map(postInfo1 -> {
                    return postDTO.builder()
                            .postTitle(postInfo1.getPostTitle())
                            .postDescription(postInfo1.getPostDescription())
                            .build();
                }
        ).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }


}
