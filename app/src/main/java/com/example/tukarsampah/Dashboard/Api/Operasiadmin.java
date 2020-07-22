package com.example.tukarsampah.Dashboard.Api;

import com.example.tukarsampah.Dashboard.Model.Responsekelolakuriradmin;
import com.example.tukarsampah.Dashboard.Model.Responsekelolapenggunaadmin;
import com.example.tukarsampah.Dashboard.Model.Responsekelolarewardadmin;
import com.example.tukarsampah.Dashboard.Model.Responseoperasi;
import com.example.tukarsampah.Dashboard.Model.Responseprofiladmin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Operasiadmin {

    @GET("getpengguna")
    @Headers("Accept: */*")
    Call<Responsekelolapenggunaadmin> getDatapengguna();

    @FormUrlEncoded
    @POST("deleteakun")
    @Headers("Accept: */*")
    Call<Responseoperasi> deletePengguna(
            @Field("username") String username,
            @Field("tipeakun") String tipeakun
    );

    @FormUrlEncoded
    @POST("resetpassakun")
    @Headers("Accept: */*")
    Call<Responseoperasi> resetpassPengguna(
            @Field("username") String username,
            @Field("tipeakun") String tipeakun
    );

    @GET("getkurir")
    @Headers("Accept: */*")
    Call<Responsekelolakuriradmin> getDatakurir();

    @GET("getreward")
    @Headers("Accept: */*")
    Call<Responsekelolarewardadmin> getDatareward();

    @FormUrlEncoded
    @POST("deletereward")
    Call<Responseoperasi> deleteReward(
            @Field("id_reward") String idreward
    );

    @FormUrlEncoded
    @POST("setreward")
    Call<Responseoperasi> setReward(
            @Field("hadiah_reward") String hadiahreward,
            @Field("point_reward") String pointreward
    );

    @FormUrlEncoded
    @POST("ubahpassword")
    @Headers("Accept: */*")
    Call<Responseoperasi> ubahPassword(
            @Field("username") String username,
            @Field("passwordlama") String passwordlama,
            @Field("passwordbaru") String passwordbaru,
            @Field("tipeakun") String tipeakun
    );

    @FormUrlEncoded
    @POST("setnohpadmin")
    @Headers("Accept: */*")
    Call<Responseoperasi> ubahNohp(
            @Field("nohp_admin") String nohp
    );

    @FormUrlEncoded
//    @Headers("Host: com.example.tukarsampah")
    @POST("setberlanggananpengguna")
    Call<Responseoperasi> setBerlanggananpengguna(
            @Field("id_pengguna") int idpengguna
    );

    @FormUrlEncoded
//    @Headers("Host: com.example.tukarsampah")
    @POST("getProfiladmin")
    Call<Responseprofiladmin> getProfiladmin(
            @Field("id_admin") String idadmin
    );



}
