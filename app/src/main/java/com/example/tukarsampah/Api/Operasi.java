package com.example.tukarsampah.Api;

import com.example.tukarsampah.Model.ResponseDaftar;
import com.example.tukarsampah.Model.ResponseMasuk;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Operasi {

    @FormUrlEncoded
    @POST("masuk")
    @Headers("Accept: */*")
    Call<ResponseMasuk> Masuk(
            @Field("username") String Username,
            @Field("password") String Password,
            @Field("tipeakun") String Tipeakun
    );

    @FormUrlEncoded
    @POST("daftar")
    @Headers("Accept: */*")
    Call<ResponseDaftar> Daftar(
            @Field("username") String Username,
            @Field("password") String Password,
            @Field("tipeakun") String Tipeakun,
            @Field("nohp") String Nohp,
            @Field("alamat") String Alamat
    );
}
