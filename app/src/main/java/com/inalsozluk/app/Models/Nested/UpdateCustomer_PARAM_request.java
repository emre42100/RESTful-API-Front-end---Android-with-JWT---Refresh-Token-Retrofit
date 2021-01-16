package com.inalsozluk.app.Models.Nested;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateCustomer_PARAM_request {

    @SerializedName("customerId")
    @Expose
    private Integer customerId;
    @SerializedName("userId")
    @Expose
    private Integer userId;
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

    public UpdateCustomer_PARAM_request(Integer customerId, Integer userId, String aciklama, String addr, String mobile) {
        this.customerId = customerId;
        this.userId = userId;
        this.aciklama = aciklama;
        this.addr = addr;
        this.mobile = mobile;
    }
}