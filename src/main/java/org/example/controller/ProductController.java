package org.example.controller;

import org.apache.commons.math.util.MathUtils;
import org.example.model.Admin;
import org.example.model.Product;
import org.example.model.Product2;
import org.example.model.Variant;
import org.example.service.AdminService;
import org.example.service.ProductService;
import org.example.service.VariantService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebServlet(name = "ProductController", urlPatterns = {"/Product"})
@MultipartConfig
public class ProductController extends HttpServlet {

    private final ProductService productService;
    private final VariantService variantService;
    private final AdminService adminService;
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/Product?chucNang=hienThi";
    private static final String LINK_LOGIN = "/Admin?chucNang=dang-nhap";
    private static final String ERRORS = "errors";
    private static final String MESSAGE = "message";
    private static final String LIST_VARIANT = "listVariant";
    private static final String LIST_PRODUCT = "listProduct";
    private static final String PRODUCT = "product";
    private static final String PRODUCT_ID = "productId";
    private static final String PRODUCT_NAME = "productName";
    private static final String CATEGORY_NAME = "categoryName";
    private static final String PRODUCT_PRICE = "productPrice";
    private static final String PRODUCT_DESCRIPTION = "productDescription";
    private static final String DISPLAY_HOME = "displayHome";
    private static final String PRODUCT_SALE = "productSale";
    private static final String VIEW_EDIT_PRODUCT = "/Admin/EditProductView.jsp";
    private static final String VIEW_ADD_PRODUCT = "/Admin/AddProductView.jsp";
    private static final String CATEGORY = "danhMucSanPham";


    public ProductController() {
        super();
        productService = new ProductService();
        variantService = new VariantService();
        adminService = new AdminService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String request = req.getParameter("chucNang");
        try {
            if (request.equals("loadMore")) {
                loadMoreProduct(req, resp);
                return;
            }
            if (request.equals("hienThiTheoDanhMuc")) {
                int totalProduct = productService.getTotalProductByCategory(req.getParameter(CATEGORY));
                int currentPage = pagination(req, totalProduct);
                showProductByCategory(req, resp, currentPage);
                return;
            }
            if (request.equals("hienThiSanPhamMoi")) {
                int totalProduct = productService.getTotalProductNewByCategory(req.getParameter(CATEGORY));
                int currentPage = pagination(req, totalProduct);
                showProductNew(req, resp, currentPage);
                return;
            }
            if (request.equals("sanPhamGiamGia")) {
                int totalProduct = productService.getTotalProductSaleByCategory(req.getParameter(CATEGORY));
                int currentPage = pagination(req, totalProduct);
                showProductSale(req, resp, currentPage);
                return;
            }
            if (request.equals("chiTietSanPham")) {
                showProductDetail(req);
                showProductSimilar(req, resp);
                return;
            }
            if (!adminService.checkLogged(req)) {
                resp.sendRedirect(req.getContextPath() + LINK_LOGIN);
                return;
            }
            if (request.compareTo("them") == 0) {
                showViewInsert(req, resp);
                return;
            }
            if (request.compareTo("hienThi") == 0) {
                showProduct(req, resp);
                return;
            }
            if (request.compareTo("sua") == 0) {
                showViewUpdate(req, resp);
                return;
            }
            if (request.compareTo("xoa") == 0) {
                showViewDelete(req, resp);
            }
        } catch (IOException | ServletException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void showViewInsert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(VIEW_ADD_PRODUCT);
        dispatcher.forward(req, resp);
    }

    private void showProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> listProduct = productService.getProduct();
        List<Product2> listProductFrontEnd = new ArrayList<>();
        for (int i = 0; i < listProduct.size(); i++) {
            Product2 productFrontEnd = new Product2();
            productFrontEnd.setProductId(String.valueOf(listProduct.get(i).getProductId()));
            productFrontEnd.setProductName(listProduct.get(i).getProductName());
            productFrontEnd.setCategoryName(listProduct.get(i).getCategoryName());
            productFrontEnd.setProductPrice(String.valueOf(listProduct.get(i).getProductPrice()));
            productFrontEnd.setProductSale(String.valueOf(listProduct.get(i).getProductSale()));
            productFrontEnd.setDisplayHome(String.valueOf(listProduct.get(i).getDisplayHome()));
            productFrontEnd.setSaleDate(String.valueOf(listProduct.get(i).getSaleDate()));
            productFrontEnd.setAdminId(String.valueOf(listProduct.get(i).getAdminId()));
            productFrontEnd.setImage(variantService.getOneImageVariantByProductId(listProduct.get(i).getProductId()));
            listProductFrontEnd.add(productFrontEnd);
        }
        req.setAttribute(LIST_PRODUCT, listProductFrontEnd);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Admin/ProductView.jsp");
        dispatcher.forward(req, resp);
    }

