package com.capstone.wishata.repository

import androidx.lifecycle.LiveData
import com.capstone.wishata.data.network.response.RegisterResponse
import com.capstone.wishata.data.network.retrofit.ApiService

class WishataRepository(private val apiService: ApiService) {

    fun register(username: String, email: String, password: String, confirmPassword: String) {

    }
    companion object {

        // writing this code will create new instance and update object inside
        fun getInstance(
            apiService: ApiService
        ): WishataRepository = WishataRepository(apiService)

    }
}