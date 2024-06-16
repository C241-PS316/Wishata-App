package com.capstone.wishata.data.network.retrofit

import AssetFileInterceptor
import android.content.Context
import android.content.res.AssetManager
import com.capstone.wishata.data.local.AppPreferences
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {

    companion object{
        fun getApiService(context: Context): ApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(AssetFileInterceptor(context.assets))
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://asia-southeast2-project-capstone-bec58.cloudfunctions.net/app/auth/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}