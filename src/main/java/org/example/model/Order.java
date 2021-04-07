package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Order {
    private int orderId;
    private Date orderDate;
    private Customer customer;
    private String paymentsMethod;
    private String orderStatus;
    private String orderAddress;
    private String orderNumberPhone;
    private int totalPrice;
    private int totalProduct;
}

