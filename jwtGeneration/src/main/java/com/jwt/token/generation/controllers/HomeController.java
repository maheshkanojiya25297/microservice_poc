package com.jwt.token.generation.controllers;

import com.jwt.token.generation.model.User;
import com.jwt.token.generation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    // http://localhost:8081/home/user

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> getUser() {
        System.out.println("getting user");
        return this.userService.getUser();
    }

    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal) {
        return principal.getName();
    }
}
