package com.implemented.user.service.external.services;

import com.implemented.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    //get

    @PostMapping("/ratings")
    public Rating createRating(Rating values);

    //put
    @PutMapping("/ratings/{ratingId}")
    public Rating updateRating(@PathVariable("ratingId") String ratingId, Rating rating);


    @DeleteMapping("ratings/{ratingId}")
    public void deleteRating(@PathVariable String ratingId);
}
