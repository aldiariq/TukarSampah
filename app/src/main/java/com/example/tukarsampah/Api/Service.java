package com.example.tukarsampah.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {
    static final String BASE_URL = "http://belajarrestapiku.000webhostapp.com/tukarsampah/";
    private static Retrofit retrofit;

    public static Retrofit Koneksi(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
}
