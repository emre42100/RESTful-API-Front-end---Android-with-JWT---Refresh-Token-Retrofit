package com.inalsozluk.app.Models.Nested;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class getCustomerDetails_PARAM_request {

    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("customerId")
    @Expose
    private String customerId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public getCustomerDetails_PARAM_request(Integer userId, String customerId) {
        this.userId = userId;
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "getCustomerDetails_PARAM_request{" +
                "userId=" + userId +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}