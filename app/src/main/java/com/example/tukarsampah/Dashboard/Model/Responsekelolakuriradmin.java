package com.example.tukarsampah.Dashboard.Model;

import java.util.List;

public class Responsekelolakuriradmin {
    private List<Kelolakuriradmin> datakurir;

    public List<Kelolakuriradmin> getData(){
        return datakurir;
    }

    public void setDatakurir(List<Kelolakuriradmin> datakurir) {
        this.datakurir = datakurir;
    }
}
