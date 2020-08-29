package com.javatechie.springsecurityjwtexample.controllers;

import lombok.Data;

@Data
public class RegisterForm {

    private String usernamee;
    private String password;
    private String repassword;

    public String getUserName() {
        return usernamee;
    }

    public void setUserName(String userName) {
        this.usernamee = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
}
