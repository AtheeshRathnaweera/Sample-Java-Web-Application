package com.atheesh.samplejavawebapp.services.impl;


import com.atheesh.samplejavawebapp.apis.AuthenticateAPIs;
import com.atheesh.samplejavawebapp.beans.AccessTokenRequest;
import com.atheesh.samplejavawebapp.beans.AuthToken;
import com.atheesh.samplejavawebapp.services.AuthenticationService;
import com.atheesh.samplejavawebapp.utils.RetrofitClient;
import com.atheesh.samplejavawebapp.utils.Values;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class AuthenticationServiceImpl implements AuthenticationService {

    //this api is not validate by the token
    private AuthenticateAPIs AUTHENTICATE_API = null;

    public AuthenticationServiceImpl(String token) {
        AUTHENTICATE_API = new RetrofitClient(Values.MAINURL, token).getRetrofit().create(AuthenticateAPIs.class);
    }

    @Override
    public String getAuthToken(AccessTokenRequest tokenRequest) {

        try {
            Call<String> getToken = AUTHENTICATE_API.getAuthToken(tokenRequest);
            Response<String> res = getToken.execute();
            return res.body();
        } catch (IOException e) {
            System.out.println("error in getauthtoken : "+e.toString());
            return null;
        }
    }

    @Override
    public String getRefreshAuthToken(AuthToken authToken) {

        try {
            Call<String> getRefreshToken = AUTHENTICATE_API.getRefreshToken(authToken);
            Response<String> res = getRefreshToken.execute();
            return res.body();
        }catch (IOException e){
            System.out.println("refresh token error : "+e);
            return null;
        }

    }
}
