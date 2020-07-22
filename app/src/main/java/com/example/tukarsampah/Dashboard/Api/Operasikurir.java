package com.example.tukarsampah.Dashboard.Api;

import com.example.tukarsampah.Dashboard.Model.Responsegettransaksikurir;
import com.example.tukarsampah.Dashboard.Model.Responseoperasi;
import com.example.tukarsampah.Dashboard.Model.Responseprofilkurir;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Operasikurir {

    @FormUrlEncoded
//    @Headers("Host: com.example.tukarsampah")
    @POST("gettransaksikurir")
    Call<Responsegettransaksikurir> getTransaksikurir(
            @Field("id_kurir") String idkurir
    );

    @FormUrlEncoded
//    @Headers("Host: com.example.tukarsampah")
    @POST("suksestransaksi")
    Call<Responseoperasi> Terimatransaksi(
            @Field("id_transaksi") String idtransaksi,
            @Field("id_pengguna") String idpengguna,
            @Field("jumlah_transaksi") String jumlahtransaksi
    );

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
    @POST("getProfilkurir")
    Call<Responseprofilkurir> getProfilkurir(
            @Field("id_kurir") String idkurir
    );
}