    private void showProductByCategory(HttpServletRequest req, HttpServletResponse resp, int currentPage) throws ServletException, IOException {
        String category = req.getParameter(CATEGORY);
        List<Product> listProduct = productService.getProductByCategory(category, (currentPage - 1) * 3);
        List<Product2> listProductFrontEnd = new ArrayList<>();
        for (int i = 0; i < listProduct.size(); i++) {
            Product2 productFrontEnd = new Product2();
            productFrontEnd.setProductId(String.valueOf(listProduct.get(i).getProductId()));
            productFrontEnd.setProductName(listProduct.get(i).getProductName());
            productFrontEnd.setCategoryName(listProduct.get(i).getCategoryName());
            productFrontEnd.setProductPrice(String.valueOf(listProduct.get(i).getProductPrice()));
            productFrontEnd.setProductSale(String.valueOf(listProduct.get(i).getProductSale()));
            productFrontEnd.setDisplayHome(String.valueOf(listProduct.get(i).getDisplayHome()));
            productFrontEnd.setSaleDate(String.valueOf(listProduct.get(i).getSaleDate()));
            productFrontEnd.setAdminId(String.valueOf(listProduct.get(i).getAdminId()));
            productFrontEnd.setImage(variantService.getOneImageVariantByProductId(listProduct.get(i).getProductId()));
            listProductFrontEnd.add(productFrontEnd);
        }
        req.setAttribute(CATEGORY_NAME, category);
        req.setAttribute(LIST_PRODUCT, listProductFrontEnd);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Customer/ListProduct.jsp");
        dispatcher.forward(req, resp);
    }

    private int pagination(HttpServletRequest req, int totalProduct) {
        int currentPage = 1;
        if (req.getParameter("page") != null) {
            try {
                currentPage = Integer.parseInt(req.getParameter("page"));

            } catch (Exception ex) {
                currentPage = 1;
            }
        }
        int totalPage = (int) Math.ceil(totalProduct / 3.0);
        if (totalPage < currentPage) {
            currentPage = totalPage;
        } else if (currentPage < 1) {
            currentPage = 1;
        }
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("totalPage", totalPage);
        return currentPage;
    }

