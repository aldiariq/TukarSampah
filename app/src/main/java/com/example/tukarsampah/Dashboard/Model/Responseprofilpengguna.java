package com.example.tukarsampah.Dashboard.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Responseprofilpengguna {
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("ID_PENGGUNA")
    @Expose
    private String iDPENGGUNA;
    @SerializedName("USERNAME_PENGGUNA")
    @Expose
    private String uSERNAMEPENGGUNA;
    @SerializedName("NOHP_PENGGUNA")
    @Expose
    private String nOHPPENGGUNA;
    @SerializedName("ALAMAT_PENGGUNA")
    @Expose
    private String aLAMATPENGGUNA;
    @SerializedName("TIPE_AKUN")
    @Expose
    private String tIPEAKUN;

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public String getIDPENGGUNA() {
        return iDPENGGUNA;
    }

    public void setIDPENGGUNA(String iDPENGGUNA) {
        this.iDPENGGUNA = iDPENGGUNA;
    }

    public String getUSERNAMEPENGGUNA() {
        return uSERNAMEPENGGUNA;
    }

    public void setUSERNAMEPENGGUNA(String uSERNAMEPENGGUNA) {
        this.uSERNAMEPENGGUNA = uSERNAMEPENGGUNA;
    }

    public String getNOHPPENGGUNA() {
        return nOHPPENGGUNA;
    }

    public void setNOHPPENGGUNA(String nOHPPENGGUNA) {
        this.nOHPPENGGUNA = nOHPPENGGUNA;
    }

    public String getALAMATPENGGUNA() {
        return aLAMATPENGGUNA;
    }

    public void setALAMATPENGGUNA(String aLAMATPENGGUNA) {
        this.aLAMATPENGGUNA = aLAMATPENGGUNA;
    }

    public String getTIPEAKUN() {
        return tIPEAKUN;
    }

    public void setTIPEAKUN(String tIPEAKUN) {
        this.tIPEAKUN = tIPEAKUN;
    }
}
