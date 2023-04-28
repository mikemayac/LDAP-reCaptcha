/**
 * LoginServlet es un servlet que maneja las solicitudes de inicio de sesión de los usuarios.
 * Procesa las credenciales del usuario y, si son válidas, crea una sesión y redirige al usuario
 * a la página de bienvenida (welcome.jsp). Si las credenciales no son válidas, muestra un mensaje
 * de error en la página de inicio de sesión (index.jsp).
 */
package com.example.ldaprecaptcha;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(LoginServlet.class.getName());

    /**
     * Procesa las solicitudes GET, mostrando la página de inicio de sesión.
     *
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta que se enviará al cliente.
     * @throws ServletException Si ocurre un error relacionado con el servlet.
     * @throws IOException      Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    /**
     * Procesa las solicitudes POST, validando las credenciales del usuario y creando una sesión si
     * las credenciales son válidas.
     *
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta que se enviará al cliente.
     * @throws ServletException Si ocurre un error relacionado con el servlet.
     * @throws IOException      Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (authUser(username, password)) {
            request.getSession().setAttribute("username", username);
            response.sendRedirect("welcome.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid credentials.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        String recaptchaResponse = request.getParameter("g-recaptcha-response");
        if (!verifyRecaptcha(recaptchaResponse)) {
            request.setAttribute("errorMessage", "reCAPTCHA verification failed.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }

    /**
     * Autentica al usuario utilizando sus credenciales proporcionadas y el sistema LDAP.
     *
     * @param userName El nombre de usuario proporcionado por el cliente.
     * @param password La contraseña proporcionada por el cliente.
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    public boolean authUser(String userName, String password) {

        try {
            Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://localhost:389");
            env.put(Context.SECURITY_PRINCIPAL, "cn="+userName+",ou=Users,o=MyOrganization,dc=maxcrc,dc=com");
            env.put(Context.SECURITY_CREDENTIALS, password);
            DirContext con = new InitialDirContext(env);
            System.out.println("success");
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println("failed: " + e.getMessage());
            return false;
        }
    }

    //Recaptcha v3 verification

    private boolean verifyRecaptcha(String recaptchaResponse) {
        try {
            String secretKey = "6LfHyMIlAAAAAKKxKCiq0ndk_MtoizLp28uxeRLb";
            String url = "https://www.google.com/recaptcha/api/siteverify";
            String params = "secret=" + secretKey + "&response=" + recaptchaResponse;

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.getOutputStream().write(params.getBytes());

            InputStream responseStream = connection.getInputStream();
            JsonObject responseJson = Json.createReader(responseStream).readObject();

            if (responseJson.getBoolean("success")) {
                double score = responseJson.getJsonNumber("score").doubleValue();
                return score >= 0.5;
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error verifying reCAPTCHA", e);
        }

        return false;
    }

}
