package com.example.springboottest.controller;

import com.example.springboottest.annotation.NeedLogin;
import com.example.springboottest.data.entity.User;
import com.example.springboottest.exception.ErrorCode;
import com.example.springboottest.service.UserService;
import com.example.springboottest.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class ServiceController {

    @Autowired
    UserService userService;

    @GetMapping("/test")
    @NeedLogin
    public Object getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/login")
    public Object getUsers(HttpServletRequest req, HttpServletResponse resp, @RequestBody User user) {
        if (user == null || StringUtil.isEmpty(user.getUsername())){
            throw new ErrorCode.LogInException();
        }
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        try {
            req.getSession().setAttribute("ACCESS-TOKEN", loginUser.getToken());
            resp.setHeader("Authorization", loginUser.getToken());
        } catch (Exception e) {
            throw new ErrorCode.SystemErrorException();
        }
        return ResultUtils.success();
    }

    @PostMapping("/signup")
    public Object signUp(@RequestBody User user) {
        if (user == null || StringUtil.isEmpty(user.getUsername())){
            throw new ErrorCode.LogInException();
        }
        User loginUser = userService.login(user.getUsername(), user.getPassword());

        return ResultUtils.success();
    }
}
