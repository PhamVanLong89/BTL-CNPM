package org.example.service;

import org.apache.commons.io.FilenameUtils;
import org.example.model.Variant;
import org.example.model.Variant2;
import org.example.repository.VariantRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class VariantService {
    private final VariantRepository variantRepository;

    public VariantService() {
        variantRepository = new VariantRepository();
    }

    public String checkVariant(Variant2 variant) {
        if (variant.getColor().trim().length() == 0) {
            return "Vui lòng chọn màu của biến thể sản phẩm";
        } else if (variant.getColor().length() > 10) {
            return "Tên màu dài quá giới hạn (10 ký tự)";
        }
        if (variant.getSize().trim().length() == 0) {
            return "Vui lòng chọn kích cỡ của biến thể sản phẩm";
        } else if (variant.getSize().length() > 10) {
            return "Tên kích cỡ dài quá giới hạn (10 ký tự)";
        }
        if (variant.getSKU().trim().length() == 0) {
            return "Vui lòng nhập SKU của biến thể";
        } else if (variant.getSKU().length() > 20) {
            return "SKU của biến thể dài quá giới hạn (20 ký tự)";
        } else if (variantRepository.getVariantBySKU(variant.getSKU()) != null) {
            return "SKU của biến thể đã tồn tại";
        }
        String error = checkQuantity(variant.getQuantity());
        if (error != null) {
            return error;
        }
        return null;
    }

    public List<Variant> getListVariant(HttpServletRequest req) {
        String[] listColor = req.getParameterValues("colorVariant");
        String[] listSize = req.getParameterValues("sizeVariant");
        String[] listSKU = req.getParameterValues("SKUVariant");
        String[] listQuantity = req.getParameterValues("quantityVariant");
        List<Variant> listVariant = new ArrayList<>();
        if (listColor == null || listSKU == null || listQuantity == null || listSize == null) {
            return listVariant;
        }
        for (int i = 0; i < listSKU.length; i++) {
            Variant2 variantFrontEnd = new Variant2();
            variantFrontEnd.setColor(listColor[i]);
            variantFrontEnd.setSize(listSize[i]);
            variantFrontEnd.setSKU(listSKU[i]);
            variantFrontEnd.setQuantity(listQuantity[i]);
            if (checkVariant(variantFrontEnd) == null) {
                Variant variant = new Variant();
                variant.setColor(listColor[i]);
                variant.setSize(listSize[i]);
                variant.setSKU(listSKU[i]);
                variant.setQuantity(Integer.parseInt(listQuantity[i]));
                listVariant.add(variant);
            }
        }
        return listVariant;
    }

    public String checkQuantity(String quantity) {
        if (quantity.trim().length() == 0) {
            return "Vui lòng nhập số lượng của biến thể sản phẩm";
        } else {
            try {
                if (Integer.parseInt(quantity) < 1) {
                    return "Số lượng của biến thể sản phẩm không hợp lệ";
                }
            } catch (NumberFormatException e) {
                return "Số lượng của biến thể sản phẩm không đúng định dạng";
            }
        }
        return null;
    }

    public String getNameImage(Part filePart1, String upLoads) {
        String fileName1 = null;
        String fileNameNew1 = null;
        if (filePart1.getSubmittedFileName() != null && filePart1.getSubmittedFileName().length() != 0) {
            fileName1 = Paths.get(filePart1.getSubmittedFileName()).getFileName().toString();
            try {
                fileNameNew1 = FilenameUtils.getBaseName(fileName1) + "-" + System.nanoTime() + "." + FilenameUtils.getExtension(fileName1);
                InputStream fileContent1 = filePart1.getInputStream();
                File file1 = new File(upLoads, fileNameNew1);
                Files.copy(fileContent1, file1.toPath());
            } catch (IOException e) {
                fileNameNew1 = null;
            }
        }
        return fileNameNew1;
    }

    public int insertVariant(Variant variant) {
        return variantRepository.insertVariant(variant);
    }

    public List<Variant> getVariantByProductId(int productId) {
        return variantRepository.getVariantByProductId(productId);
    }

    public Variant getVariantBySKU(String sKU) {
        return variantRepository.getVariantBySKU(sKU);
    }

    public int deleteVariant(String sKU) {
        return variantRepository.deleteVariant(sKU);
    }

    public int updateVariant(Variant variant) {
        return variantRepository.updateVariant(variant);
    }

    public String getOneImageVariantByProductId(int productId) {
        return variantRepository.getOneImageVariantByProductId(productId);
    }

    public List<String> getColorByProductId(int productId) {
        return variantRepository.getColorByProductId(productId);
    }

    public List<String> getImageVariantByProductId(int productId) {
        return variantRepository.getImageVariantByProductId(productId);
    }

    public List<String> getSizeByColor(String color, int productId) {
        return variantRepository.getSizeByColor(color, productId);
    }

    public Variant getVariantByColorAndSize(String size, String color, int productId) {
        return variantRepository.getVariantByColorAndSize(size, color, productId);
    }
}
