package com.example.tukarsampah.Dashboard.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Responseprofiladmin {
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("ID_ADMIN")
    @Expose
    private String iDADMIN;
    @SerializedName("USERNAME_ADMIN")
    @Expose
    private String uSERNAMEADMIN;
    @SerializedName("NOHP_ADMIN")
    @Expose
    private String nOHPADMIN;
    @SerializedName("TIPE_AKUN")
    @Expose
    private String tIPEAKUN;

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public String getIDADMIN() {
        return iDADMIN;
    }

    public void setIDADMIN(String iDADMIN) {
        this.iDADMIN = iDADMIN;
    }

    public String getUSERNAMEADMIN() {
        return uSERNAMEADMIN;
    }

    public void setUSERNAMEADMIN(String uSERNAMEADMIN) {
        this.uSERNAMEADMIN = uSERNAMEADMIN;
    }

    public String getNOHPADMIN() {
        return nOHPADMIN;
    }

    public void setNOHPADMIN(String nOHPADMIN) {
        this.nOHPADMIN = nOHPADMIN;
    }

    public String getTIPEAKUN() {
        return tIPEAKUN;
    }

    public void setTIPEAKUN(String tIPEAKUN) {
        this.tIPEAKUN = tIPEAKUN;
    }
}
