/**
 * La clase LogoutServlet es un servlet que maneja la funcionalidad de cerrar sesión.
 * Se encarga de invalidar la sesión del usuario y redirigirlo a la página de inicio de sesión (index.jsp).
 */
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

    /**
     * Maneja las solicitudes GET para cerrar la sesión del usuario.
     *
     * @param request  Objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param resp     Objeto HttpServletResponse que contiene la respuesta del servidor.
     * @throws ServletException Si ocurre un error relacionado con el servlet.
     * @throws IOException      Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        // Invalida la sesión del usuario
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Redirige al usuario a la página de inicio de sesión (index.jsp)
        resp.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
