package org.example.controller;

import org.example.model.Customer;
import org.example.model.Customer2;
import org.example.service.AdminService;
import org.example.service.CustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/Customer"})
@MultipartConfig
public class CustomerController extends HttpServlet {

    private final CustomerService customerService;
    private final AdminService adminService;
    private static final String MESSAGE = "message";
    private static final String ERRORS = "errors";
    private static final String CUSTOMER = "customer";
    private static final String ERROR = "error";
    private static final String LINK_ADMIN_LOGIN = "/Admin?chucNang=dang-nhap";
    private static final String LINK_CUSTOMER_LOGIN = "/Customer?chucNang=dang-nhap";
    private static final String VIEW_CUSTOMER_LOGIN = "/Customer/Login.jsp";
    private static final String VIEW_CUSTOMER_REGISTER = "/Customer/Register.jsp";
    private static final String VIEW_CHANGE_PASSWORD = "/Customer/ChangePassword.jsp";
    private static final String VIEW_EDIT_CUSTOMER = "/Customer/EditCustomer.jsp";
    private static final String CUSTOMER_NAME = "customerName";
    private static final String NUMBER_PHONE = "numberPhone";
    private static final String ADDRESS = "address";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String CONFIRM_PASSWORD = "confirmPassword";
    private static final String NEW_PASSWORD = "newPassword";
    private static final String CUSTOMER_ID = "customerId";
    private static final String CONTENT_TYPE = "text/html;charset=UTF-8";

    public CustomerController() {
        super();
        customerService = new CustomerService();
        adminService = new AdminService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String request = req.getParameter("chucNang");
        try {
            if (request.equals("dang-ky")) {
                showViewRegister(req, resp);
                return;
            }
            if (request.equals("dang-nhap")) {
                showViewLogin(req, resp);
                return;
            }
            if (request.equals("dang-xuat")) {
                logOut(req, resp);
                return;
            }
            if (request.equals("sua")) {
                if (!customerService.checkLogged(req)) {
                    resp.sendRedirect(req.getContextPath() + LINK_CUSTOMER_LOGIN);
                    return;
                }
                showViewUpdate(req, resp);
                return;
            }
            if (request.equals("doi-mat-khau")) {
                if (!customerService.checkLogged(req)) {
                    resp.sendRedirect(req.getContextPath() + LINK_CUSTOMER_LOGIN);
                    return;
                }
                showViewChangePassword(req, resp);
                return;
            }
            if (!adminService.checkLogged(req)) {
                resp.sendRedirect(req.getContextPath() + LINK_ADMIN_LOGIN);
                return;
            }
            if (request.equals("hien-thi")) {
                showCustomer(req, resp);
                return;
            }
            if (request.equals("adminSearch")) {
                searchCustomer(req, resp);
                return;
            }
            if (request.equals("hienThiChiTiet")) {
                showCustomerDetail(req, resp);
            }
        } catch (IOException |
                ServletException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showViewRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(VIEW_CUSTOMER_REGISTER);
        dispatcher.forward(req, resp);
    }

    private void showViewLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(VIEW_CUSTOMER_LOGIN);
        dispatcher.forward(req, resp);
    }

