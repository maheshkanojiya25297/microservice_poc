package com.implemented.user.service.services;

import com.implemented.user.service.entities.User;

import java.util.List;


public interface UserServices {

    User saveUser(User user);

    User getSingleUser(String userId);

    List<User> getAllUser();
}
