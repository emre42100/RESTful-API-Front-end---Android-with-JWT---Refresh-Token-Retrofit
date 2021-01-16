package com.inalsozluk.app.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class getCustomerDetails_response {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("aciklama")
    @Expose
    private String aciklama;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("lastUpdatedBy")
    @Expose
    private String lastUpdatedBy;
    @SerializedName("CreatedOn")
    @Expose
    private String CreatedOn;

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Override
    public String toString() {
        return "getCustomerDetails_response{" +
                "id='" + id + '\'' +
                ", userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", aciklama='" + aciklama + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", CreatedOn='" + CreatedOn + '\'' +
                '}';
    }
}