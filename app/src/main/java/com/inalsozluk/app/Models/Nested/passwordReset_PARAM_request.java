package com.inalsozluk.app.Models.Nested;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class passwordReset_PARAM_request {

    @SerializedName("pass")
    @Expose
    private String pass;
    @SerializedName("passNew")
    @Expose
    private String passNew;
    @SerializedName("passNewAgain")
    @Expose
    private String passNewAgain;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPassNew() {
        return passNew;
    }

    public void setPassNew(String passNew) {
        this.passNew = passNew;
    }

    public String getPassNewAgain() {
        return passNewAgain;
    }

    public void setPassNewAgain(String passNewAgain) {
        this.passNewAgain = passNewAgain;
    }

    public passwordReset_PARAM_request(String pass, String passNew, String passNewAgain) {
        this.pass = pass;
        this.passNew = passNew;
        this.passNewAgain = passNewAgain;
    }
}
