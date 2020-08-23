package com.atheesh.samplejavawebapp.services;


import com.atheesh.samplejavawebapp.beans.AccessTokenRequest;
import com.atheesh.samplejavawebapp.beans.AuthToken;

public interface AuthenticationService {

    String getAuthToken(AccessTokenRequest tokenRequest);
    String getRefreshAuthToken(AuthToken authToken);
}
