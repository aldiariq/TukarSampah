package com.example.tukarsampah.Dashboard.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Responseprofiladmin {

    @SerializedName("id_admin")
    @Expose
    private String id_admin;

    @SerializedName("username_admin")
    @Expose
    private String username_admin;

    @SerializedName("nohp_admin")
    @Expose
    private String nohp_admin;

    @SerializedName("tipe_akun")
    @Expose
    private String tipe_akun;

    public String getId_admin() {
        return id_admin;
    }

    public void setId_admin(String id_admin) {
        this.id_admin = id_admin;
    }

    public String getUsername_admin() {
        return username_admin;
    }

    public void setUsername_admin(String username_admin) {
        this.username_admin = username_admin;
    }

    public String getNohp_admin() {
        return nohp_admin;
    }

    public void setNohp_admin(String nohp_admin) {
        this.nohp_admin = nohp_admin;
    }

    public String getTipe_akun() {
        return tipe_akun;
    }

    public void setTipe_akun(String tipe_akun) {
        this.tipe_akun = tipe_akun;
    }
}
