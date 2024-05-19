package com.youssef_ayman.clean_architecture_example.Infrastructure;

import com.youssef_ayman.clean_architecture_example.Domain.Entities.User;
import com.youssef_ayman.clean_architecture_example.Domain.Repositories.UserRepo;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepo implements UserRepo {
    private Map<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public User findByName(String name) {
        return users.get(name);
    }
}
