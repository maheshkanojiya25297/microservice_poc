package com.relationShipMappig.relationShipMapping.dao;

import com.relationShipMappig.relationShipMapping.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDao extends JpaRepository<Post,Long> {
}
