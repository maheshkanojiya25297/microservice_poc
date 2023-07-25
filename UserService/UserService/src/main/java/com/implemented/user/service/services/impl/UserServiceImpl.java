package com.implemented.user.service.services.impl;

import com.implemented.user.service.entities.Hotel;
import com.implemented.user.service.entities.Rating;
import com.implemented.user.service.entities.User;
import com.implemented.user.service.exceptions.ResourceNotFoundException;
import com.implemented.user.service.external.services.HotelService;
import com.implemented.user.service.repositories.UserRepositories;
import com.implemented.user.service.services.UserServices;
/*
import mapstruct.com.implemented.user.service.UserPojjoEntityMapper;
import payload.com.implemented.user.service.UserRequestBean;
import payload.com.implemented.user.service.UserResponseBean;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServices {

/*
    @Autowired
    private UserPojjoEntityMapper userPojjoEntityMapper;
*/

    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        //logger.info("userRequestBean {}: " + userRequestBean);
        //User user = userPojjoEntityMapper.roleUserEntityPojo.apply(userRequestBean);
        //User user = userPojjoEntityMapper.roleUserEntityPojo(userRequestBean);
        logger.info("user save {}: " + user.getName());
        return userRepositories.save(user);
    }

    @Override
    public User getSingleUser(String userId) {
        User user1= userRepositories.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("User with given Id is not found on server !! :" + userId));
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user1.getUserId(), Rating[].class);
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user1.setRatings(ratingList);
       // UserResponseBean userResponseBean = this.userPojjoEntityMapper.ResponseRoleUserEntityPojo(user);
       // return userResponseBean;
        return user1;
    }

    @Override
    public List<User> getAllUser() {
        List<User> user1 = userRepositories.findAll();
        for (User user : user1) {
            // ArrayList<Rating> ratingsOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/" + user.getUserId(), ArrayList.class);
            Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
            logger.info("{} ", ratingsOfUser);
            List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
            List<Rating> ratingList = ratings.stream().map(rating -> {
                ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
                Hotel hotel = forEntity.getBody();
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());

            user.setRatings(ratingList);
            //user.setRatings(ratingsOfUser);
            // return userRepositories.findAll();
        }
        return user1;
    }


}
