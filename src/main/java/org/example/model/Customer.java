package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Customer implements Serializable {
    private int customerId;
    private String customerName;
    private String numberPhone;
    private String address;
    private String email;
    private String password;
    private int statusActive;
}
