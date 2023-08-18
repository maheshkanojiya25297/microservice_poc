package com.relationShipMappig.relationShipMapping.dao;

import com.relationShipMappig.relationShipMapping.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao extends JpaRepository<Comment, Long> {
}
