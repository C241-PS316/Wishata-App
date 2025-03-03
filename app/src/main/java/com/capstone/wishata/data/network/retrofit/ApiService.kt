package com.capstone.wishata.data.network.retrofit

import com.capstone.wishata.data.network.response.RegisterResponse
import com.capstone.wishata.data.network.response.WisataResponse
import com.capstone.wishata.data.network.response.LoginResponse
import com.capstone.wishata.data.network.response.TopWisataResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    @FormUrlEncoded
    @POST("auth/register")
    suspend fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confirmPassword") confirmPassword: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("wisata")
    suspend fun getWisata(): WisataResponse

    @GET("wisata/top")
    suspend fun getTopPlace(): TopWisataResponse

}