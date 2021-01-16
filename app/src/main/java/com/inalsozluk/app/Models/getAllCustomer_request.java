package com.inalsozluk.app.Models;

import com.inalsozluk.app.Models.Nested.getAllCustomer_PARAM_request;

import java.util.HashMap;
import java.util.Map;

public class getAllCustomer_request {


    private String name;
    private getAllCustomer_PARAM_request param;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public getAllCustomer_PARAM_request getParam() {
        return param;
    }

    public void setParam(getAllCustomer_PARAM_request param) {
        this.param = param;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public getAllCustomer_request(String name, getAllCustomer_PARAM_request param) {
        this.name = name;
        this.param = param;

    }

    @Override
    public String toString() {
        return "getAllCustomer_request{" +
                "name='" + name + '\'' +
                ", param=" + param +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}