package com.inalsozluk.app.Models.Nested;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ara_PARAM_request {

    @SerializedName("arama")
    @Expose
    private String arama;

    public String getArama() {
        return arama;
    }

    public void setArama(String arama) {
        this.arama = arama;
    }

    public ara_PARAM_request(String arama) {
        this.arama = arama;
    }


}
