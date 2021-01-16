package com.inalsozluk.app.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUp_request {


    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("param")
    @Expose
    private SignUp_PARAM_request param;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SignUp_PARAM_request getParam() {
        return param;
    }

    public void setParam(SignUp_PARAM_request param) {
        this.param = param;
    }

    public SignUp_request(String name, SignUp_PARAM_request param) {
        this.name = name;
        this.param = param;
    }
}