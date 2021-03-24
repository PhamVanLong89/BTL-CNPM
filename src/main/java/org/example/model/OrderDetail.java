package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OrderDetail implements Serializable {
    private int orderDetailId;
    private int orderId;
    private Variant variant;
    private Product product;
    private int sale;
    private int amount;
}
