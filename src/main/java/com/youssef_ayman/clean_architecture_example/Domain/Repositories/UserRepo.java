package com.youssef_ayman.clean_architecture_example.Domain.Repositories;

import com.youssef_ayman.clean_architecture_example.Domain.Entities.User;

public interface UserRepo {
    public void save(User user);
    public User findByName(String name);
}
