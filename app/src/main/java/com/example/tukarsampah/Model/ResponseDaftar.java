package com.example.tukarsampah.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseDaftar {
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("KETERANGAN")
    @Expose
    private String kETERANGAN;

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public String getKETERANGAN() {
        return kETERANGAN;
    }

    public void setKETERANGAN(String kETERANGAN) {
        this.kETERANGAN = kETERANGAN;
    }
}
