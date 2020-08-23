package com.atheesh.samplejavawebapp.beans;

import java.util.Date;

public class Product implements java.io.Serializable {

    private Integer idproduct;
    private String productName;

    public Product() {
    }

    public Product(Integer idproduct, String productName) {
        this.idproduct = idproduct;
        this.productName = productName;
    }

    public Integer getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Integer idproduct) {
        this.idproduct = idproduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idproduct=" + idproduct +
                ", productName='" + productName + '\'' +
                '}';
    }
}
