package com.inalsozluk.app.Models;

import com.inalsozluk.app.Models.Nested.deleteCustomer_RESPONSE_response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class deleteCustomer_response {


    @SerializedName("response")
    @Expose
    private deleteCustomer_RESPONSE_response response;

    public deleteCustomer_RESPONSE_response getResponse() {
        return response;
    }

    public void setResponse(deleteCustomer_RESPONSE_response response) {
        this.response = response;
    }

}