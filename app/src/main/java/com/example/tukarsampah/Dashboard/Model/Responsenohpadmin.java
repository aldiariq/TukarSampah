package com.example.tukarsampah.Dashboard.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Responsenohpadmin {

    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("NOHP_ADMIN")
    @Expose
    private String nOHPADMIN;

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public String getNOHPADMIN() {
        return nOHPADMIN;
    }

    public void setNOHPADMIN(String nOHPADMIN) {
        this.nOHPADMIN = nOHPADMIN;
    }

}