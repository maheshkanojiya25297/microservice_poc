package com.example.keycloak.springbootkeyclock.Controller;

import com.example.keycloak.springbootkeyclock.entity.Employee;
import com.example.keycloak.springbootkeyclock.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/employees")
    @RolesAllowed("user")
    public ResponseEntity<Employee> saveData(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeRepository.save(employee));
    }

    @GetMapping("/employees")
    @RolesAllowed("admin")
    public ResponseEntity<List<Employee>> getData() {
        return ResponseEntity.ok(employeeRepository.findAll());
    }
}
