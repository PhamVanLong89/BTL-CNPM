package org.example.repository;

import org.example.model.Customer;
import org.example.model.Mysql;
import org.example.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderRepository {
    private final Mysql mysql;
    private static final String ORDER_ID = "OrderId";
    private static final String ORDER_DATE = "OrderDate";
    private static final String USER_ID = "UserId";
    private static final String ORDER_STATUS = "OrderStatus";
    private static final String TOTAL_PRODUCT = "tinhTongSanPham(OrderId)";
    private static final String TOTAL_PRICE = "tinhTongTien(OrderId)";
    private static final String PAYMENT_METHOD = "PaymentsMethod";
    private static final String ORDER_ADDRESS = "OrderAddress";
    private static final String ORDER_NUMBER_PHONE = "OrderNumberPhone";

    public OrderRepository() {
        mysql = new Mysql();
    }

    public int insertOrder(Order order) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        if (conn != null) {
            try {
                ps = conn.prepareStatement("INSERT INTO Orders(OrderDate, UserId, OrderStatus, PaymentsMethod, OrderAddress, OrderNumberPhone) values(?,?,?,?,?,?)");
                ps.setDate(1, order.getOrderDate());
                ps.setInt(2, order.getCustomer().getCustomerId());
                ps.setString(3, order.getOrderStatus());
                ps.setString(4, order.getPaymentsMethod());
                ps.setString(5, order.getOrderAddress());
                ps.setString(6, order.getOrderNumberPhone());
                return ps.executeUpdate();
            } catch (SQLException ex) {
                return 0;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }

    public int getOrderIdNew() {
        Connection conn = mysql.getConnect();
        Statement stmt = null;
        ResultSet rs = null;
        if (conn == null) {
            return 0;
        }
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT MAX(OrderId) FROM Orders");
            if (rs.next()) {
                return rs.getInt("MAX(OrderId)");
            }
        } catch (SQLException ex) {
            return 0;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public List<Order> getOrderByCustomerId(int customerId) {
        List<Order> listOrder = new ArrayList<>();
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return listOrder;
        }
        try {
            String sqlQuery = "SELECT OrderId, OrderDate, UserId, OrderStatus, tinhTongSanPham(OrderId), tinhTongTien(OrderId) FROM Orders WHERE UserId = ?";
            ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                Customer customer = new Customer();
                order.setOrderId(rs.getInt(ORDER_ID));
                order.setOrderDate(rs.getDate(ORDER_DATE));
                customer.setCustomerId(rs.getInt(USER_ID));
                order.setCustomer(customer);
                order.setOrderStatus(rs.getString(ORDER_STATUS));
                order.setTotalProduct(rs.getInt(TOTAL_PRODUCT));
                order.setTotalPrice(rs.getInt(TOTAL_PRICE));
                listOrder.add(order);
            }
        } catch (SQLException ex) {
            return listOrder;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listOrder;
    }

    public Order getOrderById(int orderId) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return null;
        }
        try {
            String sqlQuery = "SELECT OrderId, OrderDate, UserId, OrderStatus, PaymentsMethod, tinhTongSanPham(OrderId), tinhTongTien(OrderId) FROM Orders WHERE OrderId = ?";
            ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            if (rs.next()) {
                Order order = new Order();
                Customer customer = new Customer();
                order.setOrderId(rs.getInt(ORDER_ID));
                order.setOrderDate(rs.getDate(ORDER_DATE));
                customer.setCustomerId(rs.getInt(USER_ID));
                order.setCustomer(customer);
                order.setOrderStatus(rs.getString(ORDER_STATUS));
                order.setPaymentsMethod(rs.getString(PAYMENT_METHOD));
                order.setTotalProduct(rs.getInt(TOTAL_PRODUCT));
                order.setTotalPrice(rs.getInt(TOTAL_PRICE));
                return order;
            }
        } catch (SQLException ex) {
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public List<Order> getOrder() {
        List<Order> listOrder = new ArrayList<>();
        Connection conn = mysql.getConnect();
        Statement stmt = null;
        ResultSet rs = null;
        if (conn == null) {
            return listOrder;
        }
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT OrderId, OrderDate, UserId, OrderStatus, PaymentsMethod, OrderAddress, OrderNumberPhone, tinhTongSanPham(OrderId), tinhTongTien(OrderId) FROM Orders");
            while (rs.next()) {
                Order order = new Order();
                Customer customer = new Customer();
                order.setOrderId(rs.getInt(ORDER_ID));
                order.setOrderDate(rs.getDate(ORDER_DATE));
                customer.setCustomerId(rs.getInt(USER_ID));
                order.setCustomer(customer);
                order.setOrderStatus(rs.getString(ORDER_STATUS));
                order.setPaymentsMethod(rs.getString(PAYMENT_METHOD));
                order.setOrderAddress(rs.getString(ORDER_ADDRESS));
                order.setOrderNumberPhone(rs.getString(ORDER_NUMBER_PHONE));
                order.setTotalProduct(rs.getInt(TOTAL_PRODUCT));
                order.setTotalPrice(rs.getInt(TOTAL_PRICE));
                listOrder.add(order);
            }
        } catch (SQLException ex) {
            return listOrder;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listOrder;
    }

    public int changeOrderStatus(Order order) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        if (conn != null) {
            try {
                String sql = "UPDATE Orders SET OrderStatus = ? WHERE OrderId = ? AND OrderStatus != 'Đã hủy'";
                ps = conn.prepareStatement(sql);
                ps.setString(1, order.getOrderStatus());
                ps.setInt(2, order.getOrderId());
                return ps.executeUpdate();
            } catch (SQLException ex) {
                return 0;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }

    public int cancelOrder(Order order) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        if (conn != null) {
            try {
                String sql = "UPDATE Orders SET OrderStatus = ? WHERE OrderId = ? AND OrderStatus = 'Chờ xác nhận'";
                ps = conn.prepareStatement(sql);
                ps.setString(1, order.getOrderStatus());
                ps.setInt(2, order.getOrderId());
                return ps.executeUpdate();
            } catch (SQLException ex) {
                return 0;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }
}
