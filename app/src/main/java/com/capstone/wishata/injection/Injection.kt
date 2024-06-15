package com.capstone.wishata.injection

import android.content.Context
import com.capstone.wishata.data.network.retrofit.ApiConfig
import com.capstone.wishata.repository.WishataRepository

object Injection {

    fun provideStoryRepository(context: Context): WishataRepository {
        // get token from datastore

        val apiService = ApiConfig.getApiService(context)

        return WishataRepository.getInstance(apiService)
    }
}