    private void logOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        resp.sendRedirect(req.getContextPath() + "/");
    }

    private void showCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> listCustomer = customerService.getCustomers();
        req.setAttribute("listCustomer", listCustomer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Admin/CustomerView.jsp");
        dispatcher.forward(req, resp);
    }

    private void searchCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerName = req.getParameter(CUSTOMER_NAME);
        List<Customer> listCustomer = customerService.searchCustomer(customerName);
        req.setAttribute("listCustomer", listCustomer);
        req.setAttribute(CUSTOMER_NAME, customerName);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Admin/CustomerView.jsp");
        dispatcher.forward(req, resp);
    }

    private void showViewUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Customer customerLogin = (Customer) session.getAttribute("user");
        Customer customer = customerService.getCustomerById(String.valueOf(customerLogin.getCustomerId()));
        req.setAttribute(CUSTOMER, customer);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(VIEW_EDIT_CUSTOMER);
        dispatcher.forward(req, resp);
    }

    private void showCustomerDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = customerService.getCustomerById(req.getParameter(CUSTOMER_ID));
        if (customer != null) {
            req.setAttribute(CUSTOMER, customer);
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/Admin/CustomerDetail.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/Customer?chucNang=hien-thi");
        }
    }

    private void showViewChangePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(VIEW_CHANGE_PASSWORD);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String request = req.getParameter("chucNang");
        try {
            Customer2 customerFrontEnd = new Customer2();
            switch (request) {
                case "dang-ky":
                    Map<String, String> errors = customerService.checkRegister(req);
                    if (errors.isEmpty()) {
                        register(req, resp);
                        return;
                    }
                    customerFrontEnd.setCustomerName(req.getParameter(CUSTOMER_NAME));
                    customerFrontEnd.setNumberPhone(req.getParameter(NUMBER_PHONE));
                    customerFrontEnd.setAddress(req.getParameter(ADDRESS));
                    customerFrontEnd.setEmail(req.getParameter(EMAIL));
                    customerFrontEnd.setPassword(req.getParameter(PASSWORD));
                    customerFrontEnd.setConfirmPassword(req.getParameter(CONFIRM_PASSWORD));
                    req.setAttribute(CUSTOMER, customerFrontEnd);
                    req.setAttribute(ERRORS, errors);
                    RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(VIEW_CUSTOMER_REGISTER);
                    dispatcher.forward(req, resp);
                    return;
                case "dang-nhap":
                    login(req, resp);
                    return;
                case "change-status":
                    if (!adminService.checkLogged(req)) {
                        resp.sendRedirect(req.getContextPath() + LINK_ADMIN_LOGIN);
                        return;
                    }
                    String error = customerService.checkStatusAccount(req);
                    if (error == null) {
                        changeStatusAccount(req, resp);
                        return;
                    }
                    resp.setContentType(CONTENT_TYPE);
                    resp.getWriter().write("fail");
                    return;
                default:
            }

            if (!customerService.checkLogged(req)) {
                resp.sendRedirect(req.getContextPath() + LINK_CUSTOMER_LOGIN);
                return;
            }
            if (request.equals("sua")) {
                Map<String, String> errors = customerService.checkUpdateUser(req);
                if (errors.isEmpty()) {
                    updateUser(req, resp);
                    return;
                }
                customerFrontEnd = new Customer2();
                customerFrontEnd.setCustomerName(req.getParameter(CUSTOMER_NAME));
                customerFrontEnd.setNumberPhone(req.getParameter(NUMBER_PHONE));
                customerFrontEnd.setAddress(req.getParameter(ADDRESS));
                customerFrontEnd.setEmail(req.getParameter(EMAIL));
                req.setAttribute(ERRORS, errors);
                req.setAttribute(CUSTOMER, customerFrontEnd);
                RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(VIEW_EDIT_CUSTOMER);
                dispatcher.forward(req, resp);
                return;
            }
            if (request.equals("doi-mat-khau")) {
                Map<String, String> errors = customerService.checkChangePassword(req);
                if (errors.isEmpty()) {
                    changePassword(req, resp);
                    return;
                }
                customerFrontEnd = new Customer2();
                customerFrontEnd.setPassword(req.getParameter("currentPassword"));
                customerFrontEnd.setNewPassword(req.getParameter(NEW_PASSWORD));
                customerFrontEnd.setConfirmPassword(req.getParameter(CONFIRM_PASSWORD));
                req.setAttribute(ERRORS, errors);
                req.setAttribute(CUSTOMER, customerFrontEnd);
                RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(VIEW_CHANGE_PASSWORD);
                dispatcher.forward(req, resp);
            }
        } catch (IOException | ServletException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = new Customer();
        customer.setCustomerName(req.getParameter(CUSTOMER_NAME));
        customer.setNumberPhone(req.getParameter(NUMBER_PHONE));
        customer.setAddress(req.getParameter(ADDRESS));
        customer.setEmail(req.getParameter(EMAIL));
        customer.setPassword(customerService.md5(req.getParameter(PASSWORD)));
        customer.setStatusActive(1);
        int rowAffected = customerService.insertUser(customer);
        if (rowAffected == 1) {
            req.setAttribute(MESSAGE, "Đăng ký tài khoản thành công");
        } else {
            req.setAttribute(MESSAGE, "Đăng ký tài khoản thất bại");
            req.setAttribute(CUSTOMER, customer);
        }
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(VIEW_CUSTOMER_REGISTER);
        dispatcher.forward(req, resp);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = new Customer();
        customer.setEmail(req.getParameter(EMAIL));
        customer.setPassword(customerService.md5(req.getParameter(PASSWORD)));
        Customer customerLogin = customerService.checkLogin(customer);
        if (customerLogin == null) {
            req.setAttribute(CUSTOMER, customer);
            req.setAttribute(ERROR, "Tên tài khoản hoặc mật khẩu không chính xác");
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(VIEW_CUSTOMER_LOGIN);
            dispatcher.forward(req, resp);
        } else {
            if (customerLogin.getStatusActive() == 0) {
                customer.setPassword(req.getParameter(PASSWORD));
                req.setAttribute(CUSTOMER, customer);
                req.setAttribute(ERROR, "Tài khoản của bạn đã bị khóa");
                RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(VIEW_CUSTOMER_LOGIN);
                dispatcher.forward(req, resp);
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("user", customerLogin);
                resp.sendRedirect(req.getContextPath() + "/");
            }
        }
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Customer customerLogin = (Customer) session.getAttribute("user");
        Customer customer = new Customer();
        customer.setCustomerId(customerLogin.getCustomerId());
        customer.setCustomerName(req.getParameter(CUSTOMER_NAME));
        customer.setNumberPhone(req.getParameter(NUMBER_PHONE));
        customer.setAddress(req.getParameter(ADDRESS));
        int rowAffected = customerService.updateCustomer(customer);
        if (rowAffected == 1) {
            req.setAttribute(MESSAGE, "Sửa thông tin tài khoản thành công");
            session.setAttribute("user", customer);
        } else {
            req.setAttribute(MESSAGE, "Sửa thông tin tài khoản thất bại");
        }
        req.setAttribute(CUSTOMER, customer);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(VIEW_EDIT_CUSTOMER);
        dispatcher.forward(req, resp);
    }

    private void changePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Customer customerLogin = (Customer) session.getAttribute("user");
        Customer customer = new Customer();
        customer.setCustomerId(customerLogin.getCustomerId());
        customer.setPassword(customerService.md5(req.getParameter(NEW_PASSWORD)));
        int rowAffected = customerService.changePassword(customer);
        if (rowAffected == 1) {
            req.setAttribute(MESSAGE, "Đổi mật khẩu thành công");
        } else {
            Customer2 customerFrontEnd = new Customer2();
            customerFrontEnd.setPassword(req.getParameter("currentPassword"));
            customerFrontEnd.setNewPassword(req.getParameter(NEW_PASSWORD));
            customerFrontEnd.setConfirmPassword(req.getParameter(CONFIRM_PASSWORD));
            req.setAttribute(MESSAGE, "Đổi mật khẩu thất bại");
            req.setAttribute(CUSTOMER, customerFrontEnd);
        }
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(VIEW_CHANGE_PASSWORD);
        dispatcher.forward(req, resp);
    }

    private void changeStatusAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType(CONTENT_TYPE);
        Customer customer = customerService.getCustomerById(req.getParameter(CUSTOMER_ID));
        if (customer == null) {
            resp.getWriter().write("fail");
            return;
        }
        customer.setStatusActive(Integer.parseInt(req.getParameter("statusActive")));
        int rowAffected = customerService.changeStatusAccount(customer);
        if (rowAffected == 1) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("fail");
        }
    }
}
