package com.atheesh.samplejavawebapp.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RetrofitClient {

    private Retrofit retrofit;

    public RetrofitClient(String url, String token) {

        OkHttpClient c = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor(token))
                .addInterceptor(new TokenInterceptor())
                .callTimeout(15, TimeUnit.SECONDS).build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .registerTypeAdapter(Date.class, new TimeAdapter())
                .setLenient()
                .create();
        
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(c)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

}

