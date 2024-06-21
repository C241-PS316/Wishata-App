package com.capstone.wishata.data.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class WisataResponse(

	@field:SerializedName("data")
	val data: List<WisataItem> = emptyList(),

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
) {
	@Parcelize

	data class WisataItem(

		@field:SerializedName("photoURL")
		val photoURL: String,

		@field:SerializedName("name")
		val name: String,

		@field:SerializedName("rating")
		val rating: Double,

		@field:SerializedName("category_classes")
		val categoryClasses: String,

		@field:SerializedName("description")
		val description: String,

		@field:SerializedName("lon")
		val lon: Double,

		@field:SerializedName("id")
		val id: String,

		@field:SerializedName("lat")
		val lat: Double,

		@field:SerializedName("environment_classes")
		val environmentClasses: String,

		@field:SerializedName("scenery_classes")
		val sceneryClasses: String
	): Parcelable
}