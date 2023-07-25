package com.test.lcdw.crud.service.ZcrudUserService.repositories;

import com.test.lcdw.crud.service.ZcrudUserService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZcrudUserRepository extends JpaRepository<User, Integer> {
}
