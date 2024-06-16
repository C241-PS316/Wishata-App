package com.capstone.wishata.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.capstone.wishata.data.local.AppPreferences
import com.capstone.wishata.data.network.response.ErrorResponse
import com.capstone.wishata.data.network.response.LoginResponse
import com.capstone.wishata.data.network.retrofit.ApiService
import com.google.gson.Gson
import retrofit2.HttpException
import com.capstone.wishata.utils.Status

class WishataRepository(
    private val apiService: ApiService,
    private val appPreferences: AppPreferences
) {



    fun register(username: String, email: String, password: String, confirmPassword: String) {}

    fun login(
        username: String, password: String
    ): LiveData<Status<LoginResponse>> = liveData {
        emit(Status.Loading)
        try {
            val response = apiService.login(username, password)
            emit(Status.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Status.Error(errorMessage.toString()))
        } catch (e: Exception) {
            emit(Status.Error(e.message.toString()))
        }
    }

    companion object {

        // writing this code will create new instance and update object inside
        fun getInstance(
            apiService: ApiService,
            appPreferences: AppPreferences
        ): WishataRepository = WishataRepository(apiService, appPreferences)

    }
}