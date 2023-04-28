/**
 * AuthenticationFilter es un filtro que asegura que solo los usuarios autenticados puedan acceder
 * a ciertas páginas de la aplicación. En este caso, se aplica a la página welcome.jsp.
 * Si un usuario no autenticado intenta acceder a la página protegida, será redirigido a la página
 * de inicio de sesión (index.jsp).
 */
package com.example.ldaprecaptcha;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/welcome.jsp"})
public class AuthenticationFilter implements Filter {

    /**
     * Filtra las solicitudes y respuestas para asegurarse de que solo los usuarios autenticados
     * puedan acceder a las páginas protegidas.
     *
     * @param request  El objeto ServletRequest que contiene la solicitud del cliente.
     * @param response El objeto ServletResponse que contiene la respuesta que se enviará al cliente.
     * @param chain    El objeto FilterChain que se utiliza para invocar al siguiente filtro en la cadena.
     * @throws IOException      Si ocurre un error de entrada/salida.
     * @throws ServletException Si ocurre un error relacionado con el servlet.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("username") == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }

    /**
     * Método de inicialización vacío para cumplir con la interfaz Filter.
     *
     * @param filterConfig La configuración del filtro proporcionada por el contenedor de servlets.
     */
    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * Método de destrucción vacío para cumplir con la interfaz Filter.
     */
    @Override
    public void destroy() {
    }
}
