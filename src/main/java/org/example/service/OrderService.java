package org.example.service;

import org.example.model.Customer;
import org.example.model.Order;
import org.example.model.OrderDetail;
import org.example.repository.OrderRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class OrderService {

    private OrderRepository orderRepository;
    private final CustomerService customerService;

    public OrderService() {
        orderRepository = new OrderRepository();
        customerService = new CustomerService();
    }

    public Map<String, String> checkOrder(HttpServletRequest req) {
        Map<String, String> errors = new HashMap<>();
        String errorNumberPhone = checkNumberPhone(req.getParameter("numberPhoneOrder"));
        if (errorNumberPhone != null) {
            errors.put("errorNumberPhone", errorNumberPhone);
        }
        String errorAddress = checkAddress(req.getParameter("addressOrder"));
        if (errorAddress != null) {
            errors.put("errorAddress", errorAddress);
        }
        String errorPayment = checkPaymentMethod(req.getParameter("paymentMethod"));
        if (errorPayment != null) {
            errors.put("errorPayment", errorPayment);
        }
        String errorCart = checkCart(req);
        if (errorCart != null) {
            errors.put("errorCart", errorCart);
        }
        return errors;
    }

    public String checkAddress(String address) {
        if (address.trim().length() == 0) {
            return "Vui lòng nhập địa chỉ";
        } else if (address.length() > 100) {
            return "Địa chỉ dài quá giới hạn(100 ký tự)";
        } else {
            return null;
        }
    }

    public String checkNumberPhone(String numberPhone) {
        String numberPhonePattern1 = "^[0]{1}[0-9]{9}$";
        String numberPhonePattern2 = "^[+]{1}[8]{1}[4]{1}[0-9]{9}$";
        if (numberPhone.trim().length() == 0) {
            return "Vui lòng nhập số điện thoại";
        } else if (!Pattern.matches(numberPhonePattern1, numberPhone) && !Pattern.matches(numberPhonePattern2, numberPhone)) {
            return "Số điện thoại không hợp lệ";
        } else {
            return null;
        }
    }

    public String checkPaymentMethod(String paymentMethod) {
        if (paymentMethod == null || (!paymentMethod.equals("COD") && !paymentMethod.equals("MoMo") && !paymentMethod.equals("ZaloPay"))) {
            return "Vui lòng chọn phương thức thanh toán";
        }
        return null;
    }

    public String checkCart(HttpServletRequest req) {
        HttpSession session = req.getSession();
        ArrayList<OrderDetail> cart = (ArrayList<OrderDetail>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            return "Giỏ hàng trống";
        }
        return null;
    }

    public String checkOrderStatus(String orderStatus) {
        if (!orderStatus.equals("Chờ xác nhận") && !orderStatus.equals("Đang giao") && !orderStatus.equals("Đã giao")) {
            return "Trạng thái đơn hàng không hợp lệ";
        }
        return null;
    }

    public String checkCancelOrder(String orderStatus) {
        if (!orderStatus.equals("Đã hủy")) {
            return "Vui lòng chọn hủy đơn hàng";
        }
        return null;
    }

    public int insertOrder(Order order) {
        return orderRepository.insertOrder(order);
    }

    public int getOrderIdNew() {
        return orderRepository.getOrderIdNew();
    }

    public List<Order> getOrderByCustomerId(int customerId) {
        return orderRepository.getOrderByCustomerId(customerId);
    }

    public Order getOrderById(String orderId) {
        try {
            Order order = orderRepository.getOrderById(Integer.parseInt(orderId));
            Customer customer = customerService.getCustomerById(String.valueOf(order.getCustomer().getCustomerId()));
            order.setCustomer(customer);
            return order;
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Order> getOrder() {
        List<Order> listOrder = orderRepository.getOrder();
        for (Order order : listOrder) {
            Customer customer = customerService.getCustomerById(String.valueOf(order.getCustomer().getCustomerId()));
            order.setCustomer(customer);
        }
        return listOrder;
    }

    public int changeOrderStatus(Order order) {
        return orderRepository.changeOrderStatus(order);
    }

    public int cancelOrder(Order order) {
        return orderRepository.changeOrderStatus(order);
    }
}
