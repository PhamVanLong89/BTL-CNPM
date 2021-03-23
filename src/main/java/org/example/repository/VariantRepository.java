package org.example.repository;

import org.example.model.Mysql;
import org.example.model.Variant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VariantRepository {
    private Mysql mysql;
    private static final String PRODUCT_ID = "ProductId";
    private static final String COLOR = "Color";
    private static final String QUANTITY = "Quantity";
    private static final String IMAGE1 = "Image1";
    private static final String IMAGE2 = "Image2";
    private static final String SIZE = "Size";
    private static final String SKU = "SKU";

    public VariantRepository() {
        mysql = new Mysql();
    }

    public int insertVariant(Variant variant) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        if (conn != null) {
            try {
                ps = conn.prepareStatement("INSERT INTO Variants(ProductId, SKU, Color, Size, Quantity, Image1, Image2) values(?,?,?,?,?,?,?)");
                ps.setInt(1, variant.getProductId());
                ps.setString(2, variant.getSKU());
                ps.setString(3, variant.getColor());
                ps.setString(4, variant.getSize());
                ps.setInt(5, variant.getQuantity());
                ps.setString(6, variant.getImage1());
                ps.setString(7, variant.getImage2());
                return ps.executeUpdate();
            } catch (SQLException ex) {
                return 0;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }

    public Variant getVariantBySKU(String sKU) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return null;
        }
        try {
            String sql = "SELECT ProductId, Color, Size, SKU, Quantity, Image1, Image2 FROM Variants WHERE SKU = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, sKU);
            rs = ps.executeQuery();
            if (rs.next()) {
                Variant variant = new Variant();
                variant.setProductId(rs.getInt(PRODUCT_ID));
                variant.setColor(rs.getString(COLOR));
                variant.setSize(rs.getString(SIZE));
                variant.setSKU(rs.getString(SKU));
                variant.setQuantity(rs.getInt(QUANTITY));
                variant.setImage1(rs.getString(IMAGE1));
                variant.setImage2(rs.getString(IMAGE2));
                return variant;
            }
        } catch (SQLException ex) {
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public List<Variant> getVariantByProductId(int productId) {
        ArrayList<Variant> listVariant = new ArrayList<>();
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return listVariant;
        }
        try {
            String sql = "SELECT ProductId, Color, Size, SKU, Quantity, Image1, Image2 FROM Variants WHERE ProductId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Variant variant = new Variant();
                variant.setProductId(rs.getInt(PRODUCT_ID));
                variant.setColor(rs.getString(COLOR));
                variant.setSize(rs.getString(SIZE));
                variant.setSKU(rs.getString(SKU));
                variant.setQuantity(rs.getInt(QUANTITY));
                variant.setImage1(rs.getString(IMAGE1));
                variant.setImage2(rs.getString(IMAGE2));
                listVariant.add(variant);
            }
        } catch (SQLException ex) {
            return listVariant;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listVariant;
    }

    public int deleteVariant(String sKU) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        if (conn != null) {
            try {
                String sql = "DELETE FROM Variants WHERE SKU = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, sKU);
                return ps.executeUpdate();
            } catch (SQLException ex) {
                return 0;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }

    public int updateVariant(Variant variant) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        if (conn != null) {
            try {
                String sql = "UPDATE Variants SET Quantity = ?, Image1 = ?, Image2 = ? WHERE SKU = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, variant.getQuantity());
                ps.setString(2, variant.getImage1());
                ps.setString(3, variant.getImage2());
                ps.setString(4, variant.getSKU());
                return ps.executeUpdate();
            } catch (SQLException ex) {
                return 0;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }

    public String getOneImageVariantByProductId(int productId) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return null;
        }
        try {
            String sql = "SELECT Image1, Image2 FROM Variants WHERE ProductId = ? AND (Image1 IS NOT NULL OR Image2 IS NOT NULL)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getString(IMAGE1) != null) {
                    return rs.getString(IMAGE1);
                }
                return rs.getString(IMAGE2);
            }
        } catch (SQLException ex) {
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public List<String> getColorByProductId(int productId) {
        List<String> listColor = new ArrayList<>();
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return listColor;
        }
        try {
            String sqlQuery = "SELECT DISTINCT Color FROM Variants WHERE ProductId = ?";
            ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                listColor.add(rs.getString(COLOR));
            }
        } catch (SQLException ex) {
            return listColor;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listColor;
    }

    public List<String> getImageVariantByProductId(int productId) {
        List<String> listImage = new ArrayList<>();
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return listImage;
        }
        try {
            String sql = "SELECT Image1, Image2 FROM Variants WHERE ProductId = ? AND (Image1 IS NOT NULL OR Image2 IS NOT NULL)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString(IMAGE1) != null) {
                    listImage.add(rs.getString(IMAGE1));
                }
                if (rs.getString(IMAGE2) != null) {
                    listImage.add(rs.getString(IMAGE2));
                }
            }
        } catch (SQLException ex) {
            return listImage;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listImage;
    }

    public List<String> getSizeByColor(String color, int productId) {
        List<String> listSize = new ArrayList<>();
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return listSize;
        }
        try {
            String sqlQuery = "SELECT DISTINCT Size FROM Variants WHERE Color = ? AND ProductId = ?";
            ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, color);
            ps.setInt(2, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                listSize.add(rs.getString(SIZE));
            }
        } catch (SQLException ex) {
            return listSize;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listSize;
    }

    public Variant getVariantByColorAndSize(String size, String color, int productId) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return null;
        }
        try {
            String sqlQuery = "SELECT ProductId, Color, Size, SKU, Quantity, Image1, Image2 FROM Variants WHERE ProductId = ? AND Color = ? AND Size = ?";
            ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, productId);
            ps.setString(2, color);
            ps.setString(3, size);
            rs = ps.executeQuery();
            if (rs.next()) {
                Variant variant = new Variant();
                variant.setProductId(rs.getInt(PRODUCT_ID));
                variant.setColor(rs.getString(COLOR));
                variant.setSize(rs.getString(SIZE));
                variant.setSKU(rs.getString(SKU));
                variant.setQuantity(rs.getInt(QUANTITY));
                variant.setImage1(rs.getString(IMAGE1));
                variant.setImage2(rs.getString(IMAGE2));
                return variant;
            }
        } catch (SQLException ex) {
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(VariantRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
