package com.xin.service;

import com.xin.mapper.UserMapper;
import com.xin.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper mapper;

    @Override
    public User queryUserByName(String name) {
        return mapper.queryUserByName(name);
    }
}
