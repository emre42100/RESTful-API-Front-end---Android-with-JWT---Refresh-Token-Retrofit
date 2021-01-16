package com.inalsozluk.app.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CustomerList {

    @SerializedName("toplam")
    @Expose
    private Integer toplam;
    @SerializedName("data")
    @Expose
    private ArrayList<Customer> data = null;

    public ArrayList<Customer> getData() {
        return data;
    }

    public void setCustomers(ArrayList<Customer> data) {
        this.data = data;
    }

    public Integer getToplam() {
        return toplam;
    }

    public void setToplam(Integer toplam) {
        this.toplam = toplam;
    }
}