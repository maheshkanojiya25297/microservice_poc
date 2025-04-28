package com.example.location.location_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Landmark {

    private String name;
    private double latitude;
    private double longitude;
}
