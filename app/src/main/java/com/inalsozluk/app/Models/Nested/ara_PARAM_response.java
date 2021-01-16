package com.inalsozluk.app.Models.Nested;

import com.inalsozluk.app.Models.ara_response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class ara_PARAM_response {

    @SerializedName("toplam")
    @Expose
    private Integer toplam;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("data")
    @Expose
    private ArrayList<ara_response> data = null;

    public Integer getToplam() {
        return toplam;
    }

    public void setToplam(Integer toplam) {
        this.toplam = toplam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ara_response> getData() {
        return data;
    }

    public void setData(ArrayList<ara_response> data) {
        this.data = data;
    }
}
