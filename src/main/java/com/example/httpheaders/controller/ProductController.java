package com.example.httpheaders.controller;

import com.example.httpheaders.model.Product;
import com.example.httpheaders.service.LoginService;
import com.example.httpheaders.service.LoginServiceImpl;
import com.example.httpheaders.service.ProductService;
import com.example.httpheaders.service.ProductServiceImpl;
import com.example.httpheaders.singleton.SingletonProduct;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.xls", "/productos.html", "/productos"})
public class ProductController extends HttpServlet {
    private final ProductService service = SingletonProduct.getProductService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        LoginService loginService = new LoginServiceImpl();
        Optional<String> username= loginService.getUsername(req);
        List<Product> productos = service.listProducts();
        resp.setContentType("text/html;charset=UTF-8");
        String servletPath = req.getServletPath();
        boolean esXls = servletPath.endsWith(".xls");
        if(!username.isPresent()){
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,"No has iniciado sesión :(");
            return;
        }
        if (esXls) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition",

                    "attachment;filename=productos.xls");
        }
        try (PrintWriter out = resp.getWriter()) {
            if (!esXls) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Listado de Productos</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Listado de Productos!</h1>");
                out.println("<p><a href=\"" + req.getContextPath() + "/productos.xls" + "\">exportar a xls</a></p>");
                out.println("<p><a href=\"" + req.getContextPath() + "/productos.json" + "\">mostrar json</a></p>");

            }
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>id</th>");
            out.println("<th>nombre</th>");
            out.println("<th>tipo</th>");
            out.println("<th>precio</th>");
            out.println("</tr>");
            productos.forEach(p -> {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getName() + "</td>");
                out.println("<td>" + p.getCategory() + "</td>");
                out.println("<td>" + p.getPrice() + "</td>");
                out.println("</tr>");
            });
            out.println("</table>");
            if (!esXls) {
                out.println(" </body>");
                out.println("</html>");
            }
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream jsonStream = req.getInputStream();
        System.out.println(jsonStream);
        ObjectMapper mapper = new ObjectMapper();
        Product producto = mapper.readValue(jsonStream, Product.class);
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(" <head>");
            out.println(" <meta charset=\"UTF-8\">");
            out.println(" <title>Detalle de producto desde json</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println(" <h1>Detalle de producto desde json!</h1>");
            out.println("<ul>");
            out.println("<li>Id: "+ producto.getId() + "</li>");
            out.println("<li>Nombre: " + producto.getName() + "</li>");
            out.println("<li>Tipo: " + producto.getCategory() + "</li>");
            out.println("<li>Precio: " + producto.getPrice() + "</li>");
            out.println("</ul>");
            out.println(" </body>");
            out.println("</html>");
        }
    }
}