    private void showProductNew(HttpServletRequest req, HttpServletResponse resp, int currentPage) throws ServletException, IOException {
        String category = req.getParameter(CATEGORY);
        List<Product> listProduct = productService.getProductNewByCategory(category, (currentPage - 1) * 3);
        List<Product2> listProductFrontEnd = new ArrayList<>();
        for (int i = 0; i < listProduct.size(); i++) {
            Product2 productFrontEnd = new Product2();
            productFrontEnd.setProductId(String.valueOf(listProduct.get(i).getProductId()));
            productFrontEnd.setProductName(listProduct.get(i).getProductName());
            productFrontEnd.setCategoryName(listProduct.get(i).getCategoryName());
            productFrontEnd.setProductPrice(String.valueOf(listProduct.get(i).getProductPrice()));
            productFrontEnd.setProductSale(String.valueOf(listProduct.get(i).getProductSale()));
            productFrontEnd.setDisplayHome(String.valueOf(listProduct.get(i).getDisplayHome()));
            productFrontEnd.setSaleDate(String.valueOf(listProduct.get(i).getSaleDate()));
            productFrontEnd.setAdminId(String.valueOf(listProduct.get(i).getAdminId()));
            productFrontEnd.setImage(variantService.getOneImageVariantByProductId(listProduct.get(i).getProductId()));
            listProductFrontEnd.add(productFrontEnd);
        }
        req.setAttribute(CATEGORY_NAME, category);
        req.setAttribute(LIST_PRODUCT, listProductFrontEnd);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Customer/ListProductNew.jsp");
        dispatcher.forward(req, resp);
    }

    private void showProductSale(HttpServletRequest req, HttpServletResponse resp, int currentPage) throws ServletException, IOException {
        String category = req.getParameter(CATEGORY);
        List<Product> listProduct = productService.getProductSaleByCategory(category, (currentPage - 1) * 3);
        List<Product2> listProductFrontEnd = new ArrayList<>();
        for (int i = 0; i < listProduct.size(); i++) {
            Product2 productFrontEnd = new Product2();
            productFrontEnd.setProductId(String.valueOf(listProduct.get(i).getProductId()));
            productFrontEnd.setProductName(listProduct.get(i).getProductName());
            productFrontEnd.setCategoryName(listProduct.get(i).getCategoryName());
            productFrontEnd.setProductPrice(String.valueOf(listProduct.get(i).getProductPrice()));
            productFrontEnd.setProductSale(String.valueOf(listProduct.get(i).getProductSale()));
            productFrontEnd.setDisplayHome(String.valueOf(listProduct.get(i).getDisplayHome()));
            productFrontEnd.setSaleDate(String.valueOf(listProduct.get(i).getSaleDate()));
            productFrontEnd.setAdminId(String.valueOf(listProduct.get(i).getAdminId()));
            productFrontEnd.setImage(variantService.getOneImageVariantByProductId(listProduct.get(i).getProductId()));
            listProductFrontEnd.add(productFrontEnd);
        }
        req.setAttribute(CATEGORY_NAME, category);
        req.setAttribute(LIST_PRODUCT, listProductFrontEnd);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Customer/ListProductSale.jsp");
        dispatcher.forward(req, resp);
    }

    private void showProductDetail(HttpServletRequest req) {
        String productId = req.getParameter(PRODUCT_ID);
        Product product = productService.getProductById(productId);
        if (product != null) {
            int newPrice = (int) (product.getProductPrice() - MathUtils.round((product.getProductPrice() * product.getProductSale() / 100.0), -3));
            List<String> listColor = variantService.getColorByProductId(product.getProductId());
            List<String> listImage = variantService.getImageVariantByProductId(product.getProductId());
            req.setAttribute(PRODUCT, product);
            req.setAttribute("listColor", listColor);
            req.setAttribute("listImage", listImage);
            req.setAttribute("newPrice", newPrice);
        }
    }

