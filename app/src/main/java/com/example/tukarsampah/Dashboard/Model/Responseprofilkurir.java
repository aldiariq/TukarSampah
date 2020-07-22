package com.example.tukarsampah.Dashboard.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Responseprofilkurir {
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("ID_KURIR")
    @Expose
    private String iDKURIR;
    @SerializedName("USERNAME_KURIR")
    @Expose
    private String uSERNAMEKURIR;
    @SerializedName("NOHP_KURIR")
    @Expose
    private String nOHPKURIR;
    @SerializedName("ALAMAT_KURIR")
    @Expose
    private String aLAMATKURIR;
    @SerializedName("TIPE_AKUN")
    @Expose
    private String tIPEAKUN;

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public String getIDKURIR() {
        return iDKURIR;
    }

    public void setIDKURIR(String iDKURIR) {
        this.iDKURIR = iDKURIR;
    }

    public String getUSERNAMEKURIR() {
        return uSERNAMEKURIR;
    }

    public void setUSERNAMEKURIR(String uSERNAMEKURIR) {
        this.uSERNAMEKURIR = uSERNAMEKURIR;
    }

    public String getNOHPKURIR() {
        return nOHPKURIR;
    }

    public void setNOHPKURIR(String nOHPKURIR) {
        this.nOHPKURIR = nOHPKURIR;
    }

    public String getALAMATKURIR() {
        return aLAMATKURIR;
    }

    public void setALAMATKURIR(String aLAMATKURIR) {
        this.aLAMATKURIR = aLAMATKURIR;
    }

    public String getTIPEAKUN() {
        return tIPEAKUN;
    }

    public void setTIPEAKUN(String tIPEAKUN) {
        this.tIPEAKUN = tIPEAKUN;
    }
}
