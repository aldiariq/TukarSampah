package com.example.tukarsampah.Dashboard.Model;

import java.util.List;

public class Responsekelolapenggunaadmin {
    private List<Kelolapenggunaadmin> datapengguna;

    public List<Kelolapenggunaadmin> getData(){
        return datapengguna;
    }

    public void setDatapengguna(List<Kelolapenggunaadmin> datapengguna) {
        this.datapengguna = datapengguna;
    }
}
