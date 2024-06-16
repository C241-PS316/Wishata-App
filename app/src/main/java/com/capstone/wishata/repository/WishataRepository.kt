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

    fun getUsername(): LiveData<String> {
        return appPreferences.getUsername().asLiveData()
    }

    fun login(
        username: String, password: String
    ): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(username, password)
            saveUsername(username)
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

        fun getInstance(
            placeDao: PlaceDao,
            apiService: ApiService,
            appPreferences: AppPreferences
        ): WishataRepository = WishataRepository(placeDao, apiService, appPreferences)
    }

}