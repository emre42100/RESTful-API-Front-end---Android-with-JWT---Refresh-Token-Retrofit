package com.inalsozluk.app.Models;

import com.inalsozluk.app.Models.Nested.Nested.UpdateCustomer_RESPONSE_response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateCustomer_response {

    @SerializedName("response")
    @Expose
    private UpdateCustomer_RESPONSE_response response;

    public UpdateCustomer_RESPONSE_response getResponse() {
        return response;
    }

    public void setResponse(UpdateCustomer_RESPONSE_response response) {
        this.response = response;
    }

}