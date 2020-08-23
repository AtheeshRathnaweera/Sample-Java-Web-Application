package com.atheesh.samplejavawebapp.services.impl;

import com.atheesh.samplejavawebapp.beans.AuthToken;
import com.atheesh.samplejavawebapp.services.AuthenticationService;
import com.atheesh.samplejavawebapp.services.TokenManagementService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class TokenManagementServiceImpl implements TokenManagementService {

    @Override
    public long getValidTimeAmount(String jwtToken) {
        try {
            DecodedJWT jwt = JWT.decode(jwtToken);

            Date date = jwt.getExpiresAt();
            Date currentTime = new Date();

            long diff = date.getTime() - currentTime.getTime();
            long diffSeconds = diff / 1000;

            System.out.println("valid till : "+date+" time left : "+diffSeconds);
            return diffSeconds;
        } catch (JWTDecodeException exception){
            //Invalid token
            System.out.println("jwt decode error");
            return 0;
        }
    }

    @Override
    public String sessionTokenManagement(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String accessToken = (String) session.getAttribute("accesstoken");

        System.out.println("access token : "+accessToken);
        System.out.println("refresh token : "+session.getAttribute("refreshtoken"));

        if(accessToken != null) {
            long leftTime = getValidTimeAmount(accessToken);

            if (leftTime < 300) {
                System.out.println("token should be refreshed");
                AuthToken refreshTokenObj = new AuthToken(accessToken);

                AuthenticationService authenticationService = new AuthenticationServiceImpl(accessToken);
                String refreshToken = authenticationService.getRefreshAuthToken(refreshTokenObj);

                session.removeAttribute("accesstoken");
                session.setAttribute("refreshtoken", refreshToken);

                System.out.println("new refresh token : "+refreshToken);
                getValidTimeAmount(refreshToken);
                return refreshToken;
            } else {
                System.out.println("access token is okay : time is over 300 or less than 300");
                return accessToken;
            }
        }else{
            //set the refresh token to authToken global variable
            System.out.println("refresh token is used");
            return (String) session.getAttribute("refreshtoken");
        }
    }

    @Override
    public String getTheTokenInSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String accessToken = (String) session.getAttribute("accesstoken");
        getValidTimeAmount(accessToken);
        return accessToken;
    }
}
