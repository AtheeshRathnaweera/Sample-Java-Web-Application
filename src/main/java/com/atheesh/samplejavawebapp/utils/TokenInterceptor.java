package com.atheesh.samplejavawebapp.utils;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class TokenInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException, TokenExpiredException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        if (response.code() == 401) {
            System.out.println("401 received");
            throw new TokenExpiredException("token expired");
        }
        return response;
    }
}
