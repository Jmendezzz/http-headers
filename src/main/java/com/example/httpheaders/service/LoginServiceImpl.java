package com.example.httpheaders.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class LoginServiceImpl implements LoginService{

    @Override
    public Optional<String> getUsername(HttpServletRequest req) {
        Cookie cookies[] = req.getCookies() != null ? req.getCookies() : new Cookie[0];

        return Arrays.stream(cookies).
                filter(cookie -> cookie.getName().equals("username"))
                .map(cookie -> cookie.getValue()).findAny();


    }
}
