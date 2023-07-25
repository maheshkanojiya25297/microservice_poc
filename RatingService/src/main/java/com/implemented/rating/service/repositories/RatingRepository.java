package com.implemented.rating.service.repositories;

import com.implemented.rating.service.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {
    List<Rating> findByUserId(String userId);

    List<Rating> findByHotelId(String hotelId);
}
