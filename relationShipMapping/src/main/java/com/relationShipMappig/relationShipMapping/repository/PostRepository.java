package com.relationShipMappig.relationShipMapping.repository;

import com.relationShipMappig.relationShipMapping.DTO.postDTO;
import com.relationShipMappig.relationShipMapping.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
