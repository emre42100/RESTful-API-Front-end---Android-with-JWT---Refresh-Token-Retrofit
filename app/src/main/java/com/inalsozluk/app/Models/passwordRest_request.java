package com.inalsozluk.app.Models;

import com.inalsozluk.app.Models.Nested.passwordReset_PARAM_request;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class passwordRest_request {


    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("param")
    @Expose
    private passwordReset_PARAM_request param;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public passwordReset_PARAM_request getParam() {
        return param;
    }

    public void setParam(passwordReset_PARAM_request param) {
        this.param = param;
    }


    public passwordRest_request(String name, passwordReset_PARAM_request param) {
        this.name = name;
        this.param = param;
    }
}

