<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
<div class="container">
    <h1 class="center-align">Iniciar sesión</h1>
    <div class="row">
        <form class="col s12" action="${pageContext.request.contextPath}/login" method="post" enctype="application/x-www-form-urlencoded">
            <div class="row">
                <div class="input-field col s12">
                    <input id="username" name="username" type="text" class="validate" required>
                    <label for="username">Nombre de usuario</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="password" name="password" type="password" class="validate" required>
                    <label for="password">Contraseña</label>
                </div>
            </div>
            <div class="row center" >
                <div class="col s12">
                    <input type="submit" value="Iniciar sesión" class="waves-effect waves-light btn">
                </div>
            </div>

            <% String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null) { %>
            <div class="row">
                <div class="col s12">
                    <p class="red-text"><%= errorMessage %></p>
                </div>
            </div>
            <% } %>

        </form>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>
