package com.atheesh.samplejavawebapp.apis;


import com.atheesh.samplejavawebapp.beans.Product;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface ProductAPIs {

    @GET("product/getAllProducts")
    Call<List<Product>> getAllProducts();

    @GET("product/getProductByID/{id}")
    Call<Product> getProductById(@Path("id") int id);

    @GET("product/getProductsByOrganization/{orgId}")
    Call<List<Product>> getProductByOrganizationId(@Path("orgId") int orgId);

    @POST("product/saveProduct")
    Call<Integer> saveProduct(@Body Product product);

    @POST("product/updateProduct")
    Call<String> updateProduct(@Body Product product);

    @POST("product/saveProducts")
    Call<String> saveProductList(@Body List<Product> product);
}
