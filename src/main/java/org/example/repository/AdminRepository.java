package org.example.repository;

import org.example.model.Admin;
import org.example.model.Mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminRepository {
    private final Mysql mysql;
    private static final String ADMIN_ID = "AdminId";
    private static final String ADMIN_NAME = "AdminName";
    private static final String NUMBER_PHONE = "NumberPhone";
    private static final String ADDRESS = "Address";
    private static final String SEX = "Sex";
    private static final String EMAIL = "Email";
    private static final String PASSWORD = "PassWord";
    private static final String IMAGE = "Image";

    public AdminRepository() {
        mysql = new Mysql();
    }

    public int insertAdmin(Admin admin) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        if (conn != null) {
            try {
                ps = conn.prepareStatement("INSERT INTO Admins(AdminName, NumberPhone, Sex, Address, Email, PassWord, Image) values(?,?,?,?,?,?,?)");
                ps.setString(1, admin.getAdminName());
                ps.setString(2, admin.getNumberPhone());
                ps.setString(3, admin.getSex());
                ps.setString(4, admin.getAddress());
                ps.setString(5, admin.getEmail());
                ps.setString(6, admin.getPassword());
                ps.setString(7, admin.getImage());
                return ps.executeUpdate();
            } catch (SQLException ex) {
                return 0;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }

    public Admin getAdminByEmail(String email) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return null;
        }
        try {
            String sql = "SELECT AdminId, AdminName, NumberPhone, Sex, Address, Email, PassWord, Image FROM Admins WHERE Email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin();
                admin.setAdminId(rs.getInt(ADMIN_ID));
                admin.setAdminName(rs.getString(ADMIN_NAME));
                admin.setNumberPhone(rs.getString(NUMBER_PHONE));
                admin.setSex(rs.getString(SEX));
                admin.setAddress(rs.getString(ADDRESS));
                admin.setEmail(rs.getString(EMAIL));
                admin.setPassword(rs.getString(PASSWORD));
                admin.setImage(rs.getString(IMAGE));
                return admin;
            }
        } catch (SQLException ex) {
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public List<Admin> getAdmin() {
        List<Admin> listAdmin = new ArrayList<>();
        Connection conn = mysql.getConnect();
        Statement stmt = null;
        ResultSet rs = null;
        if (conn == null) {
            return listAdmin;
        }
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT AdminId, AdminName, NumberPhone, Sex, Address, Email, PassWord, Image FROM Admins");
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setAdminId(rs.getInt(ADMIN_ID));
                admin.setAdminName(rs.getString(ADMIN_NAME));
                admin.setNumberPhone(rs.getString(NUMBER_PHONE));
                admin.setSex(rs.getString(SEX));
                admin.setAddress(rs.getString(ADDRESS));
                admin.setEmail(rs.getString(EMAIL));
                admin.setPassword(rs.getString(PASSWORD));
                admin.setImage(rs.getString(IMAGE));
                listAdmin.add(admin);
            }
        } catch (SQLException ex) {
            return listAdmin;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listAdmin;
    }

    public Admin getAdminById(int adminId) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return null;
        }
        try {
            String sql = "SELECT AdminId, AdminName, NumberPhone, Sex, Address, Email, PassWord, Image FROM Admins WHERE AdminId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, adminId);
            rs = ps.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin();
                admin.setAdminId(rs.getInt(ADMIN_ID));
                admin.setAdminName(rs.getString(ADMIN_NAME));
                admin.setNumberPhone(rs.getString(NUMBER_PHONE));
                admin.setSex(rs.getString(SEX));
                admin.setAddress(rs.getString(ADDRESS));
                admin.setEmail(rs.getString(EMAIL));
                admin.setPassword(rs.getString(PASSWORD));
                admin.setImage(rs.getString(IMAGE));
                return admin;
            }
        } catch (SQLException ex) {
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public int updateAdmin(Admin admin) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        if (conn != null) {
            try {
                String sql = "UPDATE Admins SET AdminName = ?, NumberPhone = ?, Sex = ?, Address = ?, Image = ? WHERE AdminId = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, admin.getAdminName());
                ps.setString(2, admin.getNumberPhone());
                ps.setString(3, admin.getSex());
                ps.setString(4, admin.getAddress());
                ps.setString(5, admin.getImage());
                ps.setInt(6, admin.getAdminId());
                return ps.executeUpdate();
            } catch (SQLException ex) {
                return 0;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }

    public Admin login(Admin adminLogin) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (conn == null) {
            return null;
        }
        try {
            String sql = "SELECT AdminId, AdminName, NumberPhone, Sex, Address, Email, Image FROM Admins WHERE Email = ? AND Password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, adminLogin.getEmail());
            ps.setString(2, adminLogin.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin();
                admin.setAdminId(rs.getInt(ADMIN_ID));
                admin.setAdminName(rs.getString(ADMIN_NAME));
                admin.setNumberPhone(rs.getString(NUMBER_PHONE));
                admin.setSex(rs.getString(SEX));
                admin.setAddress(rs.getString(ADDRESS));
                admin.setEmail(rs.getString(EMAIL));
                admin.setImage(rs.getString(IMAGE));
                return admin;
            }
        } catch (SQLException ex) {
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public int changePassword(Admin admin) {
        Connection conn = mysql.getConnect();
        PreparedStatement ps = null;
        if (conn != null) {
            try {
                String sql = "UPDATE Admins SET PassWord = ? WHERE AdminId = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, admin.getPassword());
                ps.setInt(2, admin.getAdminId());
                return ps.executeUpdate();
            } catch (SQLException ex) {
                return 0;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }

}
