package com.capstone.wishata.injection

import android.content.Context
import com.capstone.wishata.data.local.AppPreferences
import com.capstone.wishata.data.local.dataStore
import com.capstone.wishata.data.network.retrofit.ApiConfig
import com.capstone.wishata.repository.WishataRepository

object Injection {

    fun provideStoryRepository(context: Context): WishataRepository {

        val appPreferences = AppPreferences.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService(appPreferences)

        return WishataRepository.getInstance(apiService, appPreferences)
    }
}