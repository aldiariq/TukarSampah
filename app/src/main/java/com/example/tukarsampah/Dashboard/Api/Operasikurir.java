package com.example.tukarsampah.Dashboard.Api;

import com.example.tukarsampah.Dashboard.Model.Responsegettransaksikurir;
import com.example.tukarsampah.Dashboard.Model.Responseoperasi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Operasikurir {

    @FormUrlEncoded
    @POST("gettransaksikurir")
    Call<Responsegettransaksikurir> getTransaksikurir(
            @Field("id_kurir") String idkurir
    );

    @FormUrlEncoded
    @POST("suksestransaksi")
    Call<Responseoperasi> Terimatransaksi(
            @Field("id_transaksi") String idtransaksi,
            @Field("id_pengguna") String idpengguna,
            @Field("jumlah_transaksi") String jumlahtransaksi
    );

    @FormUrlEncoded
    @POST("ubahpassword")
    Call<Responseoperasi> ubahPassword(
            @Field("username") String username,
            @Field("passwordlama") String passwordlama,
            @Field("passwordbaru") String passwordbaru,
            @Field("tipeakun") String tipeakun
    );
}
