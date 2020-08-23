package com.atheesh.samplejavawebapp.beans;

public class AuthToken implements java.io.Serializable{

    private String authKey;

    public AuthToken(String authKey) {
        this.authKey = authKey;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    @Override
    public String toString() {
        return "AuthToken{" +
                "authKey='" + authKey + '\'' +
                '}';
    }
}

