//package com.example.ldaprecaptcha;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.*;
//import jakarta.servlet.annotation.*;
//import java.io.IOException;
//import jakarta.inject.Inject;
//
//@WebServlet("/login")
//public class LoginServlet extends HttpServlet {
//
//    private AuthenticationService authenticationService;
//
//    @Override
//    public void init() throws ServletException {
//        authenticationService = new AuthenticationService();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/index.jsp").forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        if (authenticationService.authenticate(username, password)) {
//            request.getSession().setAttribute("username", username);
//            response.sendRedirect("welcome.jsp");
//        } else {
//            request.setAttribute("errorMessage", "Invalid credentials.");
//            request.getRequestDispatcher("index.jsp").forward(request, response);
//        }
//    }
//}