package com.capstone.wishata.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.capstone.wishata.data.Result
import com.capstone.wishata.data.network.response.ErrorResponse
import com.capstone.wishata.data.network.response.LoginResponse
import com.capstone.wishata.data.network.response.RegisterResponse
import com.capstone.wishata.data.network.retrofit.ApiService
import com.google.gson.Gson
import retrofit2.HttpException

class WishataRepository(private val apiService: ApiService) {

    fun register(username: String, email: String, password: String, confirmPassword: String) {

    }

    fun login(
        username: String, password: String
    ): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(username, password)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage.toString()))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {

        // writing this code will create new instance and update object inside
        fun getInstance(
            apiService: ApiService
        ): WishataRepository = WishataRepository(apiService)

    }
}