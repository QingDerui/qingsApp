package com.example.springboottest.service.impl;

import com.example.springboottest.data.entity.User;
import com.example.springboottest.data.mapper.db1.UserTkMapper;
import com.example.springboottest.exception.ErrorCode;
import com.example.springboottest.exception.MainServiceException;
import com.example.springboottest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserTkMapper userTkMapper;

    @Override
    public List<User> getUsers() {
        return userTkMapper.getAllUsers();
    }

    @Override
    public void checkAuth(String token){
        if (StringUtil.isEmpty(token)) {
            throw new ErrorCode.LogInException();
        }
        String user = userTkMapper.checkUserByToken(token);
        if (StringUtil.isEmpty(user)) {
            throw new ErrorCode.LogInException();
        }
    }

    @Override
    public User login(String username, String password) throws MainServiceException {
        User user = userTkMapper.selectUser(username, password);
        if (user == null) {
            throw new ErrorCode.LogInException();
        }
        return user;
    }
}
