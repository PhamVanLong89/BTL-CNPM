package org.example.controller;

import org.example.model.Admin;
import org.example.model.Admin2;
import org.example.service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/Admin"})
@MultipartConfig
public class AdminController extends HttpServlet implements Serializable {
    private static final long serialVersionUID = 1L;
    private final AdminService adminService;
    private static final String ADMIN = "admin";
    private static final String ADMIN_LOGIN = "adminLogin";
    private static final String ERRORS = "errors";
    private static final String MESSAGE = "message";
    private static final String NAME = "name";
    private static final String LINK_LOGIN = "/Admin?chucNang=dang-nhap";
    private static final String CURRENT_PASSWORD = "currentPassword";
    private static final String NEW_PASSWORD = "newPassword";
    private static final String PASSWORD = "password";
    private static final String CONFIRM_PASSWORD = "confirmPassword";
    private static final String NUMBER_PHONE = "numberPhone";
    private static final String ADDRESS = "address";
    private static final String EMAIL = "email";
    private static final String IMAGE = "image";
    private static final String SEX = "sex";

    public AdminController() {
        super();
        adminService = new AdminService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String request = req.getParameter("chucNang");
        try {
            if (request.equals("dang-nhap")) {
                showViewLogin(req, resp);
                return;
            }
            if (request.equals("dang-xuat")) {
                logOut(req, resp);
                return;
            }
            if (!adminService.checkLogged(req)) {
                resp.sendRedirect(req.getContextPath() + LINK_LOGIN);
                return;
            }
            if (request.equals("them")) {
                showViewInsert(req, resp);
                return;
            }
            if (request.equals("hienThi")) {
                showAdmin(req, resp);
                return;
            }
            if (request.equals("sua")) {
                showViewUpdate(req, resp);
                return;
            }
            if (request.equals("doi-mat-khau")) {
                showViewChangePassword(req, resp);
                return;
            }
            if (request.equals("ThongTinChiTiet")) {
                showAdminDetail(req, resp);
                return;
            }
            if (request.equals("adminSearch")) {
                searchAdmin(req, resp);
            }
        } catch (IOException | ServletException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showViewInsert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/Admin/AddAdminView.jsp");
        dispatcher.forward(req, resp);
    }

    private void showAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Admin> listAdmin = adminService.getAdmin();
        req.setAttribute("listAdmin", listAdmin);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Admin/AdminView.jsp");
        dispatcher.forward(req, resp);
    }

