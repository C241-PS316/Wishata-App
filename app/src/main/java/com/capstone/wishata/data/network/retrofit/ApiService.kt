package com.capstone.wishata.data.network.retrofit

import com.capstone.wishata.data.network.response.RegisterResponse
import com.capstone.wishata.data.network.response.WisataResponse
import com.capstone.wishata.data.network.response.LoginResponse
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

//    @GET("auth/login")
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("wisata")
    suspend fun getWisata(): WisataResponse

}