package com.inalsozluk.app.Models.Nested;

import java.util.ArrayList;
import com.inalsozluk.app.Models.getCustomerDetails_response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class getCustomerDetailsList_response {


    @SerializedName("toplam")
    @Expose
    private Integer toplam;
    @SerializedName("data")
    @Expose
    private ArrayList<getCustomerDetails_response> data = null;

    public ArrayList<getCustomerDetails_response> getData() {
        return data;
    }
    public Integer getToplam() {
        return toplam;
    }

    public void setToplam(Integer toplam) {
        this.toplam = toplam;
    }



}