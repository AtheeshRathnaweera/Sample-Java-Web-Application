package com.atheesh.samplejavawebapp.apis;

import com.atheesh.samplejavawebapp.beans.AccessTokenRequest;
import com.atheesh.samplejavawebapp.beans.AuthToken;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthenticateAPIs {

    @POST("authentication/authenticateUser")
    Call<String> getAuthToken(@Body AccessTokenRequest tokenRequest);

    @POST("authentication/refreshToken")
    Call<String> getRefreshToken(@Body AuthToken authToken);
}
