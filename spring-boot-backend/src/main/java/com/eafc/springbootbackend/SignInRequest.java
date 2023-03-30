package com.eafc.springbootbackend;

import lombok.Data;

@Data
public class SignInRequest {

    private String username;
    private String password;

    public SignInRequest() {

    }

    public SignInRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
