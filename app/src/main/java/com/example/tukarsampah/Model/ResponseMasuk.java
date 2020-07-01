package com.example.tukarsampah.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseMasuk {
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("KETERANGAN")
    @Expose
    private String kETERANGAN;
    @SerializedName("ID_AKUN")
    @Expose
    private String iDAKUN;
    @SerializedName("USERNAME")
    @Expose
    private String uSERNAME;
    @SerializedName("TIPEAKUN")
    @Expose
    private String tIPEAKUN;

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

    public String getIDAKUN() {
        return iDAKUN;
    }

    public void setIDAKUN(String iDAKUN) {
        this.iDAKUN = iDAKUN;
    }

    public String getUSERNAME() {
        return uSERNAME;
    }

    public void setUSERNAME(String uSERNAME) {
        this.uSERNAME = uSERNAME;
    }

    public String getTIPEAKUN() {
        return tIPEAKUN;
    }

    public void setTIPEAKUN(String tIPEAKUN) {
        this.tIPEAKUN = tIPEAKUN;
    }
}
