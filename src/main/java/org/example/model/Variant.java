package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

public class Variant implements Serializable {

    private int productId;
    private String sKU;
    private String color;
    private String size;
    private int quantity;
    private String image1;
    private String image2;
}
