package com.capstone.wishata.repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.liveData
import com.capstone.wishata.data.network.response.RegisterResponse
import com.capstone.wishata.data.network.response.WisataResponse
import com.capstone.wishata.data.network.response.WisataResponseItem
import com.capstone.wishata.data.network.retrofit.ApiService
import com.capstone.wishata.utils.Result
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import retrofit2.HttpException

class WishataRepository(private val apiService: ApiService) {

    fun register(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.register(username, email, password, confirmPassword)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, RegisterResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage))
        }

    }

    // get all wishata
    //@SuppressLint("BuildListAdds")
    fun getWisata(): LiveData<Result<List<WisataResponse>>> = liveData {
        emit((Result.Loading))
        try {
            val response = apiService.getWisata()
            val wisata = response.wisataResponse
            //emit(wisata)
        } catch (e: Exception) {
            Log.d("NewsRepository", "getHeadlineNews: ${e.message.toString()} ")
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