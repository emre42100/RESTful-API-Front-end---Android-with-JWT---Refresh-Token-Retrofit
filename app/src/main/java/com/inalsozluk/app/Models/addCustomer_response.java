package com.inalsozluk.app.Models;
import com.inalsozluk.app.Models.Nested.addCustomer_PARAM_response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class addCustomer_response {

    @SerializedName("response")
    @Expose
    private addCustomer_PARAM_response response;

    public addCustomer_PARAM_response getResponse() {
        return response;
    }

    public void setResponse(addCustomer_PARAM_response response) {
        this.response = response;
    }

}