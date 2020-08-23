package com.atheesh.samplejavawebapp.controllers;

import com.atheesh.samplejavawebapp.beans.AccessTokenRequest;
import com.atheesh.samplejavawebapp.services.AuthenticationService;
import com.atheesh.samplejavawebapp.services.impl.AuthenticationServiceImpl;
import com.auth0.jwt.exceptions.TokenExpiredException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LogInLogOutServlet", urlPatterns = {"/login", "/logout",})
public class LogInLogOutServlet extends HttpServlet {

    public LogInLogOutServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        HttpSession session = request.getSession();

        switch (path) {
            case "/login":
                System.out.println("login get started.");
                RequestDispatcher indexDis = request.getServletContext().getRequestDispatcher("/index.jsp");
                indexDis.forward(request, response);
                break;
            case "/logout":
                System.out.println("log out get started.");
                session.invalidate();
                RequestDispatcher logOutDis = request.getServletContext().getRequestDispatcher("/index.jsp");
                logOutDis.forward(request, response);
            default:
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String path = request.getServletPath();

        // handle the post requests
        switch (path) {
            case "/login":
                String userEmail = request.getParameter("email");
                String userPassword = request.getParameter("password");

                System.out.println("received email : " + userEmail + " " + userPassword);

                //check the password and email
                if (userEmail.equals("admin@test.com") && userPassword.equals("12345")) {
                    System.out.println("user auth success");

                    //get the authentication token
//                    String authToken = getTheAuthToken(userEmail, userPassword);

                    //store data in the session
                    session.setAttribute("accesstoken", "5%87*&123456");
                    session.setAttribute("useremail", userEmail);
                    //store data in the session

                    response.sendRedirect(request.getContextPath() + "/products");
                } else {
                    System.out.println("user auth failed");

                    session.invalidate();
                    RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                }

                break;
            default:
                break;

        }
    }

    private String getTheAuthToken(String email, String password) throws TokenExpiredException {
        AuthenticationService authenticationService = new AuthenticationServiceImpl("");
        AccessTokenRequest accessTokenRequest = new AccessTokenRequest(email, password);
        return authenticationService.getAuthToken(accessTokenRequest);
    }


}
