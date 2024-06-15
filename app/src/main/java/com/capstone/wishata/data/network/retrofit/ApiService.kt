package com.capstone.wishata.data.network.retrofit

import com.capstone.wishata.data.network.response.LoginResponse
import com.capstone.wishata.data.network.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confirmPassword") confirmPassword: String
    ): RegisterResponse

    @GET("assets/login_response")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginResponse

}