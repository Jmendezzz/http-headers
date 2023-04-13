package com.example.httpheaders.controller;

import com.example.httpheaders.service.LoginService;
import com.example.httpheaders.service.LoginServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.OptionalInt;

/* HttpSession vs Cookie
* HttpSession is directly related with the server, the cookie is related with the browser.
* The information in a Session is lost when the user close the browser, the cookie has the capacity to store information even if the cliente close the browser.
* HttpSession has more Security.
*  */

@WebServlet("/login")
public class LoginController extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService loginService = new LoginServiceImpl();
        Optional<String> username = loginService.getUsername(req);
        if(username.isPresent()){
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Hola " + username.get() + "</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Hola " + username.get() + " has iniciado sesión con éxito!</h1>");
                out.println("<p><a href='" + req.getContextPath() + "/'>volver</a></p>");
                out.println("<p><a href='" + req.getContextPath() + "/logout'>cerrar sesión</a></p>");
                out.println(" </body>");
                out.println("</html>");
            }

        }else{
            req.setAttribute("name","Test forward()");
            getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);
            //resp.sendRedirect(req.getContextPath() +"/login.jsp"); // With forward the name its redirected to the jsp because we are not doing a new request
            //But with sendRedirect it does not work bc it create a new request and lost data.

        }


    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            Cookie cookie = new Cookie("username",username);
            resp.addCookie(cookie);
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Login correcto</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Login correcto!</h1>");
                out.println(" <h3>Hola "+ username + " has iniciado sesión con éxito!</h3>");
                out.println(" </body>");
                out.println("</html>");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado para ingresar a esta página!");
        }
    }


}
