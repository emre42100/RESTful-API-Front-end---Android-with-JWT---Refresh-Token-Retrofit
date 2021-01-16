package com.inalsozluk.app.Models.Nested;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class deleteCustomer_PARAM_request {


    @SerializedName("customerId")
    @Expose
    private Integer customerId;
    @SerializedName("userId")
    @Expose
    private Integer userId;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public deleteCustomer_PARAM_request(Integer customerId, Integer userId) {
        this.customerId = customerId;
        this.userId = userId;
    }
}

