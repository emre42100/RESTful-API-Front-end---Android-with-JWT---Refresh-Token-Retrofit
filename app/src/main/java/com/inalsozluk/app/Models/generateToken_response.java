package com.inalsozluk.app.Models;

import com.inalsozluk.app.Models.Nested.generateToken_RESPONSE_response;

public class generateToken_response {

    private generateToken_RESPONSE_response response;

    public generateToken_RESPONSE_response getResponse() {
        return response;
    }

    public void setResponse(generateToken_RESPONSE_response response) {
        this.response = response;
    }

    public generateToken_response(generateToken_RESPONSE_response response) {
        this.response = response;
    }

    public generateToken_response() {

    }

    @Override
    public String toString() {
        return "generateToken_response{" +
                "response=" + response +
                '}';
    }
}
