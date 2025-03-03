package com.capstone.wishata.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.capstone.wishata.data.local.AppPreferences
import com.capstone.wishata.data.local.database.entity.Place
import com.capstone.wishata.data.local.database.PlaceDao
import com.capstone.wishata.data.network.response.ErrorResponse
import com.capstone.wishata.data.network.response.LoginResponse
import com.capstone.wishata.data.network.response.RegisterResponse
import com.capstone.wishata.data.network.response.TopWisataResponse
import com.capstone.wishata.data.network.response.WisataResponse
import com.capstone.wishata.data.network.retrofit.ApiService
import com.google.gson.Gson
import retrofit2.HttpException
import com.capstone.wishata.utils.Result

class WishataRepository(
    private val placeDao: PlaceDao,
    private val apiService: ApiService,
    private val appPreferences: AppPreferences
) {

    suspend fun insertFavPlace(place: Place) {
        placeDao.insert(place)
    }

    suspend fun deleteFavPlace(place: Place) {
        placeDao.delete(place)
    }

    fun getAllFavPlace(): LiveData<List<Place>> {
        return placeDao.getAllPlaces()
    }

    fun getFavPlaceById(place: Place): LiveData<Place> {
        return placeDao.getPlace(place.id)
    }

    private suspend fun saveUsername(username: String) {
        appPreferences.saveUsername(username)
    }

    // register user
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
            emit(Result.Error(errorMessage ?: "NULL"))
        }

    }

    // get all wishata API
    fun getWisata(): LiveData<Result<WisataResponse>> = liveData {
        emit((Result.Loading))
        try {
            val response = apiService.getWisata()
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, WisataResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage ?: "ERROR"))
        }
    }

    // get Top Place
    fun getTopPlace(): LiveData<Result<TopWisataResponse>> = liveData {
        emit((Result.Loading))
        try {
            val response = apiService.getTopPlace()
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, TopWisataResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage ?: "ERROR"))
        }
    }
    fun getUsername(): LiveData<String> {
        return appPreferences.getUsername().asLiveData()
    }

    fun login(
        email: String, password: String
    ): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(email, password)
            saveUsername(email)
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
            placeDao: PlaceDao,
            apiService: ApiService,
            appPreferences: AppPreferences
        ): WishataRepository = WishataRepository(placeDao, apiService, appPreferences)
    }

}