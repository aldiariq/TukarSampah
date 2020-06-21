package com.example.tukarsampah.Dashboard.Api;

import com.example.tukarsampah.Dashboard.Model.Responsegettransaksipengguna;
import com.example.tukarsampah.Dashboard.Model.Responseoperasi;
import com.example.tukarsampah.Dashboard.Model.Responsetransaksigetkurirpengguna;
import com.example.tukarsampah.Dashboard.Model.Responsetukarpointgetpointpengguna;
import com.example.tukarsampah.Dashboard.Model.Responsetukarpointgetrewardpengguna;

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

    @FormUrlEncoded
    @POST("bataltransaksi")
    Call<Responseoperasi> Bataltransaksipengguna(
            @Field("id_transaksi") String idtransaksi
    );

    @FormUrlEncoded
    @POST("ambilpoint")
    Call<Responsetukarpointgetpointpengguna> getPointpengguna(
            @Field("id_pengguna") String idpengguna
    );

    @GET("getreward")
    Call<Responsetukarpointgetrewardpengguna> getDatareward();

    @FormUrlEncoded
    @POST("tukarpoint")
    Call<Responseoperasi> Tukarpoint(
            @Field("id_pengguna") String idpengguna,
            @Field("jumlah_point") String jumlahpoint,
            @Field("perlu_point") String perlupoint,
            @Field("id_reward") String idreward
    );

}
