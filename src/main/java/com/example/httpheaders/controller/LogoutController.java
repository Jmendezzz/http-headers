package com.example.httpheaders.controller;

import com.example.httpheaders.service.LoginService;
import com.example.httpheaders.service.LoginServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LoginService loginService = new LoginServiceImpl();
        Optional<String> username = loginService.getUsername(req);
        if(username.isPresent()){
            Cookie usernameCookie = new Cookie("username", "");
            usernameCookie.setMaxAge(0);
            resp.addCookie(usernameCookie);
        }
        resp.sendRedirect(req.getContextPath()+"/");
    }
}
