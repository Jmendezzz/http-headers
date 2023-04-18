package com.example.httpheaders.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;



@WebFilter({"/productos/*","/productos.*", "*.json"})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        String   username = (String) servletRequest.getSession().getAttribute("username");
        if (username !=null) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "No estás autorizado para ingresar a esta página!");
        }
    }
}



