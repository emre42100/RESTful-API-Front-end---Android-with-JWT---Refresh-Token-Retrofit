package com.inalsozluk.app.Models.Nested;

public class generateToken_PARAM_request {


    private String email;
    private String pass;

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

    public generateToken_PARAM_request(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "generateToken_PARAM_request{" +
                "email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}