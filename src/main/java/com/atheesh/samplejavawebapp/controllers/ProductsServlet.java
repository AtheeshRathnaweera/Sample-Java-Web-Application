package com.atheesh.samplejavawebapp.controllers;

import com.atheesh.samplejavawebapp.beans.Product;
import com.atheesh.samplejavawebapp.services.ProductService;
import com.atheesh.samplejavawebapp.services.TokenManagementService;
import com.atheesh.samplejavawebapp.services.impl.ProductServiceImpl;
import com.atheesh.samplejavawebapp.services.impl.TokenManagementServiceImpl;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ProductsServlet", urlPatterns = {"/products"})
public class ProductsServlet extends HttpServlet {

    private String authToken;

    private void setTheAuthToken(HttpServletRequest request) {
        TokenManagementService tokenManagementService = new TokenManagementServiceImpl();
        authToken = tokenManagementService.getTheTokenInSession(request);
    }

    public ProductsServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        setTheAuthToken(req);
        handleTheGetRequest(req.getServletPath(), req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        setTheAuthToken(req);
        handleThePostRequest(req, resp);
    }

    //handle the get request
    private void handleTheGetRequest(String path, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        HttpSession session = req.getSession();
//        Integer currentUserid = (Integer) session.getAttribute("userid");

//        ProductService productService = new ProductServiceImpl(authToken);

        switch (path) {
            case "/products":
//                List<Product> allProductsList = productService.getAll();

                List<Product> allProductsList = new ArrayList<>();
                allProductsList.add(new Product(1,"Product One"));
                allProductsList.add(new Product(2,"Product Two"));

                req.setAttribute("allProductsList", allProductsList);

                RequestDispatcher productDis = req.getServletContext().getRequestDispatcher("/WEB-INF/views/products.jsp");
                productDis.forward(req, resp);
                break;
            default:
                System.out.println("unknown get request in products");
                break;
        }


    }
    //handle the get request


    private void handleThePostRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String action = req.getParameter("action");

        boolean actionResult;
        String actionCompleteStatus;

        switch (action) {
            case "save":
                actionResult = saveAProduct(req);

                if (actionResult) {
                    actionCompleteStatus = "saved";
                } else {
                    actionCompleteStatus = "saveFailed";
                }
                resp.sendRedirect(req.getContextPath() + "/products?status=" + actionCompleteStatus);
                break;
            default:
                System.out.println("invalid product action");
                break;
        }
    }

    private boolean saveAProduct(HttpServletRequest req) {
        String name = req.getParameter("name");
        System.out.println("New product name : "+name);
        return true;
    }
}
