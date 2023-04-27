package com.example.ldaprecaptcha;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Invalida la sesión del usuario
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Redirige al usuario a la página de inicio de sesión (index.jsp)
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}