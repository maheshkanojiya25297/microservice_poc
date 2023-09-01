package com.jwt.token.generation.services;

import com.jwt.token.generation.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private List<User> store = new ArrayList<>();

    public UserService() {
        store.add(new User(UUID.randomUUID().toString(), "mahesh Kanojiya", "mahesh.kanojiya@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "prathmesh singh", "singh@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "aarti Kanojiya", "aarti@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "suraj Kanojiya", "suraj@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "Yash Kanojiya", "yash@gmail.com"));
    }

    public List<User> getUser() {
        return this.store;
    }
}
