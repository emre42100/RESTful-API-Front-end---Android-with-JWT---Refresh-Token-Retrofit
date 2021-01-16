package com.inalsozluk.app.Models.Nested;

import java.util.HashMap;
import java.util.Map;

public class getAllCustomer_PARAM_request {


    private String customerId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);

    }

    public getAllCustomer_PARAM_request(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "getAllCustomer_PARAM_request{" +
                "customerId='" + customerId + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}