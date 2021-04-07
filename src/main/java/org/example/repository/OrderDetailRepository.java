package org.example.repository;

import org.example.model.Mysql;
import org.example.model.OrderDetail;
import org.example.model.Product;
import org.example.model.Variant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDetailRepository {
    private final Mysql mysql;

    public OrderDetailRepository() {
        mysql = new Mysql();
    }

    public int insertOrderDetail(OrderDetail orderDetail) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        if (conn != null) {
            try {
                ps = conn.prepareStatement("INSERT INTO OrderDetails(OrderId, OrderDetailId, SKU, Quantity, Price, Sale) values(?,?,?,?,?,?)");
                ps.setInt(1, orderDetail.getOrderId());
                ps.setInt(2, orderDetail.getOrderDetailId());
                ps.setString(3, orderDetail.getVariant().getSKU());
                ps.setInt(4, orderDetail.getVariant().getQuantity());
                ps.setInt(5, orderDetail.getProduct().getProductPrice());
                ps.setInt(6, orderDetail.getProduct().getProductSale());
                return ps.executeUpdate();
            } catch (SQLException ex) {
                return 0;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(OrderDetailRepository.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDetailRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }

    public List<OrderDetail> getOrderDetailByOrderId(int orderId) {
        ArrayList<OrderDetail> listOrderDetail = new ArrayList<>();
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return listOrderDetail;
        }
        try {
            String sql = "SELECT OrderId, OrderDetailId, SKU, Quantity, Price, Sale, tinhThanhTien(OrderDetailId) FROM OrderDetails WHERE OrderId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail();
                Variant variant = new Variant();
                Product product = new Product();
                orderDetail.setOrderId(rs.getInt("OrderId"));
                orderDetail.setOrderId(rs.getInt("OrderDetailId"));
                variant.setSKU(rs.getString("SKU"));
                variant.setQuantity(rs.getInt("Quantity"));
                orderDetail.setVariant(variant);
                product.setProductPrice(rs.getInt("Price"));
                product.setProductSale(rs.getInt("Sale"));
                orderDetail.setProduct(product);
                orderDetail.setAmount(rs.getInt("tinhThanhTien(OrderDetailId)"));
                listOrderDetail.add(orderDetail);
            }
        } catch (SQLException ex) {
            return listOrderDetail;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDetailRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDetailRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(OrderDetailRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listOrderDetail;
    }
}
