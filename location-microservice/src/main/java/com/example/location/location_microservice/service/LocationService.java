package com.example.location.location_microservice.service;

import com.example.location.location_microservice.dto.Landmark;
import com.example.location.location_microservice.dto.NearByResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    public NearByResponse findNearbyLandmarks(double lat, double lon) {

        List<Landmark> landmarks = new ArrayList<>();
        landmarks.add(new Landmark("Dadar Circle", 40.67465, -90.45564));
        landmarks.add(new Landmark("Sion Circle", 20.67465, -80.45564));
        landmarks.add(new Landmark("Matunga Circle", 30.67465, -70.45564));
        landmarks.add(new Landmark("Dadar Circle", 40.67465, -90.45564));
        landmarks.add(new Landmark("Kurla Circle", 50.67465, -60.45564));
        landmarks.add(new Landmark("CST Circle", 60.67465, -50.564));

        return new NearByResponse(lat, lon, landmarks);
    }

}
