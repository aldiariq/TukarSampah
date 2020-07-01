package com.example.tukarsampah.Dashboard.Api;

import com.example.tukarsampah.Dashboard.Model.Responsekelolakuriradmin;
import com.example.tukarsampah.Dashboard.Model.Responsekelolapenggunaadmin;
import com.example.tukarsampah.Dashboard.Model.Responsekelolarewardadmin;
import com.example.tukarsampah.Dashboard.Model.Responseoperasi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Operasiadmin {

    @GET("getpengguna")
//    @Headers("Host: com.example.tukarsampah")
    Call<Responsekelolapenggunaadmin> getDatapengguna();

    @FormUrlEncoded
    @POST("deleteakun")
//    @Headers("Host: com.example.tukarsampah")
    Call<Responseoperasi> deletePengguna(
            @Field("username") String username,
            @Field("tipeakun") String tipeakun
    );

    @FormUrlEncoded
    @POST("resetpassakun")
//    @Headers("Host: com.example.tukarsampah")
    Call<Responseoperasi> resetpassPengguna(
            @Field("username") String username,
            @Field("tipeakun") String tipeakun
    );

    @GET("getkurir")
//    @Headers("Host: com.example.tukarsampah")
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
//    @Headers("Host: com.example.tukarsampah")
    Call<Responseoperasi> ubahPassword(
            @Field("username") String username,
            @Field("passwordlama") String passwordlama,
            @Field("passwordbaru") String passwordbaru,
            @Field("tipeakun") String tipeakun
    );


}
