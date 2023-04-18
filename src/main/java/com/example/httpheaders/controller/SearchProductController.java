package com.example.httpheaders.controller;

import com.example.httpheaders.model.Product;
import com.example.httpheaders.service.LoginService;
import com.example.httpheaders.service.LoginServiceImpl;
import com.example.httpheaders.service.ProductService;
import com.example.httpheaders.singleton.SingletonProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;


@WebServlet("/search-product")
public class SearchProductController extends HttpServlet {
    private final ProductService service = SingletonProduct.getProductService();


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService loginService = new LoginServiceImpl();
        String username =(String) req.getSession().getAttribute("username");
        if (username == null){
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,"No has iniciado sesión :(");
            return;
        }
        getServletContext().getRequestDispatcher("/search-product.jsp").forward(req,resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Product product =  service.findById(id);
        if(product == null){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,"No se ha encontrado ningún producto con el id:"+id);
        }else{
            req.setAttribute("product",product);
            getServletContext().getRequestDispatcher("/search-product.jsp").forward(req,resp);


        }


    }


}
