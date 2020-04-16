package com.sajad.service;

import com.sajad.model.User;

public interface IUserService {
    User createUser(User user);
    User getUserById(Long id);
}
