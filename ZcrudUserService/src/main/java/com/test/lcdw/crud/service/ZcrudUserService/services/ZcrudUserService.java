package com.test.lcdw.crud.service.ZcrudUserService.services;

import com.test.lcdw.crud.service.ZcrudUserService.entities.User;

import java.util.List;
import java.util.Optional;

public interface ZcrudUserService {

    User creatUser(User user);

    List<User> getAllUser();

    Optional<User> getSingleUser(Integer Id);


    String delUser(Integer userId);

    User updateUser(User user);
}
