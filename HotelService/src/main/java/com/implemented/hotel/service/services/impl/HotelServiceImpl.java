package com.implemented.hotel.service.services.impl;

import com.implemented.hotel.service.entities.Hotel;
import com.implemented.hotel.service.exception.ResourceNotFoundException;
import com.implemented.hotel.service.repositories.HotelRepository;
import com.implemented.hotel.service.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        System.out.println("hotelId: " + hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllDetails() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getSingleHotel(String id) {
        return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel with this id does not exist !!"));
    }
}
