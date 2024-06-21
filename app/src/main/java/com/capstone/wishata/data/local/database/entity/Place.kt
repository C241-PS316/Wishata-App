package com.capstone.wishata.data.local.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "place")
@Parcelize
data class Place(

	@ColumnInfo(name = "photoUrl")
	@field:SerializedName("photoUrl")
	val photoUrl: String,

	@ColumnInfo(name = "namePlace")
	@field:SerializedName("namePlace")
	val namePlace: String,

	@ColumnInfo(name = "description")
	@field:SerializedName("description")
	val description: String,

	@ColumnInfo(name = "rating")
	@field:SerializedName("rating")
	val rating: Double,

	@ColumnInfo(name = "lon")
	@field:SerializedName("lon")
	val lon: Double,

	@PrimaryKey
	@ColumnInfo(name = "id")
	@field:SerializedName("id")
	val id: String,

	@ColumnInfo(name = "lat")
	@field:SerializedName("lat")
	val lat: Double
): Parcelable
