package com.capstone.wishata.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.capstone.wishata.data.local.database.entity.Place

@Dao
interface PlaceDao {

    @Insert
    suspend fun insert(place: Place)

    @Delete
    suspend fun delete(place: Place)

    @Query("SELECT * FROM place")
    fun getAllPlaces(): LiveData<List<Place>>

    @Query("SELECT * FROM place WHERE id = :id")
    fun getPlace(id: String): LiveData<Place>

}