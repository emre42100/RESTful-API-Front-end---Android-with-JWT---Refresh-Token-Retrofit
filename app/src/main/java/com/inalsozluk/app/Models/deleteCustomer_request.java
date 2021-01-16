package com.inalsozluk.app.Models;

import com.inalsozluk.app.Models.Nested.deleteCustomer_PARAM_request;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class deleteCustomer_request {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("param")
    @Expose
    private deleteCustomer_PARAM_request param;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public deleteCustomer_PARAM_request getParam() {
        return param;
    }

    public void setParam(deleteCustomer_PARAM_request param) {
        this.param = param;
    }

    public deleteCustomer_request(String name, deleteCustomer_PARAM_request param) {
        this.name = name;
        this.param = param;
    }
}