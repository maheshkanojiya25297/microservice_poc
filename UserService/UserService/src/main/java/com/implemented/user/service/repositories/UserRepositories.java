package com.implemented.user.service.repositories;

import com.implemented.user.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositories extends JpaRepository<User, String> {

    @Override
    Optional<User> findById(String s);

    Optional<User> findByUserId(String userId);

}
