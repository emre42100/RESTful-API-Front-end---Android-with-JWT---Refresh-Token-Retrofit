package com.inalsozluk.app.Models;

import com.inalsozluk.app.Models.Nested.UpdateCustomer_PARAM_request;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateCustomer_request {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("param")
    @Expose
    private UpdateCustomer_PARAM_request param;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UpdateCustomer_PARAM_request getParam() {
        return param;
    }

    public void setParam(UpdateCustomer_PARAM_request param) {
        this.param = param;
    }

    public UpdateCustomer_request(String name, UpdateCustomer_PARAM_request param) {
        this.name = name;
        this.param = param;
    }
}