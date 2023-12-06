package com.example.springboottest.servlet;

import com.example.springboottest.data.entity.User;
import com.example.springboottest.data.mapper.db1.UserTkMapper;
import com.example.springboottest.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Autowired
    UserTkMapper userTkMapper;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userTkMapper.selectUser(username, password);
        if(user == null) {
            throw new ServletException(ErrorCode.NO_AUTH.getMessage());
        }
        try{
            SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedData = cipher.doFinal(username.getBytes());
            req.getSession().setAttribute("ACCESS-TOKEN", encryptedData);
            resp.setHeader("Authorization", new String(encryptedData));
        } catch (Exception e) {
            throw new ServletException(ErrorCode.SYSTEM_ERROR.getMessage());
        }
        resp.sendRedirect("/test");
    }
}
