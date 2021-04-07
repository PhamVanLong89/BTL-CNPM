package org.example.service;

import org.example.model.Product;
import org.example.model.Product2;
import org.example.repository.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {

    private ProductRepository productRepository;
    private VariantService variantService;
    private static final String DISPLAY_HOME = "displayHome";
    private static final String PRODUCT_SALE = "productSale";
    private static final String PRODUCT_PRICE = "productPrice";
    private static final String CATEGORY_NAME = "categoryName";
    private static final String PRODUCT_NAME = "productName";

    public ProductService() {
        productRepository = new ProductRepository();
        variantService = new VariantService();
    }

    public Map<String, String> checkInsertProduct(HttpServletRequest req) {
        Map<String, String> errors = new HashMap<>();
        String errorName = checkProductName(req.getParameter(PRODUCT_NAME));
        if (errorName != null) {
            errors.put(PRODUCT_NAME, errorName);
        }
        String errorCategory = checkCategory(req.getParameter(CATEGORY_NAME));
        if (errorCategory != null) {
            errors.put(CATEGORY_NAME, errorCategory);
        }
        String errorPrice = checkProductPrice(req.getParameter(PRODUCT_PRICE));
        if (errorPrice != null) {
            errors.put(PRODUCT_PRICE, errorPrice);
        }
        String errorSale = checkProductSale(req.getParameter(PRODUCT_SALE));
        if (errorSale != null) {
            errors.put(PRODUCT_SALE, errorSale);
        }
        String errorDisplay = checkDisplayHome(req.getParameter(DISPLAY_HOME));
        if (errorDisplay != null) {
            errors.put(DISPLAY_HOME, errorDisplay);
        }
        if (variantService.getListVariant(req).isEmpty()) {
            errors.put("variant", "Vui lòng thêm ít nhất 1 biến thể sản phẩm hợp lệ");
        }
        return errors;
    }

    public Map<String, String> checkUpdateProduct(HttpServletRequest req) {
        Map<String, String> errors = new HashMap<>();
        String errorId = checkProductId(req.getParameter("productId"));
        if (errorId != null) {
            errors.put("productId", errorId);
        }
        String errorName = checkProductName(req.getParameter(PRODUCT_NAME));
        if (errorName != null) {
            errors.put(PRODUCT_NAME, errorName);
        }
        String errorCategory = checkCategory(req.getParameter(CATEGORY_NAME));
        if (errorCategory != null) {
            errors.put(CATEGORY_NAME, errorCategory);
        }
        String errorPrice = checkProductPrice(req.getParameter(PRODUCT_PRICE));
        if (errorPrice != null) {
            errors.put(PRODUCT_PRICE, errorPrice);
        }
        String errorSale = checkProductSale(req.getParameter(PRODUCT_SALE));
        if (errorSale != null) {
            errors.put(PRODUCT_SALE, errorSale);
        }
        String errorDisplay = checkDisplayHome(req.getParameter(DISPLAY_HOME));
        if (errorDisplay != null) {
            errors.put(DISPLAY_HOME, errorDisplay);
        }
        return errors;
    }

    public String checkProductName(String productName) {
        if (productName.trim().length() == 0) {
            return "Vui lòng nhập tên sản phẩm";
        } else if (productName.length() > 50) {
            return "Tên sản phẩm dài quá giới hạn(50 ký tự)";
        } else {
            return null;
        }
    }

    public String checkCategory(String category) {
        if (category.trim().length() == 0) {
            return "Vui lòng chọn loại sản phẩm";
        } else {
            return null;
        }
    }

    public String checkProductPrice(String productPrice) {
        if (productPrice.trim().length() == 0) {
            return "Vui lòng nhập giá bán sản phẩm";
        } else {
            try {
                if (Integer.parseInt(productPrice) < 0) {
                    return "Giá bán sản phẩm không hợp lệ";
                } else {
                    return null;
                }
            } catch (NumberFormatException e) {
                return "Giá bán sản phẩm không đúng định dạng";
            }
        }
    }

    public String checkProductSale(String productSale) {
        if (productSale.trim().length() > 0) {
            try {
                if (Integer.parseInt(productSale) < 0 || Integer.parseInt(productSale) > 100) {
                    return "Giảm giá sản phẩm không hợp lệ";
                } else {
                    return null;
                }
            } catch (NumberFormatException e) {
                return "Giảm giá sản phẩm không đúng định dạng";
            }
        } else {
            return null;
        }
    }

    public String checkDisplayHome(String displayHome) {
        if (displayHome.compareTo("1") != 0 && displayHome.compareTo("0") != 0) {
            return "Vui lòng chọn sản phẩm có được hiển thị lên trang chủ hay không";
        } else {
            return null;
        }
    }


    public String checkProductId(String productId) {
        try {
            Integer.parseInt(productId);
            return null;
        } catch (NumberFormatException e) {
            return "Mã sản phẩm không đúng định dạng";
        }
    }

    public int insertProduct(Product product) {
        return productRepository.insertProduct(product);
    }

    public int getProductIdNew() {
        return productRepository.getProductIdNew();
    }

    public List<Product> getProduct() {
        return productRepository.getProduct();
    }

    public Product getProductById(String productId) {
        try {
            return productRepository.getProductById(Integer.parseInt(productId));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public int deleteProduct(String productId) {
        try {
            return productRepository.deleteProduct(Integer.parseInt(productId));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }

    public List<Product> getTop6ProductHome() {
        return productRepository.getTop6ProductHome();
    }

    public List<Product> getNext6ProductHome(int amountProduct) {
        return productRepository.getNext6ProductHome(amountProduct);
    }

    public List<Product> getProductByCategory(String category, String minPrice, String maxPrice) {
        try {
            if (minPrice != null && maxPrice != null) {
                Integer.parseInt(minPrice);
                Integer.parseInt(maxPrice);
            }
            return productRepository.getProductByCategory(category, minPrice, maxPrice);
        } catch (NumberFormatException ex) {
            return Collections.emptyList();
        }
    }

    public List<Product> filterProductByPrice(String minPrice, String maxPrice) {
        try {
            if (minPrice != null && maxPrice != null) {
                Integer.parseInt(minPrice);
                Integer.parseInt(maxPrice);
            }
            return productRepository.filterProductByPrice(minPrice, maxPrice);
        } catch (NumberFormatException ex) {
            return Collections.emptyList();
        }
    }

    public List<Product> getProductByCategory(String category, String minPrice, String maxPrice, int indexProduct) {
        try {
            if (minPrice != null && maxPrice != null) {
                Integer.parseInt(minPrice);
                Integer.parseInt(maxPrice);
            }
            return productRepository.getProductByCategory(category, minPrice, maxPrice, indexProduct);
        } catch (NumberFormatException ex) {
            return Collections.emptyList();
        }
    }

    public List<Product> getProductNewByCategory(String category, String minPrice, String maxPrice, int indexProduct) {
        try {
            if (minPrice != null && maxPrice != null) {
                Integer.parseInt(minPrice);
                Integer.parseInt(maxPrice);
            }
            return productRepository.getProductNewByCategory(category, minPrice, maxPrice, indexProduct);
        } catch (NumberFormatException ex) {
            return Collections.emptyList();
        }
    }

    public int getTotalProductByCategory(String category, String minPrice, String maxPrice) {
        try {
            if (minPrice != null && maxPrice != null) {
                Integer.parseInt(minPrice);
                Integer.parseInt(maxPrice);
            }
            return productRepository.getTotalProductByCategory(category, minPrice, maxPrice);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public int getTotalProductNewByCategory(String category, String minPrice, String maxPrice) {
        try {
            if (minPrice != null && maxPrice != null) {
                Integer.parseInt(minPrice);
                Integer.parseInt(maxPrice);
            }
            return productRepository.getTotalProductNewByCategory(category, minPrice, maxPrice);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public List<Product> getProductByCategory(String category) {
        return productRepository.getProductByCategory(category);
    }

    public List<Product> getProductSaleByCategory(String category, String minPrice, String maxPrice, int indexProduct) {
        try {
            if (minPrice != null && maxPrice != null) {
                Integer.parseInt(minPrice);
                Integer.parseInt(maxPrice);
            }
            return productRepository.getProductSaleByCategory(category, minPrice, maxPrice, indexProduct);
        } catch (NumberFormatException ex) {
            return Collections.emptyList();
        }
    }

    public int getTotalProductSaleByCategory(String category, String minPrice, String maxPrice) {
        try {
            if (minPrice != null && maxPrice != null) {
                Integer.parseInt(minPrice);
                Integer.parseInt(maxPrice);
            }
            return productRepository.getTotalProductSaleByCategory(category, minPrice, maxPrice);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    // tìm kiếm sản phẩm có sử dụng phân trang
    public List<Product> searchProduct(String productName, int indexProduct) {
        if (productName == null || productName.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return productRepository.searchProduct(productName, indexProduct);
    }

    //tìm kiếm sản phẩm không sử dụng phân trang
    public List<Product> searchProduct(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return productRepository.searchProduct(productName);
    }

    public int getTotalSearchProduct(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            return 0;
        }
        return productRepository.getTotalSearchProduct(productName);
    }

    public int pagination(HttpServletRequest req, int totalProduct) {
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

    public Product2 convertProductToProductFrontEnd(Product product) {
        Product2 productFrontEnd = new Product2();
        productFrontEnd.setProductId(String.valueOf(product.getProductId()));
        productFrontEnd.setProductName(product.getProductName());
        productFrontEnd.setCategoryName(product.getCategoryName());
        productFrontEnd.setProductPrice(String.valueOf(product.getProductPrice()));
        productFrontEnd.setProductSale(String.valueOf(product.getProductSale()));
        productFrontEnd.setDisplayHome(String.valueOf(product.getDisplayHome()));
        productFrontEnd.setSaleDate(String.valueOf(product.getSaleDate()));
        productFrontEnd.setAdminId(String.valueOf(product.getAdminId()));
        productFrontEnd.setImage(variantService.getOneImageVariantByProductId(product.getProductId()));
        return productFrontEnd;
    }

    public List<Product> sortProduct(String columnName, String type) {
        if (columnName == null || type == null) {
            return Collections.emptyList();
        }
        return productRepository.sortProduct(columnName, type);
    }

}
