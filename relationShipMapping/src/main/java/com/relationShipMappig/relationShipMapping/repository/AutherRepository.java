package com.relationShipMappig.relationShipMapping.repository;

import com.relationShipMappig.relationShipMapping.model.Auther;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutherRepository extends JpaRepository<Auther, Long> {
}
