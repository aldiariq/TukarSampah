package com.example.tukarsampah.Dashboard.Api;

import com.example.tukarsampah.Dashboard.Model.Responsegettransaksipengguna;
import com.example.tukarsampah.Dashboard.Model.Responseoperasi;
import com.example.tukarsampah.Dashboard.Model.Responsetransaksigetkurirpengguna;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Operasipengguna {

    @GET("getkurir")
    Call<Responsetransaksigetkurirpengguna> getDatakurir();

    @FormUrlEncoded
    @POST("ubahpassword")
    Call<Responseoperasi> ubahPassword(
            @Field("username") String username,
            @Field("passwordlama") String passwordlama,
            @Field("passwordbaru") String passwordbaru,
            @Field("tipeakun") String tipeakun
    );

    @FormUrlEncoded
    @POST("transaksi")
    Call<Responseoperasi> Transaksi(
            @Field("id_pengguna") String idpengguna,
            @Field("id_kurir") String idkurir,
            @Field("jumlah_transaksi") String jumlahtransaksi
    );

    @FormUrlEncoded
    @POST("gettransaksipengguna")
    Call<Responsegettransaksipengguna> getTransaksipengguna(
            @Field("id_pengguna") String idpengguna
    );
}
