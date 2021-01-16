package com.inalsozluk.app.Models;

import com.inalsozluk.app.Models.Nested.addCustomer_PARAM_request;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class addCustomer_request {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("param")
    @Expose
    private addCustomer_PARAM_request param;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public addCustomer_PARAM_request getParam() {
        return param;
    }

    public void setParam(addCustomer_PARAM_request param) {
        this.param = param;
    }

    public addCustomer_request(String name, addCustomer_PARAM_request param) {
        this.name = name;
        this.param = param;
    }
}