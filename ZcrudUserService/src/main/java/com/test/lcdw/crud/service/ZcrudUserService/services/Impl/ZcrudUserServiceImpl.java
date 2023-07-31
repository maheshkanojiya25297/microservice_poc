package com.test.lcdw.crud.service.ZcrudUserService.services.Impl;

import com.test.lcdw.crud.service.ZcrudUserService.entities.User;
import com.test.lcdw.crud.service.ZcrudUserService.services.ZcrudUserService;
import com.test.lcdw.crud.service.ZcrudUserService.exceptions.ResourceNotFoundException;
import com.test.lcdw.crud.service.ZcrudUserService.repositories.ZcrudUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZcrudUserServiceImpl implements ZcrudUserService {

    @Autowired
    private ZcrudUserRepository zcrudUserRepository;

    @Override
    public User creatUser(User user) {
        return zcrudUserRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return zcrudUserRepository.findAll();
    }

    @Override
    public Optional<User> getSingleUser(Integer Id) {
        return Optional.ofNullable(zcrudUserRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("User with this ID does Not Exists")));
    }

    @Override
    public String delUser(Integer userId) {
        zcrudUserRepository.deleteById(userId);
        return "Id Deleted Successfully !";
    }

    @Override
    public User updateUser(User user) {
        System.out.println("Update done successfully : " +user);
        return zcrudUserRepository.save(user);
    }
}

