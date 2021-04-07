package org.example.controller;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import org.example.model.Customer;
import org.example.model.Order;
import org.example.model.OrderDetail;
import org.example.service.AdminService;
import org.example.service.CustomerService;
import org.example.service.OrderDetailService;
import org.example.service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "OrderController", urlPatterns = {"/Order"})
public class OrderController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "text/html;charset=UTF-8";
    private final CustomerService customerService;
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;
    private final AdminService adminService;
    private static final String ORDER_STATUS = "orderStatus";

    public OrderController() {
        super();
        customerService = new CustomerService();
        orderService = new OrderService();
        orderDetailService = new OrderDetailService();
        adminService = new AdminService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String request = req.getParameter("chucNang");
        try {
            if (request.equals("donHangCuaToi")) {
                if (!customerService.checkLogged(req)) {
                    resp.sendRedirect(req.getContextPath() + "/Customer?chucNang=dang-nhap");
                    return;
                }
                showOrderOfCustomer(req, resp);
                return;
            }
            if (!adminService.checkLogged(req)) {
                resp.sendRedirect(req.getContextPath() + "/Admin?chucNang=dang-nhap");
                return;
            }
            if (request.equals("danhSachDonHang")) {
                showAllOrder(req, resp);
            }
        } catch (IOException | JsonIOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showOrderOfCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        List<Order> listOrder = orderService.getOrderByCustomerId(customer.getCustomerId());
        req.setAttribute("listOrder", listOrder);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Customer/ListOrder.jsp");
        dispatcher.forward(req, resp);
    }

    private void showAllOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> listOrder = orderService.getOrder();
        req.setAttribute("listOrder", listOrder);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Admin/ListOrder.jsp");
        dispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE);
        String request = req.getParameter("chucNang");
        try {
            if (request.equals("suaTrangThai")) {
                if (!adminService.checkLogged(req)) {
                    resp.setContentType(CONTENT_TYPE);
                    resp.getWriter().write("fail");
                    return;
                }
                String error = orderService.checkOrderStatus(req.getParameter(ORDER_STATUS));
                if (error == null) {
                    changeOrderStatus(req, resp);
                    return;
                }
                resp.setContentType(CONTENT_TYPE);
                resp.getWriter().write("fail");
                return;
            }
            if (!customerService.checkLogged(req)) {
                resp.getWriter().write("Vui lòng đăng nhập");
                return;
            }
            if (request.equals("them")) {
                Map<String, String> errors = orderService.checkOrder(req);
                if (errors.isEmpty()) {
                    order(req, resp);
                    return;
                }
                resp.setContentType("application/json");
                new Gson().toJson(errors, resp.getWriter());
                return;
            }
            if (request.equals("huyDonHang")) {
                String error = orderService.checkCancelOrder(req.getParameter(ORDER_STATUS));
                if (error == null) {
                    cancelOrder(req, resp);
                    return;
                }
                resp.setContentType(CONTENT_TYPE);
                resp.getWriter().write("fail");
            }
        } catch (IOException | JsonIOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void order(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        Order order = new Order();
        order.setOrderDate(Date.valueOf(java.time.LocalDate.now()));
        order.setOrderAddress(req.getParameter("addressOrder"));
        order.setOrderNumberPhone(req.getParameter("numberPhoneOrder"));
        order.setPaymentsMethod(req.getParameter("paymentMethod"));
        order.setOrderStatus("Chờ xác nhận");
        order.setCustomer(customer);
        int rowAffected = orderService.insertOrder(order);
        if (rowAffected == 1) {
            int orderId = orderService.getOrderIdNew();
            ArrayList<OrderDetail> cart = (ArrayList<OrderDetail>) session.getAttribute("cart");
            for (int i = 0; i < cart.size(); i++) {
                cart.get(i).setOrderId(orderId);
                orderDetailService.insertOrderDetail(cart.get(i));
            }
            session.removeAttribute("cart");
            resp.setContentType(CONTENT_TYPE);
            resp.getWriter().write("Đặt hàng thành công. Vui lòng xem lại thông tin đơn hàng");
        } else {
            resp.setContentType(CONTENT_TYPE);
            resp.getWriter().write("Đặt hàng thất bại");
        }
    }

    public void changeOrderStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType(CONTENT_TYPE);
        Order order = new Order();
        order.setOrderId(Integer.parseInt(req.getParameter("orderId")));
        order.setOrderStatus(req.getParameter(ORDER_STATUS));
        int rowAffected = orderService.changeOrderStatus(order);
        if (rowAffected == 1) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("fail");
        }
    }

    public void cancelOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType(CONTENT_TYPE);
        Order order = new Order();
        order.setOrderId(Integer.parseInt(req.getParameter("orderId")));
        order.setOrderStatus(req.getParameter(ORDER_STATUS));
        int rowAffected = orderService.cancelOrder(order);
        if (rowAffected == 1) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("fail");
        }
    }

}

