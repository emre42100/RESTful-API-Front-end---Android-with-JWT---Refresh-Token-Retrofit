package com.inalsozluk.app.Models.Nested;

import com.inalsozluk.app.Models.Nested.Nested.RESULT_generateToken_response_response;

public class generateToken_RESPONSE_response {


    private Integer status;
    private RESULT_generateToken_response_response result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public RESULT_generateToken_response_response getResult() {
        return result;
    }

    public void setResult(RESULT_generateToken_response_response result) {
        this.result = result;
    }

    public generateToken_RESPONSE_response(Integer status, RESULT_generateToken_response_response result) {
        this.status = status;
        this.result = result;
    }

    @Override
    public String toString() {
        return "generateToken_RESPONSE_response{" +
                "status=" + status +
                ", result=" + result +
                '}';
    }
}
