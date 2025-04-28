package com.example.location.location_microservice.controller;

import com.example.location.location_microservice.dto.NearByResponse;
import com.example.location.location_microservice.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/nearby")
    public NearByResponse getNearbyLandMarks(
            @RequestParam double lat,
            @RequestParam double lon
    ) {
        if(lat<0 || lon<0){
          throw new RuntimeException("latitude and longitude must be possitive !");
        }
        return locationService.findNearbyLandmarks(lat, lon);
    }
}
