package com.relationShipMappig.relationShipMapping.repository;

import com.relationShipMappig.relationShipMapping.model.Auther;
import com.relationShipMappig.relationShipMapping.model.AutherDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutherDeatailsRepository extends JpaRepository<AutherDetails, Long> {
}
