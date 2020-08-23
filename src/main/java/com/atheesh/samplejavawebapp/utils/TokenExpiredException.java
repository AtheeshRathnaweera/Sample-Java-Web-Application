package com.atheesh.samplejavawebapp.utils;

class TokenExpiredException extends RuntimeException{

    TokenExpiredException(String errorMessage) {
        super(errorMessage);
    }
}
