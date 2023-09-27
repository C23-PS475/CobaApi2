package com.example.myapplication

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitAPI {
    @FormUrlEncoded
    @POST("store/data/index.php")
    fun sendData(
        @Field("sn") sn: String,
        @Field("username") username: String,
        @Field("location") location: String,
        @Field("lat") latitude: String,
        @Field("lon") longitude: String
    ): Call<ApiResponse>
}
