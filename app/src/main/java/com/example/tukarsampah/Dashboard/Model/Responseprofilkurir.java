package com.example.tukarsampah.Dashboard.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Responseprofilkurir {
    @SerializedName("id_kurir")
    @Expose
    private String id_kurir;

    @SerializedName("username_kurir")
    @Expose
    private String username_kurir;

    @SerializedName("nohp_kurir")
    @Expose
    private String nohp_kurir;

    @SerializedName("tipe_akun")
    @Expose
    private String tipe_akun;

    public String getId_kurir() {
        return id_kurir;
    }

    public void setId_kurir(String id_kurir) {
        this.id_kurir = id_kurir;
    }

    public String getUsername_kurir() {
        return username_kurir;
    }

    public void setUsername_kurir(String username_kurir) {
        this.username_kurir = username_kurir;
    }

    public String getNohp_kurir() {
        return nohp_kurir;
    }

    public void setNohp_kurir(String nohp_kurir) {
        this.nohp_kurir = nohp_kurir;
    }

    public String getTipe_akun() {
        return tipe_akun;
    }

    public void setTipe_akun(String tipe_akun) {
        this.tipe_akun = tipe_akun;
    }
}
