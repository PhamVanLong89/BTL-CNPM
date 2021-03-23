package org.example.service;

import org.example.model.Customer;
import org.example.repository.CustomerRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class CustomerService {
    private final CustomerRepository customerRepository;
    private static final String CONFIRM_PASSWORD = "confirmPassword";
    private static final String NEW_PASSWORD = "newPassword";

    public CustomerService() {
        customerRepository = new CustomerRepository();
    }

    public Map<String, String> checkRegister(HttpServletRequest req) {
        Map<String, String> errors = new HashMap<>();
        String errorName = checkUserName(req.getParameter("customerName"));
        if (errorName != null) {
            errors.put("userName", errorName);
        }
        String errorNumberPhone = checkNumberPhone(req.getParameter("numberPhone"));
        if (errorNumberPhone != null) {
            errors.put("userNumberPhone", errorNumberPhone);
        }
        String errorAddress = checkAddress(req.getParameter("address"));
        if (errorAddress != null) {
            errors.put("userAddress", errorAddress);
        }
        String errorEmail = checkEmail(req.getParameter("email"));
        if (errorEmail != null) {
            errors.put("userEmail", errorEmail);
        }
        String errorPassword = checkPassword(req.getParameter("password"));
        if (errorPassword != null) {
            errors.put("userPassword", errorPassword);
        }
        String errorConfirmPassword = checkConfirmPassword(req.getParameter("password"), req.getParameter(CONFIRM_PASSWORD));
        if (errorConfirmPassword != null) {
            errors.put("userConfirmPassword", errorConfirmPassword);
        }
        return errors;
    }

    public Map<String, String> checkUpdateUser(HttpServletRequest req) {
        Map<String, String> errors = new HashMap<>();
        String errorName = checkUserName(req.getParameter("customerName"));
        if (errorName != null) {
            errors.put("userName", errorName);
        }
        String errorNumberPhone = checkNumberPhone(req.getParameter("numberPhone"));
        if (errorNumberPhone != null) {
            errors.put("userNumberPhone", errorNumberPhone);
        }
        String errorAddress = checkAddress(req.getParameter("address"));
        if (errorAddress != null) {
            errors.put("userAddress", errorAddress);
        }
        return errors;
    }

    public String checkUserName(String userName) {
        if (userName.trim().length() == 0) {
            return "Vui lòng nhập họ tên";
        } else if (userName.length() > 50) {
            return "Họ tên dài quá giới hạn(50 ký tự)";
        } else {
            return null;
        }

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

    public boolean checkLogged(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        if (customer == null) {
            return false;
        } else {
            return customerRepository.getCustomerById(customer.getCustomerId()) != null;
        }
    }

    public String checkEmail(String email) {
        String emailPattern = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
        if (email.trim().length() == 0) {
            return "Vui lòng nhập email";
        } else if (!Pattern.matches(emailPattern, email)) {
            return "Email không hợp lệ";
        } else if (customerRepository.getCustomerByEmail(email) != null) {
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

    public String checkStatusAccount(HttpServletRequest req) {
        if (req.getParameter("statusActive").compareTo("1") != 0 && req.getParameter("statusActive").compareTo("0") != 0) {
            return "Trạng thái tài khoản không hợp lệ";
        } else {
            return null;
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

    public int insertUser(Customer customer) {
        return customerRepository.insertUser(customer);
    }

    public Customer checkLogin(Customer customer) {
        return customerRepository.login(customer);
    }

    public Customer getCustomerById(String customerId) {
        try {
            return customerRepository.getCustomerById(Integer.parseInt(customerId));
        } catch (Exception ex) {
            return null;
        }
    }

    public int updateCustomer(Customer customer) {
        return customerRepository.updateCustomer(customer);
    }

    public Map<String, String> checkChangePassword(HttpServletRequest req) {
        Map<String, String> errors = new HashMap<>();
        HttpSession session = req.getSession();
        Customer customerLogin = (Customer) session.getAttribute("user");
        Customer customer = customerRepository.getCustomerById(customerLogin.getCustomerId());
        if (customer.getPassword().compareTo(md5(req.getParameter("currentPassword"))) != 0) {
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

    public int changePassword(Customer customer) {
        return customerRepository.changePassword(customer);
    }

    public List<Customer> getCustomers() {
        return customerRepository.getCustomer();
    }

    public int changeStatusAccount(Customer customer) {
        return customerRepository.changeStatusAccount(customer);
    }
}
