package com.example.location.location_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NearByResponse {

    private double latitude;
    private double longitude;
    private List<Landmark> landmarks;
}
