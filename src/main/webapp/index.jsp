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
        <form id="login-form" class="col s12" action="${pageContext.request.contextPath}/login" method="post" enctype="application/x-www-form-urlencoded">
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

<%--            <div class="row center" >--%>
<%--            <button class="waves-effect waves-light btn g-recaptcha"--%>
<%--                    data-sitekey="6LfHyMIlAAAAAKKxKCiq0ndk_MtoizLp28uxeRLb"--%>
<%--                    data-callback='onSubmit'--%>
<%--                    data-action='submit'>Iniciar sesión</button>--%>

<%--                <input type="hidden" id="g-recaptcha-response" name="g-recaptcha-response">--%>

<%--            </div>--%>

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

<%--            reCaptchaV3 Google function.--%>
            <script>
                async function onSubmit(token) {
                    const recaptchaResponse = await grecaptcha.execute('6LfHyMIlAAAAAKKxKCiq0ndk_MtoizLp28uxeRLb', { action: 'submit' });
                    document.getElementById('g-recaptcha-response').value = recaptchaResponse;
                    document.getElementById("login-form").submit();
                }
            </script>

        </form>
    </div>
</div>





<%--MaterializeCSS--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<%--reCaptchaV3 Google--%>
<script src="https://www.google.com/recaptcha/api.js"></script>
</body>
</html>
