package com.example.springboottest.aspect;

import com.example.springboottest.exception.ErrorCode;
import com.example.springboottest.service.UserService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class Authority {

    @Autowired
    private UserService userService;

    //@target慎用，它代表所有该注解下的类的所有方法
    @Pointcut(value = "@annotation(com.example.springboottest.annotation.NeedLogin)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void checkAuth() {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String authorization = request.getHeader("Authorization");
        if (StringUtil.isEmpty(authorization)){
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0){
                for (Cookie cookie : cookies) {
                    String name = cookie.getName();
                    if ("ACCESS-TOKEN".equals(name)){
                        authorization = cookie.getComment();
                    }
                }
            }
        } else {
            authorization = authorization.split(" ")[1];
        }
        if (StringUtil.isEmpty(authorization)) {
            throw new ErrorCode.LogInException();
        }
        userService.checkAuth(authorization);
    }

}