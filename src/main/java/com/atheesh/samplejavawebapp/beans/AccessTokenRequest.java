package com.atheesh.samplejavawebapp.beans;

public class AccessTokenRequest implements java.io.Serializable{

    private String username;//email
    private String password;

    public AccessTokenRequest() {
    }

    public AccessTokenRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AccessTokenRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
