package com.relationShipMappig.relationShipMapping.repository;

import com.relationShipMappig.relationShipMapping.model.AutherDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;

@Repository
public interface AutherDeatailsRepository extends JpaRepository<AutherDetails, Long> {


    //@Query("select new com.relationShipMappig.relationShipMapping.DTO.postDTO(post_title, post_description) from Post p where p.postPinsId=:post_pins_id")

//    @Query("select a from AutherDetails a where a.gender =:gender")
//    AutherDetails getAutherInfo(@Param("gender") String gender);
    @Query("select a.contact,a.email  from AutherDetails a where a.gender =:gender")
    Tuple getAutherInfo(@Param("gender") String gender);

}
