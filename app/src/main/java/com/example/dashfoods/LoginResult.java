package com.example.dashfoods;

import com.google.gson.annotations.SerializedName;

public class LoginResult {
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    private String username;
    private String email;
}
