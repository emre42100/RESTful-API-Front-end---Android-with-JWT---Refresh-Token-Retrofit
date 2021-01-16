package com.inalsozluk.app.Models.Nested.Nested;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUp_RESPONSE_response {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("result")
    @Expose
    private String result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}