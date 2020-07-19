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
//    @Headers("Host: com.example.tukarsampah")
    Call<Responsetransaksigetkurirpengguna> getDatakurir();

    @FormUrlEncoded
//    @Headers("Host: com.example.tukarsampah")
    @POST("ubahpassword")
    Call<Responseoperasi> ubahPassword(
            @Field("username") String username,
            @Field("passwordlama") String passwordlama,
            @Field("passwordbaru") String passwordbaru,
            @Field("tipeakun") String tipeakun
    );

    @FormUrlEncoded
//    @Headers("Host: com.example.tukarsampah")
    @POST("transaksi")
    Call<Responseoperasi> Transaksi(
            @Field("id_pengguna") String idpengguna,
            @Field("tipe_sampah") String tipesampah,
            @Field("id_kurir") String idkurir,
            @Field("jumlah_transaksi") String jumlahtransaksi
    );

    @FormUrlEncoded
//    @Headers("Host: com.example.tukarsampah")
    @POST("gettransaksipengguna")
    Call<Responsegettransaksipengguna> getTransaksipengguna(
            @Field("id_pengguna") String idpengguna
    );

    @FormUrlEncoded
//    @Headers("Host: com.example.tukarsampah")
    @POST("bataltransaksi")
    Call<Responseoperasi> Bataltransaksipengguna(
            @Field("id_transaksi") String idtransaksi
    );

    @FormUrlEncoded
//    @Headers("Host: com.example.tukarsampah")
    @POST("ambilpoint")
    Call<Responsetukarpointgetpointpengguna> getPointpengguna(
            @Field("id_pengguna") String idpengguna
    );

//    @Headers("Host: com.example.tukarsampah")
    @GET("getreward")
    Call<Responsetukarpointgetrewardpengguna> getDatareward();

    @FormUrlEncoded
//    @Headers("Host: com.example.tukarsampah")
    @POST("tukarpoint")
    Call<Responseoperasi> Tukarpoint(
            @Field("id_pengguna") String idpengguna,
            @Field("jumlah_point") String jumlahpoint,
            @Field("perlu_point") String perlupoint,
            @Field("id_reward") String idreward
    );

    @FormUrlEncoded
//    @Headers("Host: com.example.tukarsampah")
    @POST("getberlanggananpengguna")
    Call<Responseoperasi> getBerlanggananpengguna(
            @Field("id_pengguna") String idpengguna
    );

    @FormUrlEncoded
//    @Headers("Host: com.example.tukarsampah")
    @POST("setberlanggananpengguna")
    Call<Responseoperasi> setBerlanggananpengguna(
            @Field("id_pengguna") String idpengguna
    );



}
