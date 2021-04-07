package org.example.repository;

import org.example.model.Mysql;
import org.example.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductRepository {
    private final Mysql mysql;
    private static final String PRODUCT_ID = "ProductId";
    private static final String PRODUCT_NAME = "ProductName";
    private static final String PRODUCT_PRICE = "ProductPrice";
    private static final String CATEGORY_NAME = "CategoryName";
    private static final String SALE_DATE = "SaleDate";
    private static final String DESCRIPTION = "Description";
    private static final String ADMIN_ID = "AdminId";
    private static final String DISPLAY_HOME = "DisplayHome";
    private static final String SALE = "Sale";
    private static final String COUNT_PRODUCT = "COUNT(ProductId)";

    public ProductRepository() {
        mysql = new Mysql();
    }

    public int insertProduct(Product product) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        if (conn != null) {
            try {
                ps = conn.prepareStatement("INSERT INTO Products(ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, DisplayHome, AdminId) values(?,?,?,?,?,?,?,?)");
                ps.setString(1, product.getProductName());
                ps.setInt(2, product.getProductPrice());
                ps.setInt(3, product.getProductSale());
                ps.setString(4, product.getCategoryName());
                ps.setDate(5, product.getSaleDate());
                ps.setString(6, product.getDescription());
                ps.setInt(7, product.getDisplayHome());
                ps.setInt(8, product.getAdminId());
                return ps.executeUpdate();
            } catch (SQLException ex) {
                return 0;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }

    public int getProductIdNew() {
        Connection conn = mysql.getConnect();
        Statement stmt = null;
        ResultSet rs = null;
        if (conn == null) {
            return 0;
        }
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT MAX(ProductId) FROM Products");
            if (rs.next()) {
                return rs.getInt("MAX(ProductId)");
            }
        } catch (SQLException ex) {
            return 0;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public List<Product> getProduct() {
        List<Product> listProduct = new ArrayList<>();
        Connection conn = mysql.getConnect();
        Statement stmt = null;
        ResultSet rs = null;
        if (conn == null) {
            return listProduct;
        }
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products");
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt(PRODUCT_ID));
                product.setProductName(rs.getString(PRODUCT_NAME));
                product.setProductPrice(rs.getInt(PRODUCT_PRICE));
                product.setProductSale(rs.getInt(SALE));
                product.setCategoryName(rs.getString(CATEGORY_NAME));
                product.setSaleDate(rs.getDate(SALE_DATE));
                product.setDescription(rs.getString(DESCRIPTION));
                product.setAdminId(rs.getInt(ADMIN_ID));
                product.setDisplayHome(rs.getInt(DISPLAY_HOME));
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            return listProduct;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listProduct;
    }

    public List<Product> sortProduct(String columnName, String type) {
        List<Product> listProduct = new ArrayList<>();
        Connection conn = mysql.getConnect();
        Statement stmt = null;
        ResultSet rs = null;
        if (conn == null) {
            return listProduct;
        }
        try {
            String sqlQuery = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products";
            if (columnName.equals(PRODUCT_ID) || columnName.equals(PRODUCT_NAME) || columnName.equals(PRODUCT_PRICE) || columnName.equals(SALE) || columnName.equals(CATEGORY_NAME) || columnName.equals(SALE_DATE)) {
                sqlQuery += " ORDER BY " + columnName;
            }
            if (type.equals("DESC")) {
                sqlQuery += " DESC";
            }
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlQuery);
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt(PRODUCT_ID));
                product.setProductName(rs.getString(PRODUCT_NAME));
                product.setProductPrice(rs.getInt(PRODUCT_PRICE));
                product.setProductSale(rs.getInt(SALE));
                product.setCategoryName(rs.getString(CATEGORY_NAME));
                product.setSaleDate(rs.getDate(SALE_DATE));
                product.setDescription(rs.getString(DESCRIPTION));
                product.setAdminId(rs.getInt(ADMIN_ID));
                product.setDisplayHome(rs.getInt(DISPLAY_HOME));
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            return listProduct;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listProduct;
    }

    public Product getProductById(int productId) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return null;
        }
        try {
            String sql = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE ProductId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            if (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt(PRODUCT_ID));
                product.setProductName(rs.getString(PRODUCT_NAME));
                product.setProductPrice(rs.getInt(PRODUCT_PRICE));
                product.setProductSale(rs.getInt(SALE));
                product.setCategoryName(rs.getString(CATEGORY_NAME));
                product.setSaleDate(rs.getDate(SALE_DATE));
                product.setDescription(rs.getString(DESCRIPTION));
                product.setAdminId(rs.getInt(ADMIN_ID));
                product.setDisplayHome(rs.getInt(DISPLAY_HOME));
                return product;
            }
        } catch (SQLException ex) {
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public int deleteProduct(int productId) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        if (conn != null) {
            try {
                String sql = "DELETE FROM Products WHERE ProductId = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, productId);
                return ps.executeUpdate();
            } catch (SQLException ex) {
                return 0;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }

    public int updateProduct(Product product) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        if (conn != null) {
            try {
                String sql = "UPDATE Products SET ProductName = ?, ProductPrice = ?, Sale = ?, CategoryName = ?, Description = ?, DisplayHome = ? WHERE ProductId = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, product.getProductName());
                ps.setInt(2, product.getProductPrice());
                ps.setInt(3, product.getProductSale());
                ps.setString(4, product.getCategoryName());
                ps.setString(5, product.getDescription());
                ps.setInt(6, product.getDisplayHome());
                ps.setInt(7, product.getProductId());
                return ps.executeUpdate();
            } catch (SQLException ex) {
                return 0;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }

    public List<Product> getTop6ProductHome() {
        List<Product> listProduct = new ArrayList<>();
        Connection conn = mysql.getConnect();
        Statement stmt = null;
        ResultSet rs = null;
        if (conn == null) {
            return listProduct;
        }
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE DisplayHome = 1 ORDER BY ProductId LIMIT 3");
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt(PRODUCT_ID));
                product.setProductName(rs.getString(PRODUCT_NAME));
                product.setProductPrice(rs.getInt(PRODUCT_PRICE));
                product.setProductSale(rs.getInt(SALE));
                product.setCategoryName(rs.getString(CATEGORY_NAME));
                product.setSaleDate(rs.getDate(SALE_DATE));
                product.setDescription(rs.getString(DESCRIPTION));
                product.setAdminId(rs.getInt(ADMIN_ID));
                product.setDisplayHome(rs.getInt(DISPLAY_HOME));
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            return listProduct;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listProduct;
    }

    public List<Product> getNext6ProductHome(int amountProduct) {
        List<Product> listProduct = new ArrayList<>();
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return listProduct;
        }
        try {
            String sqlQuery = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE DisplayHome = 1 ORDER BY ProductId LIMIT ?, 3";
            ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, amountProduct);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt(PRODUCT_ID));
                product.setProductName(rs.getString(PRODUCT_NAME));
                product.setProductPrice(rs.getInt(PRODUCT_PRICE));
                product.setProductSale(rs.getInt(SALE));
                product.setCategoryName(rs.getString(CATEGORY_NAME));
                product.setSaleDate(rs.getDate(SALE_DATE));
                product.setDescription(rs.getString(DESCRIPTION));
                product.setAdminId(rs.getInt(ADMIN_ID));
                product.setDisplayHome(rs.getInt(DISPLAY_HOME));
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            return listProduct;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listProduct;
    }

    public List<Product> getProductByCategory(String category, String minPrice, String maxPrice, int indexProduct) {
        List<Product> listProduct = new ArrayList<>();
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return listProduct;
        }
        try {
            if ((minPrice == null && maxPrice == null)) {
                String sqlQuery = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE CategoryName LIKE ? ORDER BY ProductId DESC LIMIT ?, 3";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, indexProduct);
            } else if (minPrice == null) {
                String sqlQuery = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE CategoryName LIKE ? AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) <= ? ORDER BY ProductId DESC LIMIT ?, 3";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, Integer.parseInt(maxPrice));
                ps.setInt(3, indexProduct);
            } else if (maxPrice == null) {
                String sqlQuery = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE CategoryName LIKE ? AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) >= ? ORDER BY ProductId DESC LIMIT ?, 3";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, Integer.parseInt(minPrice));
                ps.setInt(3, indexProduct);
            } else {
                String sqlQuery = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE CategoryName LIKE ? AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) <= ? AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) >= ? ORDER BY ProductId DESC LIMIT ?, 3";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, Integer.parseInt(maxPrice));
                ps.setInt(3, Integer.parseInt(minPrice));
                ps.setInt(4, indexProduct);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt(PRODUCT_ID));
                product.setProductName(rs.getString(PRODUCT_NAME));
                product.setProductPrice(rs.getInt(PRODUCT_PRICE));
                product.setProductSale(rs.getInt(SALE));
                product.setCategoryName(rs.getString(CATEGORY_NAME));
                product.setSaleDate(rs.getDate(SALE_DATE));
                product.setDescription(rs.getString(DESCRIPTION));
                product.setAdminId(rs.getInt(ADMIN_ID));
                product.setDisplayHome(rs.getInt(DISPLAY_HOME));
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            return listProduct;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listProduct;
    }

    public int getTotalProductByCategory(String category, String minPrice, String maxPrice) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return 0;
        }
        try {
            if ((minPrice == null && maxPrice == null)) {
                String sqlQuery = "SELECT COUNT(ProductId) FROM Products WHERE CategoryName LIKE ?";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
            } else if (minPrice == null) {
                String sqlQuery = "SELECT COUNT(ProductId) FROM Products WHERE CategoryName LIKE ? AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) <= ?";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, Integer.parseInt(maxPrice));
            } else if (maxPrice == null) {
                String sqlQuery = "SELECT COUNT(ProductId) FROM Products WHERE CategoryName LIKE ? AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) >= ?";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, Integer.parseInt(minPrice));
            } else {
                String sqlQuery = "SELECT COUNT(ProductId) FROM Products WHERE CategoryName LIKE ? AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) <= ? AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) >= ?";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, Integer.parseInt(maxPrice));
                ps.setInt(3, Integer.parseInt(minPrice));
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(COUNT_PRODUCT);
            }
        } catch (SQLException ex) {
            return 0;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public List<Product> getProductNewByCategory(String category, String minPrice, String maxPrice, int indexProduct) {
        List<Product> listProduct = new ArrayList<>();
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return listProduct;
        }
        try {
            if ((minPrice == null && maxPrice == null)) {
                String sqlQuery = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE CategoryName LIKE ? AND ADDDATE(SaleDate, 60) > CURDATE() ORDER BY ProductId DESC LIMIT ?, 3";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, indexProduct);
            } else if (minPrice == null) {
                String sqlQuery = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE CategoryName LIKE ? AND ADDDATE(SaleDate, 60) > CURDATE() AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) <= ? ORDER BY ProductId DESC LIMIT ?, 3";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, Integer.parseInt(maxPrice));
                ps.setInt(3, indexProduct);
            } else if (maxPrice == null) {
                String sqlQuery = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE CategoryName LIKE ? AND ADDDATE(SaleDate, 60) > CURDATE() AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) >= ? ORDER BY ProductId DESC LIMIT ?, 3";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, Integer.parseInt(minPrice));
                ps.setInt(3, indexProduct);
            } else {
                String sqlQuery = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE CategoryName LIKE ? AND ADDDATE(SaleDate, 60) > CURDATE() AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) <= ? AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) >= ? ORDER BY ProductId DESC LIMIT ?, 3";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, Integer.parseInt(maxPrice));
                ps.setInt(3, Integer.parseInt(minPrice));
                ps.setInt(4, indexProduct);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt(PRODUCT_ID));
                product.setProductName(rs.getString(PRODUCT_NAME));
                product.setProductPrice(rs.getInt(PRODUCT_PRICE));
                product.setProductSale(rs.getInt(SALE));
                product.setCategoryName(rs.getString(CATEGORY_NAME));
                product.setSaleDate(rs.getDate(SALE_DATE));
                product.setDescription(rs.getString(DESCRIPTION));
                product.setAdminId(rs.getInt(ADMIN_ID));
                product.setDisplayHome(rs.getInt(DISPLAY_HOME));
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            return listProduct;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listProduct;
    }

    public int getTotalProductNewByCategory(String category, String minPrice, String maxPrice) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return 0;
        }
        try {
            if ((minPrice == null && maxPrice == null)) {
                String sqlQuery = "SELECT COUNT(ProductId) FROM Products WHERE CategoryName LIKE ? AND ADDDATE(SaleDate, 60) > CURDATE()";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
            } else if (minPrice == null) {
                String sqlQuery = "SELECT COUNT(ProductId) FROM Products WHERE CategoryName LIKE ? AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) <= ? AND ADDDATE(SaleDate, 60) > CURDATE()";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, Integer.parseInt(maxPrice));
            } else if (maxPrice == null) {
                String sqlQuery = "SELECT COUNT(ProductId) FROM Products WHERE CategoryName LIKE ? AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) >= ? AND ADDDATE(SaleDate, 60) > CURDATE()";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, Integer.parseInt(minPrice));
            } else {
                String sqlQuery = "SELECT COUNT(ProductId) FROM Products WHERE CategoryName LIKE ? AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) <= ? AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) >= ? AND ADDDATE(SaleDate, 60) > CURDATE()";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, Integer.parseInt(maxPrice));
                ps.setInt(3, Integer.parseInt(minPrice));
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(COUNT_PRODUCT);
            }
        } catch (SQLException ex) {
            return 0;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public List<Product> getProductByCategory(String category) {
        List<Product> listProduct = new ArrayList<>();
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return listProduct;
        }
        try {
            String sqlQuery = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE CategoryName LIKE ? ORDER BY ProductId DESC";
            ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, "%" + category);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt(PRODUCT_ID));
                product.setProductName(rs.getString(PRODUCT_NAME));
                product.setProductPrice(rs.getInt(PRODUCT_PRICE));
                product.setProductSale(rs.getInt(SALE));
                product.setCategoryName(rs.getString(CATEGORY_NAME));
                product.setSaleDate(rs.getDate(SALE_DATE));
                product.setDescription(rs.getString(DESCRIPTION));
                product.setAdminId(rs.getInt(ADMIN_ID));
                product.setDisplayHome(rs.getInt(DISPLAY_HOME));
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            return listProduct;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listProduct;
    }

    public List<Product> getProductByCategory(String category, String minPrice, String maxPrice) {
        List<Product> listProduct = new ArrayList<>();
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return listProduct;
        }
        try {
            if ((minPrice == null && maxPrice == null)) {
                String sqlQuery = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE CategoryName LIKE ? ORDER BY ProductId DESC";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
            } else if (minPrice == null) {
                String sqlQuery = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE CategoryName LIKE ? AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) <= ? ORDER BY ProductId DESC";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, Integer.parseInt(maxPrice));
            } else if (maxPrice == null) {
                String sqlQuery = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE CategoryName LIKE ? AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) >= ? ORDER BY ProductId DESC";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, Integer.parseInt(minPrice));
            } else {
                String sqlQuery = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE CategoryName LIKE ? AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) <= ? AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) >= ? ORDER BY ProductId DESC";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, Integer.parseInt(maxPrice));
                ps.setInt(3, Integer.parseInt(minPrice));
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt(PRODUCT_ID));
                product.setProductName(rs.getString(PRODUCT_NAME));
                product.setProductPrice(rs.getInt(PRODUCT_PRICE));
                product.setProductSale(rs.getInt(SALE));
                product.setCategoryName(rs.getString(CATEGORY_NAME));
                product.setSaleDate(rs.getDate(SALE_DATE));
                product.setDescription(rs.getString(DESCRIPTION));
                product.setAdminId(rs.getInt(ADMIN_ID));
                product.setDisplayHome(rs.getInt(DISPLAY_HOME));
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            return listProduct;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listProduct;
    }

    public List<Product> filterProductByPrice(String minPrice, String maxPrice) {
        List<Product> listProduct = new ArrayList<>();
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return listProduct;
        }
        try {
            if ((minPrice == null && maxPrice == null)) {
                String sqlQuery = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products ORDER BY ProductId DESC";
                ps = conn.prepareStatement(sqlQuery);
            } else if (minPrice == null) {
                String sqlQuery = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) <= ? ORDER BY ProductId DESC";
                ps = conn.prepareStatement(sqlQuery);
                ps.setInt(1, Integer.parseInt(maxPrice));
            } else if (maxPrice == null) {
                String sqlQuery = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) >= ? ORDER BY ProductId DESC";
                ps = conn.prepareStatement(sqlQuery);
                ps.setInt(1, Integer.parseInt(minPrice));
            } else {
                String sqlQuery = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) <= ? AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) >= ? ORDER BY ProductId DESC";
                ps = conn.prepareStatement(sqlQuery);
                ps.setInt(1, Integer.parseInt(maxPrice));
                ps.setInt(2, Integer.parseInt(minPrice));
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt(PRODUCT_ID));
                product.setProductName(rs.getString(PRODUCT_NAME));
                product.setProductPrice(rs.getInt(PRODUCT_PRICE));
                product.setProductSale(rs.getInt(SALE));
                product.setCategoryName(rs.getString(CATEGORY_NAME));
                product.setSaleDate(rs.getDate(SALE_DATE));
                product.setDescription(rs.getString(DESCRIPTION));
                product.setAdminId(rs.getInt(ADMIN_ID));
                product.setDisplayHome(rs.getInt(DISPLAY_HOME));
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            return listProduct;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listProduct;
    }

    public List<Product> getProductSaleByCategory(String category, String minPrice, String maxPrice, int indexProduct) {
        List<Product> listProduct = new ArrayList<>();
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return listProduct;
        }
        try {
            if ((minPrice == null && maxPrice == null)) {
                String sqlQuery = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE CategoryName LIKE ? AND Sale > 0 ORDER BY ProductId DESC LIMIT ?, 3";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, indexProduct);
            } else if (minPrice == null) {
                String sqlQuery = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE CategoryName LIKE ? AND Sale > 0 AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) <= ? ORDER BY ProductId DESC LIMIT ?, 3";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, Integer.parseInt(maxPrice));
                ps.setInt(3, indexProduct);
            } else if (maxPrice == null) {
                String sqlQuery = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE CategoryName LIKE ? AND Sale > 0 AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) >= ? ORDER BY ProductId DESC LIMIT ?, 3";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, Integer.parseInt(minPrice));
                ps.setInt(3, indexProduct);
            } else {
                String sqlQuery = "SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE CategoryName LIKE ? AND Sale > 0 AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) <= ? AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) >= ? ORDER BY ProductId DESC LIMIT ?, 3";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, Integer.parseInt(maxPrice));
                ps.setInt(3, Integer.parseInt(minPrice));
                ps.setInt(4, indexProduct);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt(PRODUCT_ID));
                product.setProductName(rs.getString(PRODUCT_NAME));
                product.setProductPrice(rs.getInt(PRODUCT_PRICE));
                product.setProductSale(rs.getInt(SALE));
                product.setCategoryName(rs.getString(CATEGORY_NAME));
                product.setSaleDate(rs.getDate(SALE_DATE));
                product.setDescription(rs.getString(DESCRIPTION));
                product.setAdminId(rs.getInt(ADMIN_ID));
                product.setDisplayHome(rs.getInt(DISPLAY_HOME));
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            return listProduct;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listProduct;
    }

    public int getTotalProductSaleByCategory(String category, String minPrice, String maxPrice) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return 0;
        }
        try {
            if ((minPrice == null && maxPrice == null)) {
                String sqlQuery = "SELECT COUNT(ProductId) FROM Products WHERE CategoryName LIKE ? AND Sale > 0";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
            } else if (minPrice == null) {
                String sqlQuery = "SELECT COUNT(ProductId) FROM Products WHERE CategoryName LIKE ? AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) <= ? AND Sale > 0";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, Integer.parseInt(maxPrice));
            } else if (maxPrice == null) {
                String sqlQuery = "SELECT COUNT(ProductId) FROM Products WHERE CategoryName LIKE ? AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) >= ? AND Sale > 0";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, Integer.parseInt(minPrice));
            } else {
                String sqlQuery = "SELECT COUNT(ProductId) FROM Products WHERE CategoryName LIKE ? AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) <= ? AND (ProductPrice - ROUND((ProductPrice*Sale/100), -3)) >= ? AND Sale > 0";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, "%" + category);
                ps.setInt(2, Integer.parseInt(maxPrice));
                ps.setInt(3, Integer.parseInt(minPrice));
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(COUNT_PRODUCT);
            }
        } catch (SQLException ex) {
            return 0;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    //Tìm kiếm sản phẩm có sử dụng phân trang
    public List<Product> searchProduct(String productName, int indexProduct) {
        String[] keyWord = productName.split(" ");
        List<Product> listProduct = new ArrayList<>();
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return listProduct;
        }
        try {
            StringBuilder sqlQuery = new StringBuilder("SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE");
            for (int i = 0; i < keyWord.length; i++) {
                if (i < keyWord.length - 1) {
                    sqlQuery.append(" ProductName LIKE ? AND");
                } else {
                    sqlQuery.append(" ProductName LIKE ? ORDER BY ProductId DESC LIMIT ?, 3");
                }
            }
            ps = conn.prepareStatement(sqlQuery.toString());
            for (int i = 0; i < keyWord.length; i++) {
                ps.setString((i + 1), "%" + keyWord[i] + "%");
            }
            ps.setInt((keyWord.length + 1), indexProduct);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt(PRODUCT_ID));
                product.setProductName(rs.getString(PRODUCT_NAME));
                product.setProductPrice(rs.getInt(PRODUCT_PRICE));
                product.setProductSale(rs.getInt(SALE));
                product.setCategoryName(rs.getString(CATEGORY_NAME));
                product.setSaleDate(rs.getDate(SALE_DATE));
                product.setDescription(rs.getString(DESCRIPTION));
                product.setAdminId(rs.getInt(ADMIN_ID));
                product.setDisplayHome(rs.getInt(DISPLAY_HOME));
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            return listProduct;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listProduct;
    }

    public int getTotalSearchProduct(String productName) {
        String[] keyWord = productName.split(" ");
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return 0;
        }
        try {
            StringBuilder sqlQuery = new StringBuilder("SELECT COUNT(ProductId) FROM Products WHERE ");
            for (int i = 0; i < keyWord.length; i++) {
                if (i < keyWord.length - 1) {
                    sqlQuery.append("ProductName LIKE ? AND ");
                } else {
                    sqlQuery.append("ProductName LIKE ?");
                }
            }
            ps = conn.prepareStatement(sqlQuery.toString());
            for (int i = 0; i < keyWord.length; i++) {
                ps.setString((i + 1), "%" + keyWord[i] + "%");
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(COUNT_PRODUCT);
            }
        } catch (SQLException ex) {
            return 0;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    //Tìm kiếm sản phẩm không sử dụng phân trang
    public List<Product> searchProduct(String productName) {
        String[] keyWord = productName.split(" ");
        List<Product> listProduct = new ArrayList<>();
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return listProduct;
        }
        try {
            StringBuilder sqlQuery = new StringBuilder("SELECT ProductId, ProductName, ProductPrice, Sale, CategoryName, SaleDate, Description, AdminId, DisplayHome FROM Products WHERE ");
            for (int i = 0; i < keyWord.length; i++) {
                if (i < keyWord.length - 1) {
                    sqlQuery.append("ProductName LIKE ? AND ");
                } else {
                    sqlQuery.append("ProductName LIKE ?");
                }
            }
            ps = conn.prepareStatement(sqlQuery.toString());
            for (int i = 0; i < keyWord.length; i++) {
                ps.setString((i + 1), "%" + keyWord[i] + "%");
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt(PRODUCT_ID));
                product.setProductName(rs.getString(PRODUCT_NAME));
                product.setProductPrice(rs.getInt(PRODUCT_PRICE));
                product.setProductSale(rs.getInt(SALE));
                product.setCategoryName(rs.getString(CATEGORY_NAME));
                product.setSaleDate(rs.getDate(SALE_DATE));
                product.setDescription(rs.getString(DESCRIPTION));
                product.setAdminId(rs.getInt(ADMIN_ID));
                product.setDisplayHome(rs.getInt(DISPLAY_HOME));
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            return listProduct;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listProduct;
    }

}
