package com.inalsozluk.app.Models.Nested;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class addCustomer_PARAM_request {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("aciklama")
    @Expose
    private String aciklama;
    @SerializedName("addr")
    @Expose
    private String addr;
    @SerializedName("mobile")
    @Expose
    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public addCustomer_PARAM_request(String name, String aciklama, String addr, String mobile) {
        this.name = name;
        this.aciklama = aciklama;
        this.addr = addr;
        this.mobile = mobile;
    }
}