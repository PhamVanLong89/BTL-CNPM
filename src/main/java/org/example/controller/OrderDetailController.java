package org.example.controller;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import org.apache.commons.math.util.MathUtils;
import org.example.model.*;
import org.example.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "OrderDetailController", urlPatterns = {"/OrderDetail"})
public class OrderDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final OrderDetailService orderDetailService;
    private final ProductService productService;
    private final VariantService variantService;
    private final CustomerService customerService;
    private final OrderService orderService;
    private final AdminService adminService;
    private static final String CONTENT_TYPE = "text/html;charset=UTF-8";
    private static final String QUANTITY = "quantity";

    public OrderDetailController() {
        super();
        orderDetailService = new OrderDetailService();
        productService = new ProductService();
        variantService = new VariantService();
        customerService = new CustomerService();
        orderService = new OrderService();
        adminService = new AdminService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String request = req.getParameter("chucNang");
        try {
            if (request.equals("hienThi")) {
                showCart(req, resp);
                return;
            }
            if (request.equals("xoa")) {
                deleteProductInCart(req);
                showCart(req, resp);
                return;
            }
            if (request.equals("chiTietDonHang")) {
                if (!customerService.checkLogged(req)) {
                    resp.sendRedirect(req.getContextPath() + "/Customer?chucNang=dang-nhap");
                    return;
                }
                showOrderDetailOfCustomer(req, resp);
                return;
            }
            if (!adminService.checkLogged(req)) {
                resp.sendRedirect(req.getContextPath() + "/Admin?chucNang=dang-nhap");
                return;
            }
            if (request.equals("chiTietDonHang2")) {
                showOrderDetail(req, resp);
            }
        } catch (IOException | ServletException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showOrderDetailOfCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        String orderId = req.getParameter("orderId");
        Order order = orderService.getOrderById(orderId);
        if (order == null || order.getCustomer().getCustomerId() != customer.getCustomerId()) {
            resp.sendRedirect(req.getContextPath() + "/Order?chucNang=donHangCuaToi");
            return;
        }
        List<OrderDetail> listOrderDetail = orderDetailService.getOrderDetailByOrderId(Integer.parseInt(orderId));
        req.setAttribute("order", order);
        req.setAttribute("listOrderDetail", listOrderDetail);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/Customer/OrderDetail.jsp");
        dispatcher.forward(req, resp);
    }

    private void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            resp.sendRedirect(req.getContextPath() + "/Order?chucNang=danhSachDonHang");
            return;
        }
        List<OrderDetail> listOrderDetail = orderDetailService.getOrderDetailByOrderId(Integer.parseInt(orderId));
        req.setAttribute("order", order);
        req.setAttribute("listOrderDetail", listOrderDetail);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/Admin/OrderDetail.jsp");
        dispatcher.forward(req, resp);
    }

    private void showCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ArrayList<OrderDetail> cart = (ArrayList<OrderDetail>) session.getAttribute("cart");
        req.setAttribute("cart", cart);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/Customer/Cart.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String request = req.getParameter("chucNang");
        try {
            if (request.equals("them")) {
                if (productService.getProductById(req.getParameter("productId")) == null) {
                    resp.sendRedirect(req.getContextPath() + "/Home");
                    return;
                }
                Map<String, String> errors = orderDetailService.checkAddProductToCart(req);
                if (errors.isEmpty()) {
                    addProductToCart(req, resp);
                    return;
                }
                resp.setContentType(CONTENT_TYPE);
                resp.setContentType("application/json");
                new Gson().toJson(errors, resp.getWriter());
                return;
            }
            if (request.equals("sua")) {
                String sKU = req.getParameter("sKU");
                Variant variant = variantService.getVariantBySKU(sKU);
                String quantity = req.getParameter(QUANTITY);
                String error = orderDetailService.checkQuantity(quantity, variant);
                if (error == null) {
                    editProductInCart(req);
                } else {
                    resp.setContentType(CONTENT_TYPE);
                    resp.getWriter().write(error);
                }
            }
        } catch (IOException | JsonIOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addProductToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));
        String color = req.getParameter("Color");
        String size = req.getParameter("Size");
        int quantity = orderDetailService.convertQuantity(req.getParameter(QUANTITY));
        Variant variant = variantService.getVariantByColorAndSize(size, color, productId);
        variant.setQuantity(quantity);
        Product product = productService.getProductById(String.valueOf(productId));
        HttpSession session = req.getSession();
        ArrayList<OrderDetail> cart = (ArrayList<OrderDetail>) session.getAttribute("cart");
        if (cart != null) {
            //nếu đã có giỏ hàng
            boolean productExists = false;
            for (int i = 0; i < cart.size(); i++) {
                if (variant.getSKU().equals(cart.get(i).getVariant().getSKU())) {
                    //nếu sản phẩm đã có trong giỏ hàng => cập nhật số lương và thành tiền
                    cart.get(i).getVariant().setQuantity(cart.get(i).getVariant().getQuantity() + variant.getQuantity());
                    int sale = cart.get(i).getVariant().getQuantity() * (int) MathUtils.round((product.getProductPrice() * product.getProductSale() / 100.0), -3);
                    int amount = cart.get(i).getVariant().getQuantity() * product.getProductPrice() - sale;
                    cart.get(i).setProduct(product);
                    cart.get(i).setAmount(amount);
                    cart.get(i).setSale(sale);
                    productExists = true;
                    break;
                }
            }
            if (!productExists) {
                // nếu sản phẩm chưa có trong giỏ hàng => thêm sản phẩm vào giỏ hàng
                int sale = variant.getQuantity() * (int) MathUtils.round((product.getProductPrice() * product.getProductSale() / 100.0), -3);
                int amount = variant.getQuantity() * product.getProductPrice() - sale;
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setVariant(variant);
                orderDetail.setAmount(amount);
                orderDetail.setSale(sale);
                cart.add(orderDetail);
            }
        } else {
            //nếu chưa có sản phẩm nào giỏ hàng => tạo giỏ hàng
            cart = new ArrayList<>();
            int sale = variant.getQuantity() * (int) MathUtils.round((product.getProductPrice() * product.getProductSale() / 100.0), -3);
            int amount = variant.getQuantity() * product.getProductPrice() - sale;
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(product);
            orderDetail.setVariant(variant);
            orderDetail.setAmount(amount);
            orderDetail.setSale(sale);
            cart.add(orderDetail);
        }
        session.setAttribute("cart", cart);
        resp.setContentType(CONTENT_TYPE);
        resp.getWriter().write("Thêm sản phẩm vào giỏ hàng thành công");
    }

    private void deleteProductInCart(HttpServletRequest req) {
        String sKU = req.getParameter("SKU");
        HttpSession session = req.getSession();
        ArrayList<OrderDetail> cart = (ArrayList<OrderDetail>) session.getAttribute("cart");
        for (int i = 0; i < cart.size(); i++) {
            if (sKU.equals(cart.get(i).getVariant().getSKU())) {
                cart.remove(i);
                break;
            }
        }
        session.setAttribute("cart", cart);
    }

    private void editProductInCart(HttpServletRequest req) {
        String sKU = req.getParameter("sKU");
        int quantity = Integer.parseInt(req.getParameter(QUANTITY));
        HttpSession session = req.getSession();
        ArrayList<OrderDetail> cart = (ArrayList<OrderDetail>) session.getAttribute("cart");
        for (int i = 0; i < cart.size(); i++) {
            if (sKU.equals(cart.get(i).getVariant().getSKU())) {
                cart.get(i).getVariant().setQuantity(quantity);
                int sale = cart.get(i).getVariant().getQuantity() * (int) MathUtils.round((cart.get(i).getProduct().getProductPrice() * cart.get(i).getProduct().getProductSale() / 100.0), -3);
                int amount = cart.get(i).getVariant().getQuantity() * cart.get(i).getProduct().getProductPrice() - sale;
                cart.get(i).setAmount(amount);
                cart.get(i).setSale(sale);
                break;
            }
        }
        session.setAttribute("cart", cart);
    }
}
