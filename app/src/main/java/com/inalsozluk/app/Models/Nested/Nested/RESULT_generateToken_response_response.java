package com.inalsozluk.app.Models.Nested.Nested;

public class RESULT_generateToken_response_response {


    private String token;
    private String refreshToken;

    public String getToken() {
        return token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setToken(String token) {
        this.token = token;
    }



    @Override
    public String toString() {
        return "RESULT_generateToken_response_response{" +
                "token='" + token + '\'' +
                "RefreshToken='" + refreshToken + '\'' +
                '}';
    }
}
