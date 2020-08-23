package com.atheesh.samplejavawebapp.services.impl;


import com.atheesh.samplejavawebapp.apis.ProductAPIs;
import com.atheesh.samplejavawebapp.beans.Product;
import com.atheesh.samplejavawebapp.services.ProductService;
import com.atheesh.samplejavawebapp.utils.RetrofitClient;
import com.atheesh.samplejavawebapp.utils.Values;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductAPIs PRODUCT_API = null;

    public ProductServiceImpl(String token) {
        this.PRODUCT_API = new RetrofitClient(Values.MAINURL, token).getRetrofit().create(ProductAPIs.class);
    }

    @Override
    public List<Product> getAll() {
        List<Product> allProducts = new ArrayList<>();

        try {
            Call<List<Product>> getAll = PRODUCT_API.getAllProducts();
            Response<List<Product>> response = getAll.execute();
            return response.body();
        } catch (IOException e) {
            System.out.println("Exception in getAllProducts : "+e);
        }
        return allProducts;
    }

    @Override
    public List<Product> getByOrganization(int id) {
        List<Product> allProducts = new ArrayList<>();

        try {
            Call<List<Product>> getByOrgCall = PRODUCT_API.getProductByOrganizationId(id);
            Response<List<Product>> response = getByOrgCall.execute();
            return response.body();
        } catch (IOException e) {
            System.out.println("Exception in getAllProducts : "+e);
        }
        return allProducts;
    }

    @Override
    public Product getById(int id) {
        try {
            Call<Product> getByIdCall = PRODUCT_API.getProductById(id);
            Response<Product> response = getByIdCall.execute();
            return response.body();
        } catch (IOException e) {
            System.out.println("Exception in getProductById method : " + e);
            return null;
        }
    }

    @Override
    public Integer save(Product product) {
        try {
            Call<Integer> saveCall = PRODUCT_API.saveProduct(product);
            Response<Integer> response = saveCall.execute();
            return response.body();
        } catch (IOException e) {
            System.out.println("Exception in saveProduct method : " + e);
            return null;
        }
    }

    @Override
    public boolean update(Product product) {
        System.out.println("recibed product for update : "+product.toString());
        try {
            Call<String> updateCall = PRODUCT_API.updateProduct(product);
            Response<String> response = updateCall.execute();
            String responseText = response.body();

            System.out.println("product update response text : "+responseText);

            return responseText.equals("Save Success");
        } catch (IOException e) {
            System.out.println("Exception in updateProduct method : " + e);
            return false;
        }
    }

    @Override
    public boolean saveBulk(List<Product> productList) {
        try {
            Call<String> saveBulkCall = PRODUCT_API.saveProductList(productList);
            Response<String> response = saveBulkCall.execute();
            String responseText = response.body();
            System.out.println("product update : "+responseText);

            return responseText.equals("Save Success");
        } catch (IOException e) {
            System.out.println("Exception in saveProductBulk method : " + e);
            return false;
        }
    }
}
