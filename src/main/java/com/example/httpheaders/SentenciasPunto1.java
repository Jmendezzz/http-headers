package com.example.httpheaders;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

//Testing 1.
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class SentenciasPunto1 extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        String metodoHttp = req.getMethod();//Get the method of the request (REST)
        String requestUri = req.getRequestURI();//Resources
        String requestUrl = req.getRequestURL().toString();//Link
        String contexPath = req.getContextPath();//Get the context of the request, it is defined by tomcat deployment config.
        String servletPath = req.getServletPath(); //Get only the path of the servlet.
        String ipCliente = req.getRemoteAddr(); // Get the ip of the client by the request.
        String ip = req.getLocalAddr(); // Get the ip of the server, where the request was received
        int port = req.getLocalPort(); //Get the port used by the server.
        String scheme = req.getScheme(); //Scheme: http, https to build an URL
        String host = req.getHeader("host"); //Find by name on a request header
        String url = scheme + "://" + host + contexPath + servletPath;
        String url2 = scheme + "://" + ip + ":" + port + contexPath + servletPath;
        System.out.println(url);
        System.out.println(url2);
        System.out.println("URI:"+ requestUri);
        System.out.println("URL:"+ requestUrl);

    }
    /*  sendRedirect() vs forward()
    *  sendRedirect() creates a new request and forward() use the same request and response to another servlet
    * In forward method you can set add and set data
    *
    * */


}