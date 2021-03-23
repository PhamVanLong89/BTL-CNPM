package org.example.controller;

import org.example.service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/Manage"})
public class Manage extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final AdminService adminService;

    public Manage() {
        super();
        adminService = new AdminService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (adminService.checkLogged(req)) {
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Admin/Admin.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (IOException | ServletException ex) {
                Logger.getLogger(Manage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                resp.sendRedirect(req.getContextPath() + "/Admin?chucNang=dang-nhap");
            } catch (IOException ex) {
                Logger.getLogger(Manage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doGet(req, resp);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(Manage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

