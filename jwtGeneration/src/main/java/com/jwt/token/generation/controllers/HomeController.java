package com.jwt.token.generation.controllers;

import com.jwt.token.generation.model.User;
import com.jwt.token.generation.repositories.AddressRepository;
import com.jwt.token.generation.service.AddressService;
import com.jwt.token.generation.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/home")
public class HomeController {

    // http://localhost:8081/home/user

    @Autowired
    private AddressService addressService;

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

    @GetMapping("/address")
    public ResponseEntity<?> getAddress() {
        return ResponseEntity.ok(this.addressService.getAddress());
    }

    @GetMapping("/jasperpdf/export")
    public void createPDF(HttpServletResponse response) throws IOException, JRException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        log.info("currentDateTime:-------------------> " +currentDateTime);
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        log.info("headerValue:-----------------------> " +headerValue);
        response.setHeader(headerKey, headerValue);
        addressService.exportJasperReport(response);
    }

    @GetMapping("/report")
    public String empReport() throws JRException, FileNotFoundException {
        return addressService.generateReport();
    }

}
