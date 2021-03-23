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
    private final VariantService variantService;

    public Home() {
        super();
        productService = new ProductService();
        variantService = new VariantService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Product> listProduct = productService.getTop6ProductHome();
            List<Product2> listProductFrontEnd = new ArrayList<>();
            for (int i = 0; i < listProduct.size(); i++) {
                Product2 productFrontEnd = new Product2();
                productFrontEnd.setProductId(String.valueOf(listProduct.get(i).getProductId()));
                productFrontEnd.setProductName(listProduct.get(i).getProductName());
                productFrontEnd.setCategoryName(listProduct.get(i).getCategoryName());
                productFrontEnd.setProductPrice(String.valueOf(listProduct.get(i).getProductPrice()));
                productFrontEnd.setProductSale(String.valueOf(listProduct.get(i).getProductSale()));
                productFrontEnd.setDisplayHome(String.valueOf(listProduct.get(i).getDisplayHome()));
                productFrontEnd.setSaleDate(String.valueOf(listProduct.get(i).getSaleDate()));
                productFrontEnd.setAdminId(String.valueOf(listProduct.get(i).getAdminId()));
                productFrontEnd.setImage(variantService.getOneImageVariantByProductId(listProduct.get(i).getProductId()));
                listProductFrontEnd.add(productFrontEnd);
            }
            req.setAttribute("listProduct", listProductFrontEnd);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Customer/Home.jsp");
            dispatcher.forward(req, resp);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
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