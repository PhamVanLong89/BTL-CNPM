package org.example.service;

import org.example.model.Variant;
import org.example.repository.ProductRepository;
import org.example.repository.VariantRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDetailService {
    private final VariantRepository variantRepository;
    private final ProductRepository productRepository;

    public OrderDetailService() {
        variantRepository = new VariantRepository();
        productRepository = new ProductRepository();
    }

    public Map<String, String> checkAddProductToCart(HttpServletRequest req) {
        Map<String, String> errors = new HashMap<>();
        int productId = Integer.parseInt(req.getParameter("productId"));
        String color = req.getParameter("Color");
        String size = req.getParameter("Size");
        String quantity = req.getParameter("quantity");
        String errorColor = checkColor(color, productId);
        if (errorColor != null) {
            errors.put("errorColor", errorColor);
        }
        String errorSize = checkSize(size, color, productId);
        if (errorSize != null) {
            errors.put("errorSize", errorSize);
        }
        Variant variant = variantRepository.getVariantByColorAndSize(size, color, productId);
        String errorQuantity = checkQuantity(String.valueOf(convertQuantity(quantity)), variant);
        if (errorQuantity != null) {
            errors.put("errorQuantity", errorQuantity);
        }
        return errors;
    }

    public String checkColor(String color, int productId) {
        if (color.trim().length() == 0) {
            return "Vui lòng chọn màu của sản phẩm";
        }
        List<String> listColor = variantRepository.getColorByProductId(productId);
        if (!listColor.contains(color)) {
            return "Sản phẩm không có màu cần chọn. Vui lòng chọn lại";
        }
        return null;
    }

    public String checkSize(String size, String color, int productId) {
        if (size.trim().length() == 0) {
            return "Vui lòng chọn kích cỡ của sản phẩm";
        }
        List<String> listSize = variantRepository.getSizeByColor(color, productId);
        if (!listSize.contains(size)) {
            return "Sản phẩm không có kích cỡ cần chọn. Vui lòng chọn lại";
        }
        return null;
    }

    public int convertQuantity(String quantity) {
        try {
            int quantityInt = Integer.parseInt(quantity);
            if (quantityInt > 1) {
                return quantityInt;
            }
        } catch (NumberFormatException e) {
            return 1;
        }
        return 1;
    }

    public String checkQuantity(String quantity, Variant variant) {
        if (variant == null) {
            return null;
        }
        try {
            if (Integer.parseInt(quantity) < 1) {
                return "Số lượng không hợp lệ";
            }
        } catch (NumberFormatException e) {
            return "Số lượng không đúng định dạng";
        }
        if (Integer.parseInt(quantity) > variant.getQuantity()) {
            return "Số lượng sản phẩm không thể đáp ứng. Vui lòng chọn lại số lượng";
        }
        return null;
    }

}
