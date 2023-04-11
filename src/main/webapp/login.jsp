<%--
  Created by IntelliJ IDEA.
  User: jugem
  Date: 11/04/2023
  Time: 8:56 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulario de login</title>
</head>
<body>
<h1>Hola, ${name}</h1>
<h1>Iniciar sesión</h1>
<form action="/test/login" method="post">
    <div>
        <label for="username">Username</label>
        <div>
            <input type="text" name="username" id="username">
        </div>
    </div>
    <div>
        <label for="password">Password</label>
        <div>
            <input type="password" name="password" id="password">
        </div>
    </div>
    <div>

        <input type="submit" value="Login">
    </div>
</form>
</body>
</html>