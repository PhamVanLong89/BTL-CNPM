package org.example.controller;

import com.google.gson.Gson;
import org.example.model.Variant;
import org.example.model.Variant2;
import org.example.service.AdminService;
import org.example.service.ProductService;
import org.example.service.VariantService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "VariantController", urlPatterns = {"/Variant"})
@MultipartConfig
public class VariantController extends HttpServlet {

    private final VariantService variantService;
    private final ProductService productService;
    private final AdminService adminService;
    private static final long serialVersionUID = 1L;
    private static final String LINK_EDIT_PRODUCT = "/Product?chucNang=sua&productId=";
    private static final String MESSAGE = "message";
    private static final String VARIANT = "variant";
    private static final String PRODUCT_ID = "productId";
    private static final String PRODUCT_QUANTITY = "productQuantity";
    private static final String VIEW_EDIT_VARIANT = "/Admin/EditVariantView.jsp";
    private static final String CONTENT_TYPE = "text/html;charset=UTF-8";
    private static final String QUANTITY = "Quantity";
    private static final String COLOR = "Color";
    private static final String SKU = "SKU";
    private static final String SIZE = "Size";

    public VariantController() {
        super();
        variantService = new VariantService();
        productService = new ProductService();
        adminService = new AdminService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String request = req.getParameter("chucNang");
        try {
            if (request.equals("getSize")) {
                if (productService.checkProductId(req.getParameter(PRODUCT_ID)) == null) {
                    getSizeByColor(req, resp);
                }
                return;
            }
            if (request.equals("getSKU")) {
                if (productService.checkProductId(req.getParameter(PRODUCT_ID)) == null) {
                    getSKUByColorAndSize(req, resp);
                }
                return;
            }
            if (!adminService.checkLogged(req)) {
                resp.sendRedirect(req.getContextPath() + "/Admin?chucNang=dang-nhap");
                return;
            }
            if (request.compareTo("sua") == 0) {
                showViewUpdate(req, resp);
                return;
            }
            if (request.compareTo("getVariantByProductId") == 0) {
                getVariantByProductId(req, resp);
            }
        } catch (IOException | ServletException ex) {
            Logger.getLogger(VariantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showViewUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sKU = req.getParameter(SKU);
        String productId = req.getParameter(PRODUCT_ID);
        Variant variant = variantService.getVariantBySKU(sKU);
        if (variant == null) {
            resp.sendRedirect(req.getContextPath() + LINK_EDIT_PRODUCT + productId);
        } else {
            req.setAttribute(VARIANT, variant);
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(VIEW_EDIT_VARIANT);
            dispatcher.forward(req, resp);
        }
    }

    private void getSizeByColor(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        String color = req.getParameter(COLOR);
        int productId = Integer.parseInt(req.getParameter(PRODUCT_ID));
        List<String> listSize = variantService.getSizeByColor(color, productId);
        new Gson().toJson(listSize, resp.getWriter());
    }

    private void getVariantByProductId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType(CONTENT_TYPE);
        resp.setContentType("application/json");
        String productId = req.getParameter(PRODUCT_ID);
        List<Variant> listVariant = variantService.getVariantByProductId(Integer.parseInt(productId));
        new Gson().toJson(listVariant, resp.getWriter());
    }

    private void getSKUByColorAndSize(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String color = req.getParameter(COLOR);
        String size = req.getParameter(SIZE);
        int productId = Integer.parseInt(req.getParameter(PRODUCT_ID));
        Variant variant = variantService.getVariantByColorAndSize(size, color, productId);
        resp.setContentType(CONTENT_TYPE);
        if (variant == null) {
            resp.getWriter().write("");
        } else {
            String sKU = variant.getSKU();
            resp.getWriter().write(sKU);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String request = req.getParameter("chucNang");
        try {
            if (!adminService.checkLogged(req)) {
                resp.sendRedirect(req.getContextPath() + "/Admin?chucNang=dang-nhap");
                return;
            }
            if (request.compareTo("checkVariant") == 0) {
                checkVariant(req, resp);
                return;
            }
            if (request.compareTo("them") == 0) {
                if (productService.getProductById(req.getParameter(PRODUCT_ID)) == null) {
                    resp.sendRedirect(req.getContextPath() + "/Product?chucNang=hienThi");
                    return;
                }
                Variant2 variantFrontEnd = new Variant2();
                variantFrontEnd.setColor(req.getParameter(COLOR));
                variantFrontEnd.setSize(req.getParameter(SIZE));
                variantFrontEnd.setSKU(req.getParameter(SKU));
                variantFrontEnd.setQuantity(req.getParameter(QUANTITY));
                variantFrontEnd.setProductId(req.getParameter(PRODUCT_ID));
                String error = variantService.checkVariant(variantFrontEnd);
                if (error == null) {
                    insertVariant(req, resp);
                    return;
                }
                resp.setContentType(CONTENT_TYPE);
                resp.getWriter().write(error);
                return;
            }
            if (request.compareTo("sua") == 0) {
                Variant variant = variantService.getVariantBySKU(req.getParameter(SKU));
                if (variant == null) {
                    String productId = req.getParameter(PRODUCT_ID);
                    resp.sendRedirect(req.getContextPath() + LINK_EDIT_PRODUCT + productId);
                    return;
                }
                String error = variantService.checkQuantity(req.getParameter(PRODUCT_QUANTITY));
                if (error == null) {
                    updateVariant(req, resp);
                    return;
                }
                Variant2 variantFrontEnd = new Variant2();
                variantFrontEnd.setSKU(variant.getSKU());
                variantFrontEnd.setColor(variant.getColor());
                variantFrontEnd.setSize(variant.getSize());
                variantFrontEnd.setQuantity(req.getParameter(PRODUCT_QUANTITY));
                variantFrontEnd.setProductId(String.valueOf(variant.getProductId()));
                variantFrontEnd.setImage1(variant.getImage1());
                variantFrontEnd.setImage2(variant.getImage2());
                req.setAttribute("error", error);
                req.setAttribute(VARIANT, variant);
                RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(VIEW_EDIT_VARIANT);
                dispatcher.forward(req, resp);
                return;
            }
            if (request.compareTo("xoa") == 0) {
                deleteVariant(req, resp);
            }
        } catch (IOException | ServletException ex) {
            Logger.getLogger(VariantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void checkVariant(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Variant2 variant = new Variant2();
        variant.setColor(req.getParameter(COLOR));
        variant.setSize(req.getParameter(SIZE));
        variant.setSKU(req.getParameter(SKU));
        variant.setQuantity(req.getParameter(QUANTITY));
        String error = variantService.checkVariant(variant);
        resp.setContentType(CONTENT_TYPE);
        if (error == null) {
            resp.getWriter().write("");
        } else {
            resp.getWriter().write(error);
        }
    }

    private void deleteVariant(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String sKU = req.getParameter(SKU);
        int rowAffected = variantService.deleteVariant(sKU);
        if (rowAffected == 0) {
            resp.getWriter().write("fail");
        } else {
            resp.getWriter().write("success");
        }
    }

    private void insertVariant(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE);
        Part filePart1 = req.getPart("Image1");
        Part filePart2 = req.getPart("Image2");
        String uploads = getServletContext().getRealPath("/img");
        String fileName1 = variantService.getNameImage(filePart1, uploads);
        String fileName2 = variantService.getNameImage(filePart2, uploads);
        Variant variant = new Variant();
        variant.setColor(req.getParameter(COLOR));
        variant.setSize(req.getParameter(SIZE));
        variant.setSKU(req.getParameter(SKU));
        variant.setQuantity(Integer.parseInt(req.getParameter(QUANTITY)));
        variant.setProductId(Integer.parseInt(req.getParameter(PRODUCT_ID)));
        variant.setImage1(fileName1);
        variant.setImage2(fileName2);
        int rowAffected = variantService.insertVariant(variant);
        if (rowAffected == 1) {
            resp.getWriter().write("");
        } else {
            resp.getWriter().write("Thêm biến thể sản phẩm thất bại");
        }
    }

    private void updateVariant(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart1 = req.getPart("product-image1");
        Part filePart2 = req.getPart("product-image2");
        String uploads = getServletContext().getRealPath("/img");
        Variant variant = variantService.getVariantBySKU(req.getParameter(SKU));
        variant.setQuantity(Integer.parseInt(req.getParameter(PRODUCT_QUANTITY)));
        String fileNameNew1 = variantService.getNameImage(filePart1, uploads);
        if (fileNameNew1 != null) {
            variant.setImage1(fileNameNew1);
        }
        String fileNameNew2 = variantService.getNameImage(filePart2, uploads);
        if (fileNameNew2 != null) {
            variant.setImage2(fileNameNew2);
        }
        int rowAffected = variantService.updateVariant(variant);
        if (rowAffected == 1) {
            req.setAttribute(MESSAGE, "Sửa thông tin biến thể sản phẩm thành công");
        } else {
            req.setAttribute(MESSAGE, "Sửa thông tin biến thể sản phẩm thất bại");
        }
        req.setAttribute(VARIANT, variant);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(VIEW_EDIT_VARIANT);
        dispatcher.forward(req, resp);
    }

}

