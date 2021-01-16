package com.inalsozluk.app.Models;

import com.inalsozluk.app.Models.Nested.Signup_Response_response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUp_response {

    @SerializedName("response")
    @Expose
    private Signup_Response_response response;

    public Signup_Response_response getResponse() {
        return response;
    }

    public void setResponse(Signup_Response_response response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "SignUp_response{" +
                "response=" + response +
                '}';
    }
}

