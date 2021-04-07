package org.example.controller;

import org.example.model.Product;
import org.example.model.Product2;
import org.example.service.ProductService;
import org.example.service.VariantService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/Home"})
public class Home extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ProductService productService;

    public Home() {
        super();
        productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            showProduct(req, resp);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> listProduct = productService.getTop6ProductHome();
        List<Product2> listProductFrontEnd = new ArrayList<>();
        for (int i = 0; i < listProduct.size(); i++) {
            Product2 productFrontEnd = productService.convertProductToProductFrontEnd(listProduct.get(i));
            listProductFrontEnd.add(productFrontEnd);
        }
        req.setAttribute("listProduct", listProductFrontEnd);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Customer/Home.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doGet(req, resp);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}