package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
/**
 *
 * @author long
 */
public class Product implements Serializable {
    private int productId;
    private String productName;
    private int productPrice;
    private int productSale;
    private String categoryName;
    private Date saleDate;
    private String description;
    private int adminId;
    private int displayHome;
}
