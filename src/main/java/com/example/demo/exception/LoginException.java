package com.example.demo.exception;


import lombok.Getter;

@Getter
public class LoginException extends RuntimeException{
    private String code;
    public LoginException(String code,String message){
        super(message);
        this.code=code;

    }
}
