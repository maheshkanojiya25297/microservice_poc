package com.implemented.hotel.service.services;

import com.implemented.hotel.service.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel create(Hotel hotel);

    List<Hotel> getAllDetails();

    Hotel getSingleHotel(String id);

}
