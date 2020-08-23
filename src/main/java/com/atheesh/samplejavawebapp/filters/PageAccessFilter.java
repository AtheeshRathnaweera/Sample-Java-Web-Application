package com.atheesh.samplejavawebapp.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter(urlPatterns = {"/*"})
public class PageAccessFilter implements Filter {

    private static ArrayList<String> filterPagesList = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        createTheServletNames();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        String requestPath = request.getServletPath();

        String accessToken = (String) session.getAttribute("accesstoken");
        String userEmail = (String) session.getAttribute("useremail");

        if (checkIfTheRequestIsInside(requestPath)) {
            if (accessToken == null || userEmail == null) {
                System.out.println("access denied. Not Logged In.");
                session.invalidate();
                response.sendRedirect(request.getContextPath() + "/login");
            }else{
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }


    }

    @Override
    public void destroy() {

    }

    private boolean checkIfTheRequestIsInside(String requestPath) {
        String mainPath = requestPath.split("/")[1];
        return filterPagesList.contains(mainPath);
    }

    private void createTheServletNames() {
        filterPagesList.add("products");
    }

}