    private void searchAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adminName = req.getParameter("key");
        List<Admin> listAdmin = adminService.searchAdmin(adminName);
        req.setAttribute("listAdmin", listAdmin);
        req.setAttribute("key", adminName);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Admin/AdminView.jsp");
        dispatcher.forward(req, resp);
    }

    private void showViewUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Admin adminLogin = (Admin) session.getAttribute(ADMIN_LOGIN);
        req.setAttribute(ADMIN, adminLogin);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/Admin/EditAdminView.jsp");
        dispatcher.forward(req, resp);
    }

    private void showAdminDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adminId = req.getParameter("adminId");
        Admin admin = adminService.getAdminById(adminId);
        if(admin == null){
            resp.sendRedirect(req.getContextPath() + "/Admin?chucNang=hienThi");
            return;
        }
        req.setAttribute(ADMIN, admin);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/Admin/AdminDetail.jsp");
        dispatcher.forward(req, resp);
    }

    private void showViewLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/Admin/LoginView.jsp");
        dispatcher.forward(req, resp);
    }

    private void showViewChangePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/Admin/ChangePasswordView.jsp");
        dispatcher.forward(req, resp);
    }

    private void logOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.removeAttribute(ADMIN_LOGIN);
        resp.sendRedirect(req.getContextPath() + LINK_LOGIN);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String request = req.getParameter("chucNang");
        try {
            if (request.equals("dang-nhap")) {
                login(req, resp);
                return;
            }
            if (!adminService.checkLogged(req)) {
                resp.sendRedirect(req.getContextPath() + LINK_LOGIN);
                return;
            }
            if (request.equals("them")) {
                Map<String, String> errors = adminService.checkInsertAdmin(req);
                if (errors.isEmpty()) {
                    addAdmin(req);
                } else {
                    Admin2 admin = new Admin2();
                    admin.setAdminName(req.getParameter(NAME));
                    admin.setNumberPhone(req.getParameter(NUMBER_PHONE));
                    admin.setSex(req.getParameter(SEX));
                    admin.setAddress(req.getParameter(ADDRESS));
                    admin.setEmail(req.getParameter(EMAIL));
                    admin.setPassword(req.getParameter(PASSWORD));
                    admin.setConfirmPassword(req.getParameter(CONFIRM_PASSWORD));
                    req.setAttribute(ADMIN, admin);
                    req.setAttribute(ERRORS, errors);
                }
                RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/Admin/AddAdminView.jsp");
                dispatcher.forward(req, resp);
                return;
            }
            if (request.equals("sua")) {
                Map<String, String> errors = adminService.checkUpdateAdmin(req);
                if (errors.isEmpty()) {
                    updateAdmin(req);
                } else {
                    HttpSession session = req.getSession();
                    Admin adminLogin = (Admin) session.getAttribute(ADMIN_LOGIN);
                    Admin2 admin = new Admin2();
                    admin.setAdminName(req.getParameter(NAME));
                    admin.setNumberPhone(req.getParameter(NUMBER_PHONE));
                    admin.setSex(req.getParameter(SEX));
                    admin.setAddress(req.getParameter(ADDRESS));
                    admin.setEmail(adminLogin.getEmail());
                    admin.setImage(adminLogin.getImage());
                    req.setAttribute(ERRORS, errors);
                    req.setAttribute(ADMIN, admin);
                }
                RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/Admin/EditAdminView.jsp");
                dispatcher.forward(req, resp);
                return;
            }
            if (request.equals("doi-mat-khau")) {
                Map<String, String> errors = adminService.checkChangePassword(req);
                if (errors.isEmpty()) {
                    changePassword(req);
                } else {
                    Admin2 admin = new Admin2();
                    admin.setPassword(req.getParameter(CURRENT_PASSWORD));
                    admin.setConfirmPassword(req.getParameter(CONFIRM_PASSWORD));
                    admin.setNewPassword(req.getParameter(NEW_PASSWORD));
                    req.setAttribute(ERRORS, errors);
                    req.setAttribute(ADMIN, admin);
                }
                RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/Admin/ChangePasswordView.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (IOException | ServletException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addAdmin(HttpServletRequest req) throws ServletException, IOException {
        Part filePart = req.getPart(IMAGE);
        String uploads = getServletContext().getRealPath("/img");
        String fileName = adminService.getNameImage(filePart, uploads);
        Admin admin = new Admin();
        admin.setAdminName(req.getParameter(NAME));
        admin.setNumberPhone(req.getParameter(NUMBER_PHONE));
        admin.setSex(req.getParameter(SEX));
        admin.setAddress(req.getParameter(ADDRESS));
        admin.setEmail(req.getParameter(EMAIL));
        admin.setPassword(adminService.md5(req.getParameter(PASSWORD)));
        admin.setImage(fileName);
        int rowAffected = adminService.insertAdmin(admin);
        if (rowAffected == 1) {
            req.setAttribute(MESSAGE, "Thêm mới tài khoản admin thành công");
        } else {
            req.setAttribute(MESSAGE, "Thêm mới tài khoản admin thất bại");
            req.setAttribute(ADMIN, admin);
        }
    }

    private void updateAdmin(HttpServletRequest req) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Admin adminLogin = (Admin) session.getAttribute(ADMIN_LOGIN);
        Part filePart = req.getPart(IMAGE);
        String uploads = getServletContext().getRealPath("/img");
        String fileName = adminService.getNameImage(filePart, uploads);
        if (fileName == null) {
            fileName = adminLogin.getImage();
        }
        Admin admin = new Admin();
        admin.setAdminId(adminLogin.getAdminId());
        admin.setAdminName(req.getParameter(NAME));
        admin.setNumberPhone(req.getParameter(NUMBER_PHONE));
        admin.setSex(req.getParameter(SEX));
        admin.setAddress(req.getParameter(ADDRESS));
        admin.setEmail(adminLogin.getEmail());
        admin.setImage(fileName);
        int rowAffected = adminService.updateAdmin(admin);
        if (rowAffected == 1) {
            req.setAttribute(MESSAGE, "Sửa thông tin tài khoản admin thành công");
            req.setAttribute(ADMIN, admin);
            session.setAttribute(ADMIN_LOGIN, admin);
        } else {
            req.setAttribute(MESSAGE, "Sửa thông tin tài khoản admin thất bại");
            req.setAttribute(ADMIN, admin);
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Admin admin = new Admin();
        admin.setEmail(req.getParameter(EMAIL));
        admin.setPassword(adminService.md5(req.getParameter(PASSWORD)));
        Admin adminLogin = adminService.login(admin);
        if (adminLogin == null) {
            admin.setPassword(req.getParameter(PASSWORD));
            req.setAttribute(ADMIN, admin);
            req.setAttribute("error", "Tên tài khoản hoặc mật khẩu không chính xác");
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/Admin/LoginView.jsp");
            dispatcher.forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute(ADMIN_LOGIN, adminLogin);
            resp.sendRedirect(req.getContextPath() + "/Manage");
        }
    }

    private void changePassword(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Admin adminLogin = (Admin) session.getAttribute(ADMIN_LOGIN);
        Admin admin = new Admin();
        admin.setAdminId(adminLogin.getAdminId());
        admin.setPassword(adminService.md5(req.getParameter(NEW_PASSWORD)));
        int rowAffected = adminService.changePassword(admin);
        if (rowAffected == 1) {
            req.setAttribute(MESSAGE, "Đổi mật khẩu thành công");
        } else {
            Admin2 adminFrontEnd = new Admin2();
            adminFrontEnd.setPassword(req.getParameter(CURRENT_PASSWORD));
            adminFrontEnd.setConfirmPassword(req.getParameter(CONFIRM_PASSWORD));
            adminFrontEnd.setNewPassword(req.getParameter(NEW_PASSWORD));
            req.setAttribute(MESSAGE, "Đổi mật khẩu thất bại");
            req.setAttribute(ADMIN, adminFrontEnd);
        }
    }
}
