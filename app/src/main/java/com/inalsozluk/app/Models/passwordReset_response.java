package com.inalsozluk.app.Models;

import com.inalsozluk.app.Models.Nested.passwordReset_RESPONSE_response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class passwordReset_response {

    @SerializedName("response")
    @Expose
    private passwordReset_RESPONSE_response response;

    public passwordReset_RESPONSE_response getResponse() {
        return response;
    }

    public void setResponse(passwordReset_RESPONSE_response response) {
        this.response = response;
    }



}
