package com.example.keycloak.springbootkeyclock.repository;

import com.example.keycloak.springbootkeyclock.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
