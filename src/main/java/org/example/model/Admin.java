package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Admin implements Serializable {
    private int adminId;
    private String adminName;
    private String numberPhone;
    private String sex;
    private String address;
    private String email;
    private String password;
    private String image;

}