package org.example.service;

import org.apache.commons.io.FilenameUtils;
import org.example.model.Admin;
import org.example.repository.AdminRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class AdminService {
    private final AdminRepository adminRepository;
    private static final String CONFIRM_PASSWORD = "confirmPassword";
    private static final String NEW_PASSWORD = "newPassword";

    public AdminService() {
        adminRepository = new AdminRepository();
    }

    public Map<String, String> checkInsertAdmin(HttpServletRequest req) {
        Map<String, String> errors = new HashMap<>();
        String errorName = checkAdminName(req.getParameter("name"));
        if (errorName != null) {
            errors.put("adminName", errorName);
        }
        String errorNumberPhone = checkNumberPhone(req.getParameter("numberPhone"));
        if (errorNumberPhone != null) {
            errors.put("adminNumberPhone", errorNumberPhone);
        }
        String errorSex = checkSexAdmin(req.getParameter("sex"));
        if (errorSex != null) {
            errors.put("adminSex", errorSex);
        }
        String errorAddress = checkAddress(req.getParameter("address"));
        if (errorAddress != null) {
            errors.put("adminAddress", errorAddress);
        }
        String errorEmail = checkEmail(req.getParameter("email"));
        if (errorEmail != null) {
            errors.put("adminEmail", errorEmail);
        }
        String errorPassword = checkPassword(req.getParameter("password"));
        if (errorPassword != null) {
            errors.put("adminPassword", errorPassword);
        }
        String errorConfirmPassword = checkConfirmPassword(req.getParameter("password"), req.getParameter(CONFIRM_PASSWORD));
        if (errorConfirmPassword != null) {
            errors.put("adminConfirmPassword", errorConfirmPassword);
        }
        return errors;
    }

    public Map<String, String> checkUpdateAdmin(HttpServletRequest req) {
        Map<String, String> errors = new HashMap<>();
        String errorName = checkAdminName(req.getParameter("name"));
        if (errorName != null) {
            errors.put("adminName", errorName);
        }
        String errorNumberPhone = checkNumberPhone(req.getParameter("numberPhone"));
        if (errorNumberPhone != null) {
            errors.put("adminNumberPhone", errorNumberPhone);
        }
        String errorSex = checkSexAdmin(req.getParameter("sex"));
        if (errorSex != null) {
            errors.put("adminSex", errorSex);
        }
        String errorAddress = checkAddress(req.getParameter("address"));
        if (errorAddress != null) {
            errors.put("adminAddress", errorAddress);
        }
        return errors;
    }

    public String checkAdminName(String adminName) {
        if (adminName.trim().length() == 0) {
            return "Vui lòng nhập họ tên của admin";
        } else if (adminName.length() > 50) {
            return "Họ tên của admin dài quá giới hạn(50 ký tự)";
        } else {
            return null;
        }

    }

    public String checkSexAdmin(String sex) {
        if (sex.trim().length() == 0) {
            return "Vui lòng chọn giới tính của admin";
        } else if (sex.length() > 6) {
            return "Giới tính của admin dài qúa giới hạn(6 ký tự)";
        } else {
            return null;
        }
    }

    public String checkAddress(String address) {
        if (address.trim().length() == 0) {
            return "Vui lòng nhập địa chỉ của admin";
        } else if (address.length() > 100) {
            return "Địa chỉ của admin dài quá giới hạn(100 ký tự)";
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

    public String checkEmail(String email) {
        String emailPattern = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
        if (email.trim().length() == 0) {
            return "Vui lòng nhập email";
        } else if (!Pattern.matches(emailPattern, email)) {
            return "Email không hợp lệ";
        } else if (adminRepository.getAdminByEmail(email) != null) {
            return "Email đã được sử dụng";
        } else {
            return null;
        }
    }

    public String checkPassword(String password) {
        String passwordPattern = "^[a-z0-9_-]{6,20}$";
        if (password.trim().length() == 0) {
            return "Vui lòng nhập mật khẩu";
        } else if (!Pattern.matches(passwordPattern, password)) {
            return "Mật khẩu phải dài từ 6 đến 20 ký tự";
        } else {
            return null;
        }

    }

    public String checkConfirmPassword(String password, String confirmPassword) {
        if (confirmPassword.trim().length() == 0) {
            return "Vui lòng nhập mật khẩu xác thực";
        } else if (confirmPassword.trim().compareTo(password.trim()) != 0) {
            return "Mật khẩu không khớp";
        } else {
            return null;
        }
    }

    public String getNameImage(Part filePart, String upLoads) {
        String fileName = null;
        String fileNameNew = null;
        if (filePart.getSubmittedFileName() != null && filePart.getSubmittedFileName().length() != 0) {
            fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            try {
                fileNameNew = FilenameUtils.getBaseName(fileName) + "-" + System.nanoTime() + "." + FilenameUtils.getExtension(fileName);
                InputStream fileContent = filePart.getInputStream();
                File file = new File(upLoads, fileNameNew);
                Files.copy(fileContent, file.toPath());
            } catch (IOException e) {
                fileNameNew = null;
            }
        }
        return fileNameNew;
    }

    public boolean checkLogged(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("adminLogin");
        if (admin == null) {
            return false;
        } else {
            return adminRepository.getAdminById(admin.getAdminId()) != null;
        }
    }

    public String md5(String password) {
        String passwordEncoded = null;
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            BigInteger bigInteger = new BigInteger(1, digest.digest());
            passwordEncoded = bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            passwordEncoded = null;
        }
        return passwordEncoded;
    }

    public int insertAdmin(Admin admin) {
        return adminRepository.insertAdmin(admin);
    }

    public List<Admin> getAdmin() {
        return adminRepository.getAdmin();
    }

    public Admin getAdminById(String adminId) {
        try {
            return adminRepository.getAdminById(Integer.parseInt(adminId));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public int updateAdmin(Admin admin) {
        return adminRepository.updateAdmin(admin);
    }

    public Admin login(Admin admin) {
        return adminRepository.login(admin);
    }

    public Map<String, String> checkChangePassword(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Admin adminLogin = (Admin) session.getAttribute("adminLogin");
        Admin admin = adminRepository.getAdminById(adminLogin.getAdminId());
        Map<String, String> errors = new HashMap<>();
        if (admin.getPassword().compareTo(md5(req.getParameter("currentPassword"))) != 0) {
            errors.put("currentPassword", "Mật khẩu hiện tại không chính xác");
        }
        String errorPassword = checkPassword(req.getParameter(NEW_PASSWORD));
        if (errorPassword != null) {
            errors.put(NEW_PASSWORD, errorPassword);
        }
        String errorConfirmPassword = checkConfirmPassword(req.getParameter(NEW_PASSWORD), req.getParameter(CONFIRM_PASSWORD));
        if (errorConfirmPassword != null) {
            errors.put(CONFIRM_PASSWORD, errorConfirmPassword);
        }
        return errors;
    }

    public int changePassword(Admin admin) {
        return adminRepository.changePassword(admin);
    }
}
