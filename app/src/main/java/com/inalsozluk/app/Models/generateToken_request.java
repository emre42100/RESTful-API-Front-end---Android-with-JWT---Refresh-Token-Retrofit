package com.inalsozluk.app.Models;

import com.inalsozluk.app.Models.Nested.generateToken_PARAM_request;

public class generateToken_request {


    private String name;
    private generateToken_PARAM_request param;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public generateToken_PARAM_request getParam() {
        return param;
    }

    public void setParam(generateToken_PARAM_request param) {
        this.param = param;
    }

    public generateToken_request(String name, generateToken_PARAM_request param) {
        this.name = name;
        this.param = param;
    }

    @Override
    public String toString() {
        return "generateToken_request{" +
                "name='" + name + '\'' +
                ", param=" + param +
                '}';
    }
}

