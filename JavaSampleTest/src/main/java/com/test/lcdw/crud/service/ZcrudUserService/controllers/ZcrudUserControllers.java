package com.test.lcdw.crud.service.ZcrudUserService.controllers;

import com.test.lcdw.crud.service.ZcrudUserService.entities.User;
import com.test.lcdw.crud.service.ZcrudUserService.exceptions.ResourceNotFoundException;
import com.test.lcdw.crud.service.ZcrudUserService.services.ZcrudUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class ZcrudUserControllers {

    @Autowired
    private ZcrudUserService zcrudUserService;


    @GetMapping
    public  String test(){
        return  "Test !";
    }

    @PostMapping
    public ResponseEntity<User> creatUser(@RequestBody User user) {
        User user1 = zcrudUserService.creatUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> result = zcrudUserService.getAllUser();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> getSingleUser(@PathVariable Integer userId) {
        Optional<User> user = zcrudUserService.getSingleUser(userId);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{userId}")
    public String delUser(@PathVariable Integer userId) {
        String response = zcrudUserService.delUser(userId);
        return response;
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Integer userId, @RequestBody User user) {
        User singleMap = zcrudUserService.getSingleUser(userId).orElseThrow(() -> new ResourceNotFoundException("User not exists with Id : " + userId));
        System.out.println("singleMap : " + singleMap);
        singleMap.setName(user.getName());
        singleMap.setCity(user.getCity());
        singleMap.setGender(user.getGender());
        User output = zcrudUserService.updateUser(singleMap);
        return ResponseEntity.ok().body(output);
    }


}
