package com.inalsozluk.app.Models;

import com.inalsozluk.app.Models.Nested.ara_PARAM_request;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ara_request {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("param")
    @Expose
    private ara_PARAM_request param;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ara_PARAM_request getParam() {
        return param;
    }

    public void setParam(ara_PARAM_request param) {
        this.param = param;
    }

    public ara_request(String name, ara_PARAM_request param) {
        this.name = name;
        this.param = param;
    }
}
