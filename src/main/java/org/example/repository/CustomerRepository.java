package org.example.repository;

import org.example.model.Customer;
import org.example.model.Mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerRepository {
    private final Mysql mysql;
    private static final String USER_ID = "UserID";
    private static final String USER_NAME = "UserName";
    private static final String NUMBER_PHONE = "NumberPhone";
    private static final String ADDRESS = "Address";
    private static final String EMAIL = "Email";
    private static final String PASSWORD = "PassWord";
    private static final String STATUS_ACTIVE = "StatusActive";


    public CustomerRepository() {
        mysql = new Mysql();
    }

    public Customer getCustomerByEmail(String email) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return null;
        }
        try {
            String sql = "SELECT UserID, UserName, NumberPhone, Address, Email, PassWord, StatusActive FROM Users WHERE Email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt(USER_ID));
                customer.setCustomerName(rs.getString(USER_NAME));
                customer.setNumberPhone(rs.getString(NUMBER_PHONE));
                customer.setAddress(rs.getString(ADDRESS));
                customer.setEmail(rs.getString(EMAIL));
                customer.setPassword(rs.getString(PASSWORD));
                customer.setStatusActive(rs.getInt(STATUS_ACTIVE));
                return customer;
            }
        } catch (SQLException ex) {
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public int insertUser(Customer customer) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        if (conn != null) {
            try {
                ps = conn.prepareStatement("INSERT INTO Users(UserName, NumberPhone, Address, Email, PassWord, StatusActive) values(?,?,?,?,?,?)");
                ps.setString(1, customer.getCustomerName());
                ps.setString(2, customer.getNumberPhone());
                ps.setString(3, customer.getAddress());
                ps.setString(4, customer.getEmail());
                ps.setString(5, customer.getPassword());
                ps.setInt(6, customer.getStatusActive());
                return ps.executeUpdate();
            } catch (SQLException ex) {
                return 0;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }

    public Customer login(Customer customerLogin) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return null;
        }
        try {
            String sql = "SELECT UserID, UserName, NumberPhone, Address, Email, StatusActive FROM Users WHERE Email = ? AND Password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, customerLogin.getEmail());
            ps.setString(2, customerLogin.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt(USER_ID));
                customer.setCustomerName(rs.getString(USER_NAME));
                customer.setNumberPhone(rs.getString(NUMBER_PHONE));
                customer.setAddress(rs.getString(ADDRESS));
                customer.setEmail(rs.getString(EMAIL));
                customer.setStatusActive(rs.getInt(STATUS_ACTIVE));
                return customer;
            }
        } catch (SQLException ex) {
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Customer getCustomerById(int customerId) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return null;
        }
        try {
            String sql = "SELECT UserID, UserName, NumberPhone, Address, Email, PassWord, StatusActive FROM Users WHERE UserID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt(USER_ID));
                customer.setCustomerName(rs.getString(USER_NAME));
                customer.setNumberPhone(rs.getString(NUMBER_PHONE));
                customer.setAddress(rs.getString(ADDRESS));
                customer.setEmail(rs.getString(EMAIL));
                customer.setPassword(rs.getString(PASSWORD));
                customer.setStatusActive(rs.getInt(STATUS_ACTIVE));
                return customer;
            }
        } catch (SQLException ex) {
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public int updateCustomer(Customer customer) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        if (conn != null) {
            try {
                String sql = "UPDATE Users SET UserName = ?, NumberPhone = ?, Address = ? WHERE UserID = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, customer.getCustomerName());
                ps.setString(2, customer.getNumberPhone());
                ps.setString(3, customer.getAddress());
                ps.setInt(4, customer.getCustomerId());
                return ps.executeUpdate();
            } catch (SQLException ex) {
                return 0;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }

    public int changePassword(Customer customer) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        if (conn != null) {
            try {
                String sql = "UPDATE Users SET PassWord = ? WHERE UserID = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, customer.getPassword());
                ps.setInt(2, customer.getCustomerId());
                return ps.executeUpdate();
            } catch (SQLException ex) {
                return 0;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }

    public List<Customer> getCustomer() {
        List<Customer> listCustomers = new ArrayList<>();
        Connection conn = mysql.getConnect();
        Statement stmt = null;
        ResultSet rs = null;
        if (conn == null) {
            return listCustomers;
        }
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT UserID, UserName, NumberPhone, Address, Email, StatusActive FROM Users");
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt(USER_ID));
                customer.setCustomerName(rs.getString(USER_NAME));
                customer.setNumberPhone(rs.getString(NUMBER_PHONE));
                customer.setAddress(rs.getString(ADDRESS));
                customer.setEmail(rs.getString(EMAIL));
                customer.setStatusActive(rs.getInt(STATUS_ACTIVE));
                listCustomers.add(customer);
            }
        } catch (SQLException ex) {
            return listCustomers;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listCustomers;
    }

    public int changeStatusAccount(Customer customer) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        if (conn != null) {
            try {
                String sql = "UPDATE Users SET StatusActive = ? WHERE UserID = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, customer.getStatusActive());
                ps.setInt(2, customer.getCustomerId());
                return ps.executeUpdate();
            } catch (SQLException ex) {
                return 0;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }

    public List<Customer> searchCustomer(String customerName) {
        String[] keyWord = customerName.split(" ");
        List<Customer> listCustomer = new ArrayList<>();
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return listCustomer;
        }
        try {
            StringBuilder sqlQuery = new StringBuilder("SELECT UserID, UserName, NumberPhone, Address, Email, StatusActive FROM Users WHERE ");
            for (int i = 0; i < keyWord.length; i++) {
                if (i < keyWord.length - 1) {
                    sqlQuery.append("UserName LIKE ? AND ");
                } else {
                    sqlQuery.append("UserName LIKE ?");
                }
            }
            ps = conn.prepareStatement(sqlQuery.toString());
            for (int i = 0; i < keyWord.length; i++) {
                ps.setString((i + 1), "%" + keyWord[i] + "%");
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt(USER_ID));
                customer.setCustomerName(rs.getString(USER_NAME));
                customer.setNumberPhone(rs.getString(NUMBER_PHONE));
                customer.setAddress(rs.getString(ADDRESS));
                customer.setEmail(rs.getString(EMAIL));
                customer.setStatusActive(rs.getInt(STATUS_ACTIVE));
                listCustomer.add(customer);
            }
        } catch (SQLException ex) {
            return listCustomer;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listCustomer;
    }

}
