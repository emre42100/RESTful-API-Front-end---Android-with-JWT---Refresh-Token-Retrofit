package com.inalsozluk.app.Models.Nested;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class passwordReset_RESPONSE_response {

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

    @Override
    public String toString() {
        return "passwordReset_RESPONSE_response{" +
                "status=" + status +
                ", result='" + result + '\'' +
                '}';
    }

    public passwordReset_RESPONSE_response(Integer status, String result) {
        this.status = status;
        this.result = result;
    }
}
