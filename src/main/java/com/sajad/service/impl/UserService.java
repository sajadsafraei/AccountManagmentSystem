package com.sajad.service.impl;

import com.sajad.dao.UserDao;
import com.sajad.model.User;
import com.sajad.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User createUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.findById(id).get();
    }
}
