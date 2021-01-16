package com.inalsozluk.app.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUp_PARAM_request {


    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("pass")
    @Expose
    private String pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public SignUp_PARAM_request(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.pass = pass;
    }
}

