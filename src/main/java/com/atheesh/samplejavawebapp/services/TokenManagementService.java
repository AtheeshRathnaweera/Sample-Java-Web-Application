package com.atheesh.samplejavawebapp.services;

import javax.servlet.http.HttpServletRequest;

public interface TokenManagementService {

    long getValidTimeAmount(String jwtToken);
    String sessionTokenManagement(HttpServletRequest request);
    String getTheTokenInSession(HttpServletRequest request);

}
