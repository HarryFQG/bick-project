package com.it.project.user.server.impl;

import com.it.project.user.dao.UserMapper;
import com.it.project.user.entity.User;
import com.it.project.user.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String login() {

        User user = new User();
        user.setUserId(1);
        userMapper.insertSelective(user);
        return null;
    }
}
