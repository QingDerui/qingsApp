package com.example.springboottest.service;


import com.example.springboottest.data.entity.User;
import com.example.springboottest.exception.ErrorCode;
import com.example.springboottest.exception.MainServiceException;

import javax.servlet.ServletException;
import java.util.List;

public interface UserService {
    List<User> getUsers();

    void checkAuth(String token);

    User login(String username, String password);
}
