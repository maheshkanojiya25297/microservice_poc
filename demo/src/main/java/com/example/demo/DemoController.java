package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DemoController {


    @GetMapping
    public String test() {
        return "Test !";
    }

}
