<%@ page import="com.example.httpheaders.model.Product" %><%--
  Created by IntelliJ IDEA.
  User: jugem
  Date: 11/04/2023
  Time: 9:17 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Busca un producto</title>
</head>
<body>
  <h1>Busca un producto</h1>

  <form method="post">
    <input type="text" name="id" placeholder="Ingrese el id">
    <button type="submit">Buscar</button>

  </form>
  <% Product product = (Product) request.getAttribute("product"); %>
  <%if(product!=null){ %>
    <div>

      <p>id:<%=product.getId()%></p>
      <p>nombre:<%=product.getName()%></p>
      <p>categoria:<%=product.getCategory()%></p>
      <p>precio:<%=product.getPrice()%></p>
    </div>
  <%} %>


</body>
</html>
