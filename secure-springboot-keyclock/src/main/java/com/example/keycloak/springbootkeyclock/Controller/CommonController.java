package com.example.keycloak.springbootkeyclock.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/test")
public class CommonController {

    @GetMapping("/admin")
    @RolesAllowed("admin")
    public String getadmin() {
        return "Success for admin !";
    }

    @GetMapping("/superadmin")
    @RolesAllowed("super_admin")
    public String getsupeadmin() {
        return "Sucess for super admin";
    }
}
