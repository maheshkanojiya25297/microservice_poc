package com.relationShipMappig.relationShipMapping.repository;


import com.relationShipMappig.relationShipMapping.model.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {
}
