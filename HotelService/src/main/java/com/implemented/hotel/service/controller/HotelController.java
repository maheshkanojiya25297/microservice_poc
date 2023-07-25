package com.implemented.hotel.service.controller;

import com.implemented.hotel.service.entities.Hotel;
import com.implemented.hotel.service.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;




    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        System.out.println("createUser API Calling........");
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }



    @GetMapping
    public ResponseEntity<List<Hotel>> getAllDetails() {
        System.out.println("getAllDetails API Calling......");
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAllDetails());
    }


    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getSingleHotel(@PathVariable String hotelId) {
        System.out.println("getSingleHotel API Calling......");
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getSingleHotel(hotelId));
    }


}
