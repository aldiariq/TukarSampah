package com.example.tukarsampah.Dashboard.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Responseprofilpengguna {

    @SerializedName("id_pengguna")
    @Expose
    private String id_pengguna;

    @SerializedName("username_pengguna")
    @Expose
    private String username_pengguna;

    @SerializedName("nohp_pengguna")
    @Expose
    private String nohp_pengguna;

    @SerializedName("tipe_akun")
    @Expose
    private String tipe_akun;

    public String getId_pengguna() {
        return id_pengguna;
    }

    public void setId_pengguna(String id_pengguna) {
        this.id_pengguna = id_pengguna;
    }

    public String getUsername_pengguna() {
        return username_pengguna;
    }

    public void setUsername_pengguna(String username_pengguna) {
        this.username_pengguna = username_pengguna;
    }

    public String getNohp_pengguna() {
        return nohp_pengguna;
    }

    public void setNohp_pengguna(String nohp_pengguna) {
        this.nohp_pengguna = nohp_pengguna;
    }

    public String getTipe_akun() {
        return tipe_akun;
    }

    public void setTipe_akun(String tipe_akun) {
        this.tipe_akun = tipe_akun;
    }
}
