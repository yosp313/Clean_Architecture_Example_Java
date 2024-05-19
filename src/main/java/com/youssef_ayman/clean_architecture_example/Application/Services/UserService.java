package com.youssef_ayman.clean_architecture_example.Application.Services;

import com.youssef_ayman.clean_architecture_example.Domain.Entities.User;
import com.youssef_ayman.clean_architecture_example.Domain.Repositories.UserRepo;

public class UserService {
    private static UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        UserService.userRepo = userRepo;
    }

    public void post(String name, int age, String job) {
        User user = new User(name, age, job);
        userRepo.save(user);
    }

    public User get(String name) {
        return userRepo.findByName(name);
    }
}
