package com.atheesh.samplejavawebapp.services;


import com.atheesh.samplejavawebapp.beans.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();
    List<Product> getByOrganization(int id);
    Product getById(int id);
    Integer save(Product product);
    boolean update(Product product);
    boolean saveBulk(List<Product> productList);
}
