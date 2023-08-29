package com.relationShipMappig.relationShipMapping.repository;

import com.relationShipMappig.relationShipMapping.model.AutherDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository
public interface AutherDeatailsRepository extends JpaRepository<AutherDetails, Long> {


    //@Query("select new com.relationShipMappig.relationShipMapping.DTO.postDTO(post_title, post_description) from Post p where p.postPinsId=:post_pins_id")

    //    @Query("select a from AutherDetails a where a.gender =:gender")
    //    AutherDetails getAutherInfo(@Param("gender") String gender);


    /* Data feth using List of Tuple */
    @Query("select a.contact,a.email  from AutherDetails a where a.gender =:gender and a.id <=:id")
    List<Tuple> getAutherInfo(@Param("gender") String gender, @Param("id") long id);

    /* Data feth using Entity Class */
    @Query("select a from AutherDetails a where a.gender =:gender")
    AutherDetails getSingleAutherInfo(@Param("gender") String gender);

    /* Data feth using Tuple*/
    @Query("select a.contact, a.email, a.createdBy,a.createdDate from AutherDetails a where a.gender =:gender")
    Tuple getSingleAutherInfoSpecific(@Param("gender") String gender);

    /* Data fetch using DTO*/
    @Query("select a from AutherDetails a where a.gender is not null")
    List<AutherDetails> getSingleAutherInfoAll();

    /* Data fetch using Derived Query*/
    List<AutherDetails> findByGenderNotNull();

    /* Data fetch using Derived Query*/
    List<AutherDetails> findByGenderNotNullOrderByIdDesc();
}
