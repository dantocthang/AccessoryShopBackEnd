package com.nhom9.springjwt.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ProfileRequest {
    // @NotBlank
    // private String username;

    @NotBlank
    @Email
    private String email;

    // public String getUsername() {
    // return username;
    // }

    // public void setUsername(String username) {
    // this.username = username;
    // }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // @NotBlank
    // @Size(max = 120)
    // private String password;
}
