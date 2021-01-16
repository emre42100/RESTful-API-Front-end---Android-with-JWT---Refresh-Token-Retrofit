package com.inalsozluk.app.Models;

import com.inalsozluk.app.Models.Nested.getCustomerDetails_PARAM_request;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class getCustomerDetails_request {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("param")
    @Expose
    private getCustomerDetails_PARAM_request param;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public getCustomerDetails_PARAM_request getParam() {
        return param;
    }
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
    public void setParam(getCustomerDetails_PARAM_request param) {
        this.param = param;
    }

    public getCustomerDetails_request(String name, getCustomerDetails_PARAM_request param) {
        this.name = name;
        this.param = param;
    }

    @Override
    public String toString() {
        return "getCustomerDetails_request{" +
                "name='" + name + '\'' +
                ", param=" + param +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}