    private void showProductSimilar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = productService.getProductById(req.getParameter(PRODUCT_ID));
        if (product == null) {
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            List<Product> listProduct = productService.getProductByCategory(product.getCategoryName());
            List<Product2> listProductFrontEnd = new ArrayList<>();
            for (int i = 0; i < listProduct.size(); i++) {
                if (listProduct.get(i).getProductId() != product.getProductId()) {
                    Product2 productFrontEnd = new Product2();
                    productFrontEnd.setProductId(String.valueOf(listProduct.get(i).getProductId()));
                    productFrontEnd.setProductName(listProduct.get(i).getProductName());
                    productFrontEnd.setCategoryName(listProduct.get(i).getCategoryName());
                    productFrontEnd.setProductPrice(String.valueOf(listProduct.get(i).getProductPrice()));
                    productFrontEnd.setProductSale(String.valueOf(listProduct.get(i).getProductSale()));
                    productFrontEnd.setDisplayHome(String.valueOf(listProduct.get(i).getDisplayHome()));
                    productFrontEnd.setSaleDate(String.valueOf(listProduct.get(i).getSaleDate()));
                    productFrontEnd.setAdminId(String.valueOf(listProduct.get(i).getAdminId()));
                    productFrontEnd.setImage(variantService.getOneImageVariantByProductId(listProduct.get(i).getProductId()));
                    listProductFrontEnd.add(productFrontEnd);
                }
            }
            req.setAttribute(LIST_PRODUCT, listProductFrontEnd);
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/Customer/ProductDetail.jsp");
            dispatcher.forward(req, resp);
        }
    }


    private void showViewUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter(PRODUCT_ID);
        Product product = productService.getProductById(productId);
        if (product == null) {
            resp.sendRedirect(req.getContextPath() + VIEW);
        } else {
            req.setAttribute(PRODUCT, product);
            List<Variant> listVariant = variantService.getVariantByProductId(product.getProductId());
            req.setAttribute(LIST_VARIANT, listVariant);
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(VIEW_EDIT_PRODUCT);
            dispatcher.forward(req, resp);
        }
    }

    private void showViewDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter(PRODUCT_ID);
        Product product = productService.getProductById(productId);
        if (product == null) {
            resp.sendRedirect(req.getContextPath() + VIEW);
        } else {
            req.setAttribute(PRODUCT, product);
            List<Variant> listVariant = variantService.getVariantByProductId(product.getProductId());
            req.setAttribute(LIST_VARIANT, listVariant);
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/Admin/DeleteProductView.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void loadMoreProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int amountProduct = 0;
        try {
            amountProduct = Integer.parseInt(req.getParameter("amountProduct"));
        } catch (Exception ex) {
            amountProduct = 0;
        }
        resp.setContentType("text/html;charset=UTF-8");
        List<Product> listProduct = productService.getNext6ProductHome(amountProduct);
        for (int i = 0; i < listProduct.size(); i++) {
            String image = variantService.getOneImageVariantByProductId(listProduct.get(i).getProductId());
            if (listProduct.get(i).getProductSale() == 0) {
                resp.getWriter().write("<div class=\"product col-md-4 col-6 p-1 p-md-3\">\n" +
                        "                        <div class=\"card card-product mb-3\">\n" +
                        "                            <a href=\"Product?chucNang=chiTietSanPham&productId=" + listProduct.get(i).getProductId() + "\"><img class=\"card-img-top p-md-3\" src=\"img/" + image + "\" alt=\"product 4\" /></a>\n" +
                        "                            <div class=\"card-body\">\n" +
                        "                                <p class=\"card-title text-center text-truncate\"><a href=\"Product?chucNang=chiTietSanPham&productId=" + listProduct.get(i).getProductId() + "\" class=\"text-decoration-none\">" + listProduct.get(i).getProductName() + "</a></p>\n" +
                        "                                <h5 class=\"card-text font-weight-bold text-center\">" + listProduct.get(i).getProductPrice() + "đ</h5>\n" +
                        "                            </div>\n" +
                        "                        </div>\n" +
                        "                    </div>");
            } else {
                resp.getWriter().write("<div class=\"product col-md-4 col-6 p-1 p-md-3\">\n" +
                        "                            <div class=\"card\">\n" +
                        "                                    <div class='ribbon-wrapper'>\n" +
                        "                                        <div class='ribbon'>Giảm " + listProduct.get(i).getProductSale() + "%</div>\n" +
                        "                                    </div>\n" +
                        "                                    <a href=\"Product?chucNang=chiTietSanPham&productId=" + listProduct.get(i).getProductId() + "\"><img class=\"card-img-top p-md-3\" src=\"img/" + image + "\" alt=\"product 1\" /></a>\n" +
                        "                                    <div class=\"card-body\">\n" +
                        "                                        <p class=\"card-title text-center text-truncate\"><a href=\"Product?chucNang=chiTietSanPham&productId=" + listProduct.get(i).getProductId() + "\" class=\"text-decoration-none\">" + listProduct.get(i).getProductName() + "</a></p>\n" +
                        "                                        <h5 class=\"card-text font-weight-bold text-center\">" + listProduct.get(i).getProductPrice() + "đ</h5>\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                            </div>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String request = req.getParameter("chucNang");
        try {
            if (!adminService.checkLogged(req)) {
                resp.sendRedirect(req.getContextPath() + LINK_LOGIN);
                return;
            }

            if (request.compareTo("them") == 0) {
                Map<String, String> errors = productService.checkInsertProduct(req);
                if (errors.isEmpty()) {
                    addProduct(req, resp);
                    return;
                }
                Product2 productFrontEnd = new Product2();
                productFrontEnd.setProductName(req.getParameter(PRODUCT_NAME));
                productFrontEnd.setCategoryName(req.getParameter(CATEGORY_NAME));
                productFrontEnd.setProductPrice(req.getParameter(PRODUCT_PRICE));
                productFrontEnd.setDescription(req.getParameter(PRODUCT_DESCRIPTION));
                productFrontEnd.setProductSale(req.getParameter(PRODUCT_SALE));
                productFrontEnd.setDisplayHome(req.getParameter(DISPLAY_HOME));
                List<Variant> variantList = variantService.getListVariant(req);
                req.setAttribute(PRODUCT, productFrontEnd);
                req.setAttribute(LIST_VARIANT, variantList);
                req.setAttribute(ERRORS, errors);
                RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(VIEW_ADD_PRODUCT);
                dispatcher.forward(req, resp);
                return;
            }

            if (request.compareTo("sua") == 0) {
                if (productService.getProductById(req.getParameter(PRODUCT_ID)) == null) {
                    resp.sendRedirect(req.getContextPath() + VIEW);
                    return;
                }
                Map<String, String> errors = productService.checkUpdateProduct(req);
                if (errors.isEmpty()) {
                    updateProduct(req, resp);
                    return;
                }
                Product2 productFrontEnd = new Product2();
                productFrontEnd.setProductId(req.getParameter(PRODUCT_ID));
                productFrontEnd.setProductName(req.getParameter(PRODUCT_NAME));
                productFrontEnd.setCategoryName(req.getParameter(CATEGORY_NAME));
                productFrontEnd.setProductPrice(req.getParameter(PRODUCT_PRICE));
                productFrontEnd.setDescription(req.getParameter(PRODUCT_DESCRIPTION));
                productFrontEnd.setProductSale(req.getParameter(PRODUCT_SALE));
                productFrontEnd.setDisplayHome(req.getParameter(DISPLAY_HOME));
                List<Variant> variantList = variantService.getVariantByProductId(Integer.parseInt(req.getParameter(PRODUCT_ID)));
                req.setAttribute(PRODUCT, productFrontEnd);
                req.setAttribute(LIST_VARIANT, variantList);
                req.setAttribute(ERRORS, errors);
                RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(VIEW_EDIT_PRODUCT);
                dispatcher.forward(req, resp);
                return;
            }

            if (request.compareTo("xoa") == 0) {
                deleteProduct(req, resp);
            }

        } catch (IOException | ServletException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Admin adminLogin = (Admin) session.getAttribute("adminLogin");
        Product product = new Product();
        product.setProductName(req.getParameter(PRODUCT_NAME));
        product.setCategoryName(req.getParameter(CATEGORY_NAME));
        product.setProductPrice(Integer.parseInt(req.getParameter(PRODUCT_PRICE)));
        product.setSaleDate(Date.valueOf(java.time.LocalDate.now()));
        product.setDescription(req.getParameter(PRODUCT_DESCRIPTION));
        if (req.getParameter(PRODUCT_SALE).trim().length() == 0) {
            product.setProductSale(0);
        } else {
            product.setProductSale(Integer.parseInt(req.getParameter(PRODUCT_SALE)));
        }
        product.setDisplayHome(Integer.parseInt(req.getParameter(DISPLAY_HOME)));
        product.setAdminId(adminLogin.getAdminId());

        List<Variant> variantList = variantService.getListVariant(req);

        int rowAffected = productService.insertProduct(product);
        if (rowAffected == 1) {
            int productId = productService.getProductIdNew();
            String uploads = getServletContext().getRealPath("/img");
            List<Part> fileParts1 = req.getParts().stream().filter(part -> "product-image1".equals(part.getName())).collect(Collectors.toList());
            List<Part> fileParts2 = req.getParts().stream().filter(part -> "product-image2".equals(part.getName())).collect(Collectors.toList());
            for (int i = 0; i < variantList.size(); i++) {
                variantList.get(i).setProductId(productId);
                variantList.get(i).setImage1(variantService.getNameImage(fileParts1.get(i), uploads));
                variantList.get(i).setImage2(variantService.getNameImage(fileParts2.get(i), uploads));
                variantService.insertVariant(variantList.get(i));
            }
            req.setAttribute(MESSAGE, "Thêm mới sản phẩm thành công");
        } else {
            req.setAttribute(MESSAGE, "Thêm mới sản phẩm thất bại");
            req.setAttribute(PRODUCT, product);
            req.setAttribute(LIST_VARIANT, variantList);
        }
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(VIEW_ADD_PRODUCT);
        dispatcher.forward(req, resp);
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter(PRODUCT_ID);
        int rowAffected = productService.deleteProduct(productId);
        if (rowAffected == 0) {
            Product product = productService.getProductById(productId);
            if (product == null) {
                resp.sendRedirect(req.getContextPath() + VIEW);
            } else {
                req.setAttribute("error", "Xóa sản phẩm thất bại");
                List<Variant> listVariant = variantService.getVariantByProductId(product.getProductId());
                req.setAttribute(PRODUCT, product);
                req.setAttribute(LIST_VARIANT, listVariant);
                RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/Admin/DeleteProductView.jsp");
                dispatcher.forward(req, resp);
            }
        } else {
            resp.sendRedirect(req.getContextPath() + VIEW);
        }
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        product.setProductId(Integer.parseInt(req.getParameter(PRODUCT_ID)));
        product.setProductName(req.getParameter(PRODUCT_NAME));
        product.setCategoryName(req.getParameter(CATEGORY_NAME));
        product.setProductPrice(Integer.parseInt(req.getParameter(PRODUCT_PRICE)));
        product.setDescription(req.getParameter(PRODUCT_DESCRIPTION));
        if (req.getParameter(PRODUCT_SALE).trim().length() == 0) {
            product.setProductSale(0);
        } else {
            product.setProductSale(Integer.parseInt(req.getParameter(PRODUCT_SALE)));
        }
        product.setDisplayHome(Integer.parseInt(req.getParameter(DISPLAY_HOME)));

        int rowAffected = productService.updateProduct(product);
        if (rowAffected == 1) {
            req.setAttribute(MESSAGE, "Sửa thông tin sản phẩm thành công");
        } else {
            req.setAttribute(MESSAGE, "Sửa thông tin sản phẩm thất bại");
        }
        List<Variant> variantList = variantService.getVariantByProductId(product.getProductId());
        req.setAttribute(PRODUCT, product);
        req.setAttribute(LIST_VARIANT, variantList);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(VIEW_EDIT_PRODUCT);
        dispatcher.forward(req, resp);
    }

